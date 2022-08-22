package VoidlessUtilities.Processing.Core;

import com.google.gson.annotations.*;
import processing.core.*;
import VoidlessUtilities.*;
import VoidlessUtilities.Enumerators.ComponentCycleState;
import VoidlessUtilities.Interfaces.IPDebuggable;
import VoidlessUtilities.ObjectPooling.IReproducible;
import VoidlessUtilities.Processing.Interfaces.IPComponent;

public abstract class PComponent extends PObject implements IPComponent, IPDebuggable, IReproducible<PComponent>
{
	private transient PEntity entity;
	private ComponentCycleState cycleState;
	private boolean enabled;
	private boolean previousEnabledState;
	
	public PEntity entity() { return entity; }
	public void entity(PEntity _entity)
	{
		if(entity == null) entity = _entity;
	}
	
	public PTransform transform() { return entity.transform(); }
	
	public ComponentCycleState cycleState() { return cycleState; }
	public void cycleState(ComponentCycleState _cycleState)
	{
		cycleState = _cycleState;
		
		switch(cycleState)
		{
			case None:
				enabled = true;
			break;
			
			default:
				if(enabled) enabled = false;
			break;
		}
	}

	public boolean enabled() { return enabled; }
	public void enabled(boolean _enabled)
	{
		if(cycleState != ComponentCycleState.None)
		{
			enabled = _enabled;
			
			if(enabled != previousEnabledState)
			{
				if(enabled) OnEnable();
				else OnDisable();
			}
			
			previousEnabledState = enabled;
		}
	}

	public void OnEnable() { /*...*/ }
	
	public void OnDisable() { /*...*/ }

	public void OnAwake() { /*...*/ }

	public void OnStart() { /*...*/ }

	public void Update() { /*...*/ }
	
	public void Debug() { /*...*/ }
	
	@Override
	public void Reset()
	{
		super.Reset();
	}
	
	public PComponent Reproduce()
	{
		return null;
	}
}
