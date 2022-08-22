package VoidlessUtilities.Processing.Interfaces;

import java.util.*;
import java.util.concurrent.*;

public interface IPComposite<T extends IPComponent> extends IPComponent
{
	CopyOnWriteArrayList<T> components();
	
	<C> C GetComponent(Class<C> _component);
	
	void AddComponent(T _child);
	
	void AddComponents(T... _children);
	
	void RemoveComponent(T _child);
	
	void RemoveComponents();
}
