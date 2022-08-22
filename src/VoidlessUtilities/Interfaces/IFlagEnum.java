package VoidlessUtilities.Interfaces;

public interface IFlagEnum<T extends Enum<T>>
{
	int value();
	void value(int _value);
	
	default boolean HasFlag(IFlagEnum<T> _enum)
	{
		return ((value() & _enum.value()) == _enum.value());
	}
	
	default boolean HasFlags(IFlagEnum<T> ... _enums)
	{
		for(IFlagEnum<T> e : _enums)
		{
			if((value() & e.value()) != e.value()) return false;
		}
		return true;
	}
	
	default void AddFlag(IFlagEnum<T> _enum)
	{
		value(value() | _enum.value());
	}
	
	default void AddFlags(IFlagEnum<T> ... _enums)
	{
		for(IFlagEnum<T> e : _enums)
		{
			value(value() | e.value());
		}
	}
	
	default void RemoveFlag(IFlagEnum<T> _enum)
	{
		if((value() & _enum.value()) == _enum.value()) value(value() ^ _enum.value());
	}
	
	default void RemoveFlags(IFlagEnum<T> ... _enums)
	{
		for(IFlagEnum<T> e : _enums)
		{
			if((value() & e.value()) == e.value()) value(value() ^ e.value());
		}
	}
	
	default void ToggleFlag(IFlagEnum<T> _enum)
	{
		value(value() ^ _enum.value());
	}
	
	default void ToggleFlags(IFlagEnum<T> ... _enums)
	{
		for(IFlagEnum<T> e : _enums)
		{
			value(value() ^ e.value());
		}
	}
}
