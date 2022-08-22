package VoidlessUtilities.Interfaces;

public interface IObserver<T>
{
	void la(IObserver<Void> oc);
	T Update();
}
