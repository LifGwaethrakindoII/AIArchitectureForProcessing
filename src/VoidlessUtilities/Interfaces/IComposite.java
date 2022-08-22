package VoidlessUtilities.Interfaces;

import java.util.*;

public interface IComposite<T extends IComponent> extends IComponent
{
	ArrayList<T> GetComponents();
		
	void AddComponent(T _child);
	
	@SuppressWarnings("unchecked")
	void AddComponents(T... _children);
	
	void RemoveComponent(T _child);
	
	void RemoveComponents();
}
