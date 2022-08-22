package VoidlessUtilities.Collections;

import java.util.*;

public class StackQueue<T>
{
	private LinkedList<T> linkedList;
	
	public int count() { return linkedList.size(); }
	
	public StackQueue()
	{
		linkedList = new LinkedList<T>();
	}
	
	public void Enqueue(T _item)
	{
		linkedList.addFirst(_item);
	}
	
	public T Dequeue()
	{
		return linkedList.peek() != null ? linkedList.remove() : null;
	}
	
	public void Push(T _item)
	{
		linkedList.addLast(_item);
	}
	
	public T Pop()
	{
		return linkedList.pop();
	}
	
	public T PeekFirst()
	{
		return linkedList.peekFirst();
	}
	
	public T PeekLast()
	{
		return linkedList.peekLast();
	}
	
	public void Clear()
	{
		linkedList.clear();
	}
}
