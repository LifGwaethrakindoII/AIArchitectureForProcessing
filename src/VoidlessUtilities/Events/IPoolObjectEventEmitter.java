package VoidlessUtilities.Events;

import java.util.concurrent.*;
import VoidlessUtilities.*;
import VoidlessUtilities.ObjectPooling.IPoolObject;

public interface IPoolObjectEventEmitter
{
	CopyOnWriteArrayList<IPoolObjectEventListener> listeners();
	void listeners(CopyOnWriteArrayList<IPoolObjectEventListener> _listeners);
	
	default void AddListener(IPoolObjectEventListener _listener)
	{
		if(listeners() != null) listeners().add(_listener);
	}
	
	default void RemoveListener(IPoolObjectEventListener _listener)
	{
		if(listeners() != null) listeners().remove(_listener);
	}
	
	default void Emit(IPoolObject _poolObject)
	{
		if(listeners() != null)
		{
			for(IPoolObjectEventListener listener : listeners())
			{
				listener.OnPoolObjectDeactivation(_poolObject);
			}
		}
	}
}
