package VoidlessUtilities.Enumerators;

import VoidlessUtilities.Interfaces.IFlagEnum;

public enum Axes2D implements IFlagEnum<Axes2D>
{
	None(0x00),
	Left(0x01),
	Right(0x02),
	Down(0x04),
	Up(0x08),
	
	LeftAndDown(Left.value() | Down.value()),
	LeftAndUp(Left.value() | Up.value()),
	RightAndDown(Right.value() | Down.value()),
	RightAndUp(Right.value() | Up.value());
	
	private int value;

	private Axes2D(int _value)
	{
		value = _value;
	}

	@Override
	public int value() { return value; }

	@Override
	public void value(int _value) { value = _value; }
}
