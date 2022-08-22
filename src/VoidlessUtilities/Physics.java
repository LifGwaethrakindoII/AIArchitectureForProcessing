package VoidlessUtilities;

public class Physics
{
	public final static float GRAVITY_ACCELERATION = 9.81f;
	private static float gravityAcceleration = GRAVITY_ACCELERATION;
	
	public static float gravityAcceleration() { return gravityAcceleration; }
	public static void gravityAcceleration(float _gravityAcceleration) { gravityAcceleration = _gravityAcceleration; }
}
