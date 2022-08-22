package VoidlessUtilities.Processing.Enumerators;

import VoidlessUtilities.*;
import VoidlessUtilities.Interfaces.IFlagEnum;

public enum PColorFormat implements IFlagEnum<PColorFormat>
{
	Grayscale(0x01),
	RGB(0x02),
	Alpha(0x03),
	GrayscaleAlpha(Grayscale.value | Alpha.value),
	RGBA(RGB.value | Alpha.value);
	
	private int value;
	
	private PColorFormat(int _value)
	{
		value = _value;
	}

	@Override
	public int value() { return value; }

	@Override
	public void value(int _value) { value = _value; }
}