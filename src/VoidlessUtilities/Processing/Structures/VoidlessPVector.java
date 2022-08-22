package VoidlessUtilities.Processing.Structures;

import processing.core.*;
import VoidlessUtilities.*;
import VoidlessUtilities.Processing.Core.PSketch;

public class VoidlessPVector
{
	private static PVector zero;
	private static PVector one;
	private static PVector left;
	private static PVector right;
	private static PVector up;
	private static PVector down;
	private static PVector back;
	private static PVector forward;
	
	public static PVector Zero() { return zero == null ? zero = new PVector(0f, 0f, 0f) : zero.copy(); }
	public static PVector One() { return one == null ? one = new PVector(1f, 1f, 1f) : one.copy(); }
	public static PVector Left() { return left == null ? left = new PVector(-1f, 0f, 0f) : left.copy(); }
	public static PVector Right() { return right == null ? right = new PVector(1f, 0f, 0f) : right.copy(); }
	public static PVector Down() { return down == null ? down = new PVector(0f, -1f, 0f) : down.copy(); }
	public static PVector Up() { return up == null ? up = new PVector(0f, 1f, 0f) : up.copy(); }
	public static PVector Back() { return back == null ? back = new PVector(0f, 0f, -1f) : back.copy(); }
	public static PVector Forward() { return forward == null ? forward = new PVector(0f, 0f, 1f) : forward.copy(); }

	public static PVector DirectionFromTo(PVector a, PVector b)
	{
		return b.copy().sub(a);
	}
	
	public static PVector Lerp(PVector origin, PVector destiny, float t)
	{
		return origin.add(destiny.sub(origin).mult(t));
	}
	
	public static PVector Invert(PVector _vector)
	{
		PVector result = _vector.copy();
		
		result.x = -result.x;
		result.y = -result.y;
		result.z = -result.z;
		
		return result;
	}
	
	public static PVector Scale(PVector a, PVector b)
	{
		return new PVector((a.x * b.x), (a.y * b.y), (a.z * b.z));
	}
	
	public static float Angle(PVector a, PVector b)
	{
		float dot = ((a.x * b.x) + (a.y * b.y) + (a.z * b.z));
	
		return PApplet.acos(dot / (a.mag() * b.mag())) * VoidlessMath.RADIANS_TO_DEGREES;
	}
	
	public static PVector Normalized(PVector v)
	{
		float magnitudeInverse = v.mag() != 1.0f ? 1.0f / v.mag() : 1.0f;
			
		return v.copy().mult(magnitudeInverse);
	}
	
	public static PVector Limit(PVector v, float x)
	{
		return Normalized(v).mult(x);
	}
	
	public static float ScalarProjection(PVector a, PVector b)
	{
		float aMag = a.mag();
		float bMag = b.mag();
		float dot = ((a.x * b.x) + (a.y * b.y) + (a.z * b.z));
		float angle = PApplet.acos(dot / (aMag * bMag)) * VoidlessMath.RADIANS_TO_DEGREES;
		
		return VoidlessMath.Min(aMag, bMag) * PApplet.cos(angle);
	}
	
	public static PVector VectorProjection(PVector a, PVector b)
	{
		float aMag = a.mag();
		float bMag = b.mag();
		float dot = ((a.x * b.x) + (a.y * b.y) + (a.z * b.z));
		float angle = PApplet.acos(dot / (aMag * bMag)) * VoidlessMath.RADIANS_TO_DEGREES;
		PVector max = VoidlessMath.Max(aMag, bMag) == aMag ? a : b;
		
		return max.normalize().mult(VoidlessMath.Min(aMag, bMag) * PApplet.cos(angle));
	}
	
	public static PVector ComponentWiseMultiplication(PVector a, PVector b)
	{
		return new PVector(a.x * b.x, a.y * b.y, a.z * b.z);
	}
	
	public static PVector Random(float minX, float maxX, float minY, float maxY)
	{
		return new PVector(PSketch.sketch().random(minX, maxX), PSketch.sketch().random(minY, maxY));
	}
}
