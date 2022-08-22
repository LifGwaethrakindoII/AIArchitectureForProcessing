package VoidlessUtilities.Processing.Colliders;

import processing.core.*;
import java.util.*;

//import VoidlessUtilities.Processing.*;
import VoidlessUtilities.*;
import VoidlessUtilities.Enumerators.*;
import VoidlessUtilities.Processing.Core.*;
import VoidlessUtilities.Processing.Structures.*;
import VoidlessUtilities.Processing.Physics.*;

public abstract class PCollider extends PComponent implements IPPhysicsInteractable
{
	/// TODO:
	/*
	 * - Change OnTrigger and OnCollision for OnContact, so the Collider can solve internally
	 * Which callbacks to invoke (depending if it is Trigger or not)
	 * - When setting isTrigger to true/false, Register Collision/Trigger Contacts as false
	 * - Change RegisterTrigger and RegisterCollision with RegisterContact, with the similar
	 * OnContact's callback premise.
	 * */
	protected HashMap<PCollider, Boolean> contactsMap;
	protected HashMap<Integer, Boolean> collisionsMap;
	protected HashMap<Integer, Boolean> triggersMap;
	private PVector position;
	private PBounds bounds;
	private boolean isTrigger;
	private boolean previousIsTrigger;
	private int interactionCount;
	
	@Override public PVector position() { return position.copy().add(entity().transform().position()); }
	@Override public void position(PVector _position) { position = _position; }
	
	@Override public abstract int interactableID();
	
	@Override public abstract float data(int _ID);
	
	public PBounds bounds() { return bounds; }
	public void bounds(PBounds _bounds) { bounds = _bounds; }
	
	public boolean isTrigger() { return isTrigger; }
	public void isTrigger(boolean _isTrigger)
	{
		isTrigger = _isTrigger;
		
		if(isTrigger != previousIsTrigger)
		{
			for(Map.Entry<PCollider, Boolean> contactEntry : contactsMap.entrySet())
			{
				if(contactEntry.getValue()) contactEntry.getKey();
			}
			
			previousIsTrigger = isTrigger;
		}
		
		if(isTrigger)
		{
			for(boolean register : collisionsMap.values())
			{
				register = false;
			}
		}
		else
		{
			for(boolean register : triggersMap.values())
			{
				register = false;
			}
		}
	}
	
	public boolean HasInteraction() { return interactionCount > 0; }
	
	public PCollider()
	{
		Reset();
		PPhysicsSystem.AddInteractable(this);
	}
	
	public PCollider(boolean _isTrigger)
	{
		Reset();
		previousIsTrigger = _isTrigger;
		isTrigger(_isTrigger);
		PPhysicsSystem.AddInteractable(this);
	}
	
	public void finalize()
	{
		PPhysicsSystem.RemoveInteractable(this);
	}
	
	@Override public void Reset()
	{
		super.Reset();
		bounds = new PBounds();
		interactionCount = 0;
		position = new PVector();
		contactsMap = new HashMap<PCollider, Boolean>();
		collisionsMap = new HashMap<Integer, Boolean>();
		triggersMap = new HashMap<Integer, Boolean>();
		PPhysicsSystem.GetInstance().AddCollider(this);
		previousIsTrigger = false;
		isTrigger(previousIsTrigger);
	}
	
	@Override public void OnDestroyed()
	{
		super.OnDestroyed();
		PPhysicsSystem.GetInstance().RemoveCollider(this);
	}
	
	public abstract boolean CollisionDetected(PCollider _collider);
	
	public void RegisterCollision(int _ID, boolean _collided)
	{
		collisionsMap.put(_ID, _collided);
		if(_collided) interactionCount++;
		else interactionCount = VoidlessMath.Max(--interactionCount, 0);
	}
	
	public void RegisterTrigger(int _ID, boolean _triggered)
	{
		triggersMap.put(_ID, _triggered);
	}
	
	public boolean HasRegisteredContactsWith(int _ID)
	{
		if(!isTrigger) return collisionsMap.containsKey(_ID) ? collisionsMap.get(_ID) : false;
		else return triggersMap.containsKey(_ID) ? triggersMap.get(_ID) : false;
	}
	
	public boolean HasRegisteredCollisionWith(int _ID)
	{
		return collisionsMap.containsKey(_ID) ? collisionsMap.get(_ID) : false;
	}
	
	public boolean HasRegisteredTriggerWith(int _ID)
	{
		return triggersMap.containsKey(_ID) ? triggersMap.get(_ID) : false;
	}
	
	protected void UpdateBounds() {/**/}
	
	@Override public void Update()
	{
		UpdateBounds();
	}
	
	public void OnContact(PCollider _collider, ContactState _state)
	{
		if(isTrigger || _collider.isTrigger())
		{
			/*for(IPCollisionListener listener : entity().collisionListeners())
			{
				listener.OnTrigger(_collider, _state);
			}*/
		}
		else
		{
			//if(entity().body() != null) entity().body().OnCollision(_collider, _state);
			/*for(IPCollisionListener listener : entity().collisionListeners())
			{
				listener.OnCollision(_collider, _state);
			}*/
		}
	}
	
	@Override public void Debug()
	{
		super.Debug();
		bounds.Debug();
		PSketch.sketch().stroke(PColor.Green());
		PSketch.sketch().noFill();
		//PSketch.sketch().fill(PColor.Color(0, 0, 0, 0));
	}
}