package VoidlessUtilities.Enumerators;

import VoidlessUtilities.Interfaces.IFlagEnum;

public enum Axes3D implements IFlagEnum<Axes3D>
{
	None(0x00),
	Left(0x01),
	Right(0x02),
	Down(0x04),
	Up(0x08),
	Back(0x16),
	Forward(0x32),
	
	LeftAndDown(Left.value() | Down.value()),
	LeftAndUp(Left.value() | Up.value()),
	LeftAndBack(Left.value() | Back.value()),
	LeftAndForward(Left.value() | Forward.value()),
	LeftDownAndBack(Left.value() | Down.value() | Back.value()),
	LeftDownAndForward(Left.value() | Down.value() | Forward.value()),
	LeftUpAndBack(Left.value() | Up.value() | Back.value()),
	LeftUpAndForward(Left.value() | Up.value() | Forward.value()),
	RightAndDown(Right.value() | Down.value()),
	RightAndUp(Right.value() | Up.value()),
	RightAndBack(Right.value() | Back.value()),
	RightAndForward(Right.value() | Forward.value()),
	RightDownAndBack(Right.value() | Down.value() | Back.value()),
	RightDownAndForward(Right.value() | Down.value() | Forward.value()),
	RightUpAndBack(Right.value() | Up.value() | Back.value()),
	RightUpAndForward(Right.value() | Up.value() | Forward.value()),
	DownAndBack(Down.value() | Back.value()),
	DownAndForward(Down.value() | Forward.value()),
	UpAndBack(Up.value() | Back.value()),
	UpAndForward(Up.value() | Forward.value());
	
	private int value;

	private Axes3D(int _value)
	{
		value = _value;
	}

	@Override
	public int value() { return value; }

	@Override
	public void value(int _value) { value = _value; }
}