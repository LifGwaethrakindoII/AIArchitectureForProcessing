package VoidlessUtilities.Enumerators;

import VoidlessUtilities.Interfaces.IFlagEnum;

public enum ForceMode implements IFlagEnum<ForceMode>
{
	Force(0x01),
	Acceleration(0x02),
	Impulse(0x04),
	VelocityChange(0x08);
	
	private int value;

	private ForceMode(int _value)
	{
		value = _value;
	}

	@Override
	public int value() { return value; }

	@Override
	public void value(int _value) { value = _value; }
}
