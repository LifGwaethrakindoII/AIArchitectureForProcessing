package VoidlessUtilities.Processing.Structures;

import processing.core.*;

public class PQuaternion
{
	private static PQuaternion identity;
	
	private float w;
	private float x;
	private float y;
	private float z;
	
	public static PQuaternion identity() { return identity == null ? identity = new PQuaternion(1f, 0f, 0f, 0f) : identity; }
	
	public float w() { return w; }
	public void w(float _w) { w = _w; }
	
	public float x() { return x; }
	public void x(float _x) { x = _x; }
	
	public float y() { return y; }
	public void y(float _y) { y = _y; }
	
	public float z() { return z; }
	public void z(float _z) { z = _z; }
	
	public PQuaternion(float _w, float _x, float _y, float _z)
	{
		w(_w);
		x(_x);
		y(_y);
		z(_z);
	}
	
	public static PVector Multiply(PQuaternion q, PVector v)
	{
		PVector result = new PVector();
		
		float num = q.x * 2f;
	    float num2 = q.y * 2f;
	    float num3 = q.z * 2f;
	    float num4 = q.x * num;
	    float num5 = q.y * num2;
	    float num6 = q.z * num3;
	    float num7 = q.x * num2;
	    float num8 = q.x * num3;
	    float num9 = q.y * num3;
	    float num10 = q.w * num;
	    float num11 = q.w * num2;
	    float num12 = q.w * num3;
	    result.x = (1f - (num5 + num6)) * v.x + (num7 - num12) * v.y + (num8 + num11) * v.z;
	    result.y = (num7 + num12) * v.x + (1f - (num4 + num6)) * v.y + (num9 - num10) * v.z;
	    result.z = (num8 - num11) * v.x + (num9 + num10) * v.y + (1f - (num4 + num5)) * v.z;
	    
		return result;
	}
	
	public static PVector LookRotation(PVector _direction, PVector _up)
	{
		PVector result = new PVector();

		
		
		return result;
	}
}
