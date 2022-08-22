package VoidlessUtilities.Processing.AI;

import VoidlessUtilities.*;
import VoidlessUtilities.Collections.StackQueue;
import VoidlessUtilities.Processing.Core.PBody;
import VoidlessUtilities.Processing.Core.PComponent;
import VoidlessUtilities.Processing.Core.PDebug;
import VoidlessUtilities.Processing.Core.PSketch;
import VoidlessUtilities.Processing.Structures.PRay;
import VoidlessUtilities.Processing.Structures.VoidlessPVector;
import processing.core.*;

public class PSteeringVehicle extends PComponent
{
	private StackQueue<PSteeringData> steeringQueue;
	private PBody body;
	private PVector desiredForce;
	private float maxSpeed;
	private float maxForce;
	
	public StackQueue<PSteeringData> steeringQueue() { return steeringQueue; }
	
	public PBody body()
	{
		if(body == null) body = entity().GetComponent(PBody.class);
		return body;
	}
	
	public PVector desiredForce() { return desiredForce; }
	
	public PSteeringVehicle()
	{
		desiredForce = new PVector();
		steeringQueue = new StackQueue<PSteeringData>();
	}
	
	public PSteeringVehicle(float _maxSpeed, float _maxForce)
	{
		desiredForce = new PVector();
		steeringQueue = new StackQueue<PSteeringData>();
		maxSpeed = _maxSpeed;
		maxForce = _maxForce;
	}
	
	public void Update()
	{
		float queueCount = steeringQueue.count();
		
		if(queueCount > 0.0f)
		{
			PVector force = new PVector();
			PVector additionalForce = new PVector();
			PSteeringData data = steeringQueue.Dequeue();
			PVector accumulatedTarget = new PVector();
			float arrivalScale = 0.0f;
			float inverseCount = 1.0f / queueCount;
			
			while(data != null)
			{
				switch(data.behavior())
				{
					case Seek: additionalForce = SeekForce(data.target()); break;
					case Flee: additionalForce = FleeForce(data.target()); break;
					case Pursuit: additionalForce = SeekForce(data.target()); break;
					case Evasion: additionalForce = FleeForce(data.target()); break;
					default: additionalForce.set(0.0f, 0.0f, 0.0f); break;
				}
				
				accumulatedTarget.add(data.target());
				force.add(additionalForce.mult(data.weight()));
				arrivalScale += ArrivalScalar(data.target(), data.arrivalRadius());
				data = steeringQueue.Dequeue();
			}

			desiredForce = force;
			desiredForce.limit(maxSpeed);
			body().Limit(maxSpeed * arrivalScale * inverseCount);
			desiredForce.mult(arrivalScale * inverseCount);
			
			//System.out.println("Scale: " + (arrivalScale * inverseCount));
		}
		else desiredForce.set(0.0f, 0.0f, 0.0f);
	}
	
	public void AddDesiredForce(PSteeringData _data)
	{
		steeringQueue.Enqueue(_data);
	}
	
	public void Limit()
	{
		
	}
	
	private PVector SeekForce(PVector _target)
	{
		PVector desired = VoidlessPVector.DirectionFromTo(transform().position(), _target).limit(maxSpeed);
		PVector force = VoidlessPVector.DirectionFromTo(body().velocity(), desired);
		//PDebug.DrawLine(transform().position(), _target, PSketch.sketch().color(255, 255, 255));
		//PDebug.DrawRay(new PRay(transform().position(), VoidlessPVector.DirectionFromTo(transform().position(), _target)), PSketch.sketch().color(255, 0, 255));
		
		/*if(force.magSq() > maxForce * maxForce)*/ force.limit(maxForce);
		
		PDebug.DrawRay(new PRay(_target, force.mult(15.0f)), PSketch.sketch().color(255,0,0));
		
		return force;
	}
	
	private PVector FleeForce(PVector _target)
	{
		PVector desired = VoidlessPVector.DirectionFromTo(_target, transform().position()).limit(maxSpeed);
		PVector force = VoidlessPVector.DirectionFromTo(body().velocity(), desired).limit(maxForce);
		
		return force;
	}
	
	private float ArrivalScalar(PVector _target, float _radius)
	{
		float distance = VoidlessPVector.DirectionFromTo(_target.add(body().velocity()), transform().position()).magSq();
		_radius *= _radius;
		
		return VoidlessMath.Clamp(distance / _radius, 0.0f, 1.0f);
	}
}
