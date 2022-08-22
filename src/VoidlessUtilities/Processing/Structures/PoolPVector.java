package VoidlessUtilities.Processing.Structures;

import VoidlessUtilities.*;
import VoidlessUtilities.Events.*;
import VoidlessUtilities.ObjectPooling.IPoolObject;
import VoidlessUtilities.ObjectPooling.IReproducible;
import processing.core.PVector;

public class PoolPVector extends PVector implements IPoolObject, IReproducible<PoolPVector>
{
	private static final long serialVersionUID = 1L;

	IPoolObjectEventEmitter emitter;
	private boolean active;
	
	@Override public IPoolObjectEventEmitter emitter() { return emitter; }

	@Override public boolean active() { return active; }

	@Override public void active(boolean _active) { active = _active; }
	
	public PoolPVector()
	{
		super();
		active = false;
		emitter = new PoolObjectEventEmitter();
	}
	
	@Override
	public PoolPVector Reproduce()
	{
		return new PoolPVector();
	}
}
