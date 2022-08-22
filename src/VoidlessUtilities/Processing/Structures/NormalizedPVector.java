package VoidlessUtilities.Processing.Structures;

import VoidlessUtilities.VoidlessMath;
import processing.core.*;

public class NormalizedPVector extends PVector
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NormalizedPVector(float _x, float _y, float _z)
	{
		x = VoidlessMath.Clamp(_x, 0.0f, 1.0f);
		y = VoidlessMath.Clamp(_y, 0.0f, 1.0f);
		z = VoidlessMath.Clamp(_z, 0.0f, 1.0f);
	}
}
