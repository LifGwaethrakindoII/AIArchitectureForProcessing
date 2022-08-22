package VoidlessUtilities.Processing.AI;

import processing.core.*;
import VoidlessUtilities.*;
import VoidlessUtilities.Processing.Core.PBody;
import VoidlessUtilities.Processing.Core.PDebug;
import VoidlessUtilities.Processing.Core.PSketch;
import VoidlessUtilities.Processing.Structures.PRay;

public class PVehicle extends PBody
{
	private float maxSpeed;
	private float maxForce;
	
	public float maxSpeed() { return maxSpeed; }
	public void maxSpeed(float _maxSpeed) { maxSpeed = _maxSpeed; }
	
	public float maxForce() { return maxForce; }
	public void maxForce(float _maxForce) { maxForce = _maxForce; }

	public PVehicle(float _maxSpeed, float _maxForce)
	{
		super();
		maxSpeed = _maxSpeed;
		maxForce = _maxForce;
	}
	
	public PVector SeekForce(PVector _target)
	{
		PVector desired = _target.sub(entity().transform().position()).limit(maxSpeed);
		PVector steering = desired.sub(velocity());

		return steering.limit(maxForce);
	}
	
	public PVector FleeForce(PVector _target)
	{
		PVector desired = entity().transform().position().sub(_target);
		PVector steering = desired.sub(velocity());
		
		return steering.limit(maxForce);
	}
	
	public float ArriveMultiplier(PVector _target, float _distance)
	{
		PVector desired = _target.sub(entity().transform().position());

		return VoidlessMath.Clamp((desired.magSq() / (_distance * _distance)), 0.0f, 1.0f);
	}
	
	public void RotateTowardsVelocity()
	{
		//float rotation = PSketch.atan2(velocity().y, velocity().x);
		//entity().transform().SetRoll(rotation);
		entity().transform().SetRoll(velocity().heading());
	}
	
	@Override
	public void Update()
	{
		super.Update();
		RotateTowardsVelocity();
	}
	
	@Override
	public void Debug()
	{
		PDebug.DrawRay(new PRay(entity().transform().position(), velocity().mult(maxSpeed)), PSketch.sketch().color(0, 255, 255));
	}
}
