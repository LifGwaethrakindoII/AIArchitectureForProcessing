package VoidlessUtilities.Structures;

import VoidlessUtilities.VoidlessMath;

public class Vector3
{
	public float x;
	public float y;
	public float z;
	
	public static Vector3 Zero() { return new Vector3(0f); }
	
	public static Vector3 One() { return new Vector3(1f); }
	
	public static Vector3 Up() { return new Vector3(0f, 1f, 0f); }
	
	public static Vector3 Down() { return new Vector3(0f, -1f, 0f); }
	
	public static Vector3 Right() { return new Vector3(1f, 0f, 0f); }
	
	public static Vector3 Left() { return new Vector3(-1f, 0f, 0f); }
	
	public static Vector3 Front() { return new Vector3(0f, 0f, 1f); }
	
	public static Vector3 Back() { return new Vector3(0f, 0f, -1f); }
	
	public Vector3(float _value)
	{
		x = _value;
		y = _value;
		z = _value;
	}
	
	public Vector3(float _x, float _y, float _z)
	{
		x = _x;
		y = _y;
		z = _z;
	}
	
	public Vector3 Copy()
	{
		return new Vector3(x, y, z);
	}
	
	public Vector3 Add(Vector3 _vector)
	{
		x += _vector.x;
		y += _vector.y;
		z += _vector.z;
		
		return this;
	}
	
	public Vector3 Subtract(Vector3 _vector)
	{
		x -= _vector.x;
		y -= _vector.y;
		z -= _vector.z;
		
		return this;
	}
	
	public Vector3 Times(float _scalar)
	{
		x *= _scalar;
		y *= _scalar;
		z *= _scalar;
		
		return this;
	}
	
	public Vector3 DividedBy(float _scalar)
	{
		x /= _scalar;
		y /= _scalar;
		z /= _scalar;
		
		return this;
	}
	
	public float Magnitude()
	{
		return VoidlessMath.Pithagoras(x, y, z);
	}
	
	public float SquareMagnitude()
	{
		return VoidlessMath.SquarePithagoras(x, y, z);
	}
	
	public float DistanceBetween(Vector3 _vector)
	{
		return Subtract(_vector).Magnitude();
	}
	
	public float SquareDistanceBetween(Vector3 _vector)
	{
		return Subtract(_vector).SquareMagnitude();
	}
	
	public Vector3 Normalized()
	{
		float distanceInverse = (1.0f / Magnitude());
		
		return new Vector3((x * distanceInverse), (y * distanceInverse), (z * distanceInverse));
	}
	
	public Vector3 Normalize()
	{
		float distanceInverse = (1.0f / Magnitude());
		
		x *= distanceInverse;
		y *= distanceInverse;
		z *= distanceInverse;
		
		return this;
	}
	
	public Vector3 Limited(float _magnitudeLimit)
	{
		return Normalize().Times(_magnitudeLimit);
	}
	
	public Vector3 Limit(float _magnitudeLimit)
	{
		float distanceInverse = ((1.0f / Magnitude()) * _magnitudeLimit);
		
		x *= distanceInverse;
		y *= distanceInverse;
		z *= distanceInverse;
		
		return this;
	}
	
	public static Vector3 Lerp(Vector3 a, Vector3 b, float t)
	{
		t = VoidlessMath.Clamp(t, 0.0f, 1.0f);
		return a.Add(b.Subtract(a).Times(t));
	}
}
