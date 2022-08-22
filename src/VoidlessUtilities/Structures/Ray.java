package VoidlessUtilities.Structures;

public class Ray
{
	public Vector3 origin;
	public Vector3 direction;
	
	public Ray(Vector3 _origin, Vector3 _direction)
	{
		origin = _origin;
		direction = _direction;
	}
	
	public Vector3 Lerp(float t)
	{
		return origin.Add(direction).Times(t);
	}
}
