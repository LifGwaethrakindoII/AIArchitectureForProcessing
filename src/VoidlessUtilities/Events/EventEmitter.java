package VoidlessUtilities.Events;

import java.util.concurrent.CopyOnWriteArrayList;

public interface EventEmitter<T>
{
	CopyOnWriteArrayList<EventListener<T>> listeners();
	void listeners(CopyOnWriteArrayList<EventListener<T>> _listeners);
	
	
	default void AddListener(EventListener<T> _listener)
	{
		if(listeners() != null) listeners().add(_listener);
	}
	
	default void RemoveListener(EventListener<T> _listener)
	{
		if(listeners() != null) listeners().remove(_listener);
	}
	
	default void Emit(T _argument)
	{
		if(listeners() != null)
		{
			for(EventListener<T> listener : listeners())
			{
				listener.OnEvent(_argument);
			}
		}
	}
}
