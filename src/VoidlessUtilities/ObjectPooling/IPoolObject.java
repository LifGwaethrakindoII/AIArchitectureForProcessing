package VoidlessUtilities.ObjectPooling;

import VoidlessUtilities.Events.*;

public interface IPoolObject
{
	IPoolObjectEventEmitter emitter();
	
	boolean active();
	void active(boolean _active);
	
	default void OnCreation()
	{
		active(false);
	}
	
	default void OnRecycle()
	{
		active(true);
	}
	
	default void OnDeactivation()
	{
		active(false);
		if(emitter() != null) emitter().Emit(this);
	}
	
	default void OnDestruction()
	{
		OnDeactivation();
	}
}
