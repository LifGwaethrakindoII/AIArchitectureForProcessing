package VoidlessUtilities.Processing.Core;

import java.util.*;
import java.util.concurrent.*;
import VoidlessUtilities.*;
import VoidlessUtilities.Enumerators.ComponentCycleState;
import VoidlessUtilities.ObjectPooling.IReproducible;
import VoidlessUtilities.Processing.Interfaces.IPCollisionListener;
import VoidlessUtilities.Processing.Interfaces.IPComposite;
import VoidlessUtilities.Structures.LayerMask;
import processing.core.*;
import com.google.gson.annotations.*;
/*
 * PEntity Class.
 * PEntity acts like an Entity, containing Components of the type PComponent.
 * Contains a default PComponent PTransform, that acts as a transformation class for Processing.
 * 
 * @author Lif Gwaethrakindo
 * @version 1.0
 * @since 1.0
 * */
public class PEntity extends PObject implements IPComposite<PComponent>, IReproducible<PEntity>
{
	private String name; 													/// Entity's Name.
	private String tag; 													/// Entity's Tag.
	private transient PTransform transform; 								/// Entity's Transform.
	private CopyOnWriteArrayList<PComponent> components; 					/// Entity's Components.
	private CopyOnWriteArrayList<IPCollisionListener> collisionListeners; 	/// Entity's Collision Listener Components.
	private short contactLayer; 											/// Entity's Contact Layer.
	private short visibleLayer; 											/// Entity's Visible Layer.
	private short audibleLayer; 											/// Entity's Audible Layer.
	private short odorableLayer; 											/// Entity's Odorable Layer.
	
	public String name() { return name; }
	public void name(String _name) { name = _name; }
	
	public String tag() { return tag; }
	public void tag(String _tag) { tag = _tag; }
	
	public PTransform transform() { return transform; }
	
	public CopyOnWriteArrayList<PComponent> components() { return components; }
	
	public CopyOnWriteArrayList<IPCollisionListener> collisionListeners() { return collisionListeners; }
	
	public short contactLayer() { return contactLayer; }
	public void contactLayer(short _contactLayer){ if(LayerMask.LayerWithinRange(_contactLayer)) contactLayer = _contactLayer; }
	
	public short visibleLayer() { return visibleLayer; }
	public void visibleLayer(short _visibleLayer){ if(LayerMask.LayerWithinRange(_visibleLayer)) visibleLayer = _visibleLayer; }

	public short audibleLayer() { return audibleLayer; }
	public void audibleLayer(short _audibleLayer){ if(LayerMask.LayerWithinRange(_audibleLayer)) audibleLayer = _audibleLayer; }

	public short odorableLayer() { return odorableLayer; }
	public void odorableLayer(short _odorableLayer){ if(LayerMask.LayerWithinRange(_odorableLayer)) odorableLayer = _odorableLayer; }
	
	/*PEntity's default constructor.*/
	public PEntity()
	{
		Reset();
	}
	
	/*
	 * PEntity's Constructor.
	 * @param _components Components to add to Entity once it is instantiated.
	 * */
	public PEntity(PComponent... _components)
	{
		Reset();
		AddComponents(_components);
	}
	
	/*
	 * PEntity's Constructor.
	 * @param _components Components to add to Entity once it is instantiated.
	 * */
	public PEntity(String _name, PComponent... _components)
	{
		Reset();
		name(_name);
		AddComponents(_components);
	}
	
	/* Callback invoked when Entity is enabled. */
	@Override
	public void OnEnabled()
	{
		for(PComponent component : components)
		{
			component.enabled(true);
		}
	}
	
	/* Callback invoked when Entity is disabled. */
	@Override
	public void OnDisabled()
	{
		for(PComponent component : components)
		{
			component.enabled(false);
		}
	}
	
	/* Callback invoked when Entity is instantiated. */
	@Override
	public void OnAwake()
	{
		for(PComponent component : components)
		{
			component.OnAwake();
		}
	}
	
	/* Updates Entity. */
	@Override
	public void Update()
	{
		UpdateComponents();
	}
	
	/* Updates Entity's Components */
	private void UpdateComponents()
	{
		if(active())
		for(PComponent component : components)
		{
			component.Update();
		}
	}
	
	/* Resets Entity. */
	public void Reset()
	{
		super.Reset();
		active(true);
		components = new CopyOnWriteArrayList<PComponent>();
		collisionListeners = new CopyOnWriteArrayList<IPCollisionListener>();
		transform = new PTransform();
		AddComponent(transform);
	}
	
	/* Debugs Entity's Components. */
	public void Debug()
	{
		if(active())
		for(PComponent component : components)
		{
			component.Debug();
		}
	}
	
	/*
	 * Gets Entity's Component.
	 * @param _componentClass Component's Class.
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public <C> C GetComponent(Class<C> _componentClass)
	{
		for(PComponent component : components)
		{
			if(component.getClass() == _componentClass) return (C)component;
		}
		
		return null;
	}

	/*
	 * Adds Component
	 * @param _child Component to add.
	 * */
	public void AddComponent(PComponent _child)
	{
		components.add(_child);
		if(_child.getClass().isAssignableFrom(IPCollisionListener.class))
			collisionListeners.add((IPCollisionListener)_child);
		_child.entity(this);
	}

	/*
	 * Add Components.
	 * @param _children Components to add.
	 * */
	public void AddComponents(PComponent... _children)
	{
		for(PComponent child : _children)
		{
			components.add(child);
			if(child.getClass().isAssignableFrom(IPCollisionListener.class))
				collisionListeners.add((IPCollisionListener)child);
			child.entity(this);
		}
	}

	/*
	 * Removes Component.
	 * @param _child Component to remove.
	 * */
	public void RemoveComponent(PComponent _child)
	{
		if(_child.getClass().isAssignableFrom(IPCollisionListener.class))
			collisionListeners.remove((IPCollisionListener)_child);
		components.add(_child);
		_child.cycleState(ComponentCycleState.ToBeRemoved);
		// TODO: Dispatch the component.
	}
	
	/*Removes Component.*/
	public void RemoveComponents() { /*...*/ }

	
	/* Reproduces Entity with all its components. */
	public PEntity Reproduce()
	{
		PEntity entity = new PEntity();
		
		for(PComponent component : components())
		{
			if(component instanceof PTransform)
			{
				PTransform transform = (PTransform)component;
				transform.CopyTransform(transform());
			}
			
			entity.AddComponent(component);
		}
		
		return entity;
	}
}
