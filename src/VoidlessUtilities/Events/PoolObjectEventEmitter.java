package VoidlessUtilities.Events;

import java.util.concurrent.CopyOnWriteArrayList;

public class PoolObjectEventEmitter implements IPoolObjectEventEmitter
{
	private CopyOnWriteArrayList<IPoolObjectEventListener> listeners;

	@Override
	public CopyOnWriteArrayList<IPoolObjectEventListener> listeners() { return listeners; }

	@Override
	public void listeners(CopyOnWriteArrayList<IPoolObjectEventListener> _listeners) { listeners = _listeners; }

	public PoolObjectEventEmitter()
	{
		listeners = new CopyOnWriteArrayList<IPoolObjectEventListener>();
	}
}
