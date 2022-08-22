package VoidlessUtilities.Processing.Structures;

import processing.core.*;

public class PRay
{
	public PVector origin;
	public PVector direction;
	
	public PRay(PVector _origin, PVector _direction)
	{
		origin = _origin;
		direction = _direction;
	}
	
	public PVector Lerp(float t)
	{
		return origin.add(direction).mult(t);
	}
}
