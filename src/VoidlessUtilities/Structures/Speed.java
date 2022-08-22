package VoidlessUtilities.Structures;

import VoidlessUtilities.VoidlessMath;

public class Speed
{
	private float currentSpeed;
	private float minSpeed;
	private float maxSpeed;
	private float accelerationTime;
	private float acceleration;
	
	public float currentSpeed() { return currentSpeed; }

	public float minSpeed() { return minSpeed; }
	public void minSpeed(float _minSpeed)
	{
		minSpeed = _minSpeed;
		UpdateAcceleration();
	}
	
	public float maxSpeed() { return maxSpeed; }
	public void maxSpeed(float _maxSpeed)
	{
		maxSpeed = _maxSpeed;
		UpdateAcceleration();
	}
	
	public float accelerationTime() { return accelerationTime; }
	public void accelerationTime(float _accelerationTime)
	{
		accelerationTime = _accelerationTime;
		UpdateAcceleration();
	}
	
	public float acceleration() { return acceleration; }
	
	private void UpdateAcceleration()
	{
		acceleration = (maxSpeed - minSpeed / accelerationTime);
	}
	
	public Speed(float _minSpeed, float _maxSpeed, float _accelerationTime)
	{
		minSpeed = _minSpeed;
		maxSpeed = _maxSpeed;
		accelerationTime = _accelerationTime;
		
		UpdateAcceleration();
	}
	
	public Speed(float _maxSpeed, float _accelerationTime)
	{
		minSpeed = 0.0f;
		maxSpeed = _maxSpeed;
		accelerationTime = _accelerationTime;
		
		UpdateAcceleration();
	}
	
	public float Accelerate()
	{
		return currentSpeed = VoidlessMath.Max((currentSpeed + acceleration), maxSpeed);
	}
	
	public float Deccelerate()
	{
		return currentSpeed = VoidlessMath.Min((currentSpeed - acceleration), minSpeed);
	}
	
	public void Reset()
	{
		currentSpeed = minSpeed;
	}
}
