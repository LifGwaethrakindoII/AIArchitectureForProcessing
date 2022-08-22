package VoidlessUtilities.Structures;

public class LayerMask
{
	public static final short LIMIT_MASK = 64;
	
	private long value;
	
	public long value() { return value; }
	
	public LayerMask(short ... _values)
	{
		AddLayers(_values);
	}
	
	public void AddLayer(short _value)
	{
		if(_value <= LIMIT_MASK)
		value |= 1 << _value;
	}
	
	public void AddLayers(short ... _values)
	{
		for(short value : _values)
		{
			AddLayer(value);
		}
	}
	
	public void RemoveLayer(short _value)
	{
		if((value | (1 << _value)) == value) value ^= _value;
	}
	
	public void RemoveLayers(short ... _values)
	{
		for(short value : _values)
		{
			RemoveLayer(value);
		}
	}
	
	public static boolean LayerWithinRange(short _value)
	{
		return (_value >= 0 && _value <= LIMIT_MASK);
	}
	
	public boolean LayerInMask(short _value)
	{
		return (_value <= LIMIT_MASK) ? ((value | (1 << _value)) == value) : false;
	}
}
