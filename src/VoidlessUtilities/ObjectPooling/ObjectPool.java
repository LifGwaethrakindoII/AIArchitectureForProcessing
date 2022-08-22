package VoidlessUtilities.ObjectPooling;

import VoidlessUtilities.VoidlessMath;
import VoidlessUtilities.Collections.StackQueue;
import VoidlessUtilities.Events.*;

import java.util.*;

public class ObjectPool<T extends IReproducible<T> & IPoolObject> implements IPoolObjectEventListener
{
	private StackQueue<T> poolQueue;
	private T cloneableObject;
	private int occupiedSlots;
	
	public int count() { return poolQueue.count(); }
	
	public ObjectPool(T _cloneableObject)
	{
		cloneableObject = _cloneableObject;
		poolQueue = new StackQueue<T>();
	}
	
	/* Adds new object to pool. */
	public T Add()
	{
		T newObject = null;
		
		/*if(occupiedSlots <= count())
		{
			newObject = poolQueue.Dequeue();
		}
		else*/
		{
			newObject = cloneableObject.Reproduce();
			newObject.emitter().AddListener(this);
			newObject.OnCreation();
		}
		
		poolQueue.Enqueue(newObject);
		return newObject;
	}
	
	public T Recycle()
	{
		T recycledObject = count() > 0 ? poolQueue.PeekFirst() : null;
		
		if(recycledObject != null && !recycledObject.active())
		{
			poolQueue.Dequeue();
			poolQueue.Enqueue(recycledObject);
		}
		else recycledObject = Add();
		
		recycledObject.OnRecycle();
		occupiedSlots++;
		return recycledObject;
	}
	
	public void Dispatch()
	{
		if(count() <= 0) return;
		
		T objectToDispatch = poolQueue.Dequeue();
		if(objectToDispatch != null) objectToDispatch.OnDestruction();
	}
	
	public void Dispatch(int _count)
	{
		if(count() <= 0) return;
		T objectToDispatch = null;
		
		for(int i = 0; i < VoidlessMath.Min(_count, count()); i++)
		{
			objectToDispatch = poolQueue.Dequeue();
			if(objectToDispatch != null) objectToDispatch.OnDestruction();
		}
	}

	@Override
	public void OnPoolObjectDeactivation(IPoolObject _poolObject)
	{
		occupiedSlots--;
		System.out.println("Pool Object " + _poolObject.toString() + " deactivated.");
	}
}
