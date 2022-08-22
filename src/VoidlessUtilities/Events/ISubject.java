package VoidlessUtilities.Events;

import VoidlessUtilities.Interfaces.IObserver;

public interface ISubject<T extends IObserver<T>>
{
	void AddObserver(IObserver<T> _observer);
	
	void RemoveObserver(IObserver<T> _observer);
	
	void NotifyOvservers();
}