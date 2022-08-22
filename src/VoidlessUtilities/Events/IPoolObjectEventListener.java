package VoidlessUtilities.Events;

import VoidlessUtilities.*;
import VoidlessUtilities.ObjectPooling.IPoolObject;

public interface IPoolObjectEventListener
{
	void OnPoolObjectDeactivation(IPoolObject _poolObject);
}
