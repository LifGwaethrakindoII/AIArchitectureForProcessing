package VoidlessUtilities.Interfaces;

public interface IResettable
{
	/// Resets PObject.
	/// It will allow PObject classes to have default initialization inside Reset.
	void Reset();
}