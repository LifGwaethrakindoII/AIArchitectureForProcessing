package VoidlessUtilities.Processing.Core;

import VoidlessUtilities.*;
import VoidlessUtilities.Interfaces.IResettable;

public class PObject implements IResettable// implements IReproducible<PObject>
{
	private boolean active;
	private boolean previousActiveState;
	
	public boolean active() { return active; }
	public void active(boolean _active)
	{
		active = _active;
		
		if(active != previousActiveState)
		{
			if(active) OnEnabled();
			else OnDisabled();
		}
		
		previousActiveState = active;
	}
	
	protected boolean previousActiveState() { return previousActiveState; }
	protected void previousActiveState(boolean _previousActiveState) { previousActiveState = _previousActiveState; }
	
	public int ID() { return System.identityHashCode(this); }
	
	public PObject()
	{
		Reset();
	}
	
	public void OnEnabled() { /*...*/ }
	
	public void OnDisabled() { /*...*/ }
	
	public void OnAwake() { /*...*/ }
	
	public void OnStart() { /*...*/ }
	
	public void OnDestroyed() { /*...*/ }
	
	public void Destroy() throws Throwable
	{
		OnDestroyed();
		finalize();
	}
	
	public PObject Reproduce()
	{
		return new PObject();
	}

	@Override
	public void Reset()
	{
		active = true;
		previousActiveState = active;
	}
}
