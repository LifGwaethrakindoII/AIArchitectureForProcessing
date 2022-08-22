package VoidlessUtilities.Processing.AI;

import VoidlessUtilities.*;
import VoidlessUtilities.Enumerators.SteeringBehavior;
import VoidlessUtilities.Processing.Core.PBody;
import VoidlessUtilities.Processing.Core.PTransform;
import processing.core.*;

public class PSteeringData
{
	private SteeringBehavior behavior;
	private PVector target;
	private float weight;
	private float arrivalRadius;
	
	public SteeringBehavior behavior() { return behavior; }
	public void behavior(SteeringBehavior _behavior) { behavior = _behavior; }
	
	public PVector target() { return target; }
	public void target(PVector _target) { target = _target; }
	
	public float weight() { return weight; }
	public void weight(float _weight) { weight = _weight; }
	
	public float arrivalRadius() { return arrivalRadius; }
	public void arrivalRadius(float _arrivalRadius) { arrivalRadius = _arrivalRadius; }
	
	public PSteeringData()
	{
		weight = 1.0f;
		target = new PVector();
		behavior = SteeringBehavior.Seek;
	}
	
	public PSteeringData(SteeringBehavior _behavior, PVector _target, float _weight)
	{
		behavior = _behavior;
		target = _target;
		weight = _weight;
	}
	
	public PSteeringData(SteeringBehavior _behavior, PVector _target, float _weight, float _arrivalRadius)
	{
		behavior = _behavior;
		target = _target;
		weight = _weight;
		arrivalRadius = _arrivalRadius;
	}
	
	public PSteeringData(SteeringBehavior _behavior, PVector _target)
	{
		behavior = _behavior;
		target = _target;
		weight = 1.0f;
		arrivalRadius = 1.0f;
	}
	
	public PSteeringData(SteeringBehavior _behavior, PBody _body, float _weight)
	{
		behavior = _behavior;
		target =_behavior == SteeringBehavior.Pursuit || _behavior == SteeringBehavior.Evasion ?
		_body.entity().transform().position().add(_body.velocity()) : _body.transform().position();
		weight = _weight;
	}
	
	public PSteeringData(SteeringBehavior _behavior, PBody _body, float _weight, float _arrivalRadius)
	{
		behavior = _behavior;
		target =_behavior == SteeringBehavior.Pursuit || _behavior == SteeringBehavior.Evasion ?
		_body.entity().transform().position().add(_body.velocity()) : _body.transform().position();
		weight = _weight;
		arrivalRadius = _arrivalRadius;
	}
	
	public PSteeringData(SteeringBehavior _behavior, PBody _body)
	{
		behavior = _behavior;
		target =_behavior == SteeringBehavior.Pursuit || _behavior == SteeringBehavior.Evasion ?
		_body.entity().transform().position().add(_body.velocity()) : _body.transform().position();
		weight = 1.0f;
		arrivalRadius = 1.0f;
	}
	
	public PSteeringData(SteeringBehavior _behavior, PTransform _transform, float _weight)
	{
		behavior = _behavior;
		target =_behavior == SteeringBehavior.Pursuit || _behavior == SteeringBehavior.Evasion ?
		_transform.position().add(_transform.deltaPosition()) : _transform.position();
		weight = _weight;
	}
	
	public PSteeringData(SteeringBehavior _behavior, PTransform _transform, float _weight, float _arrivalRadius)
	{
		behavior = _behavior;
		target =_behavior == SteeringBehavior.Pursuit || _behavior == SteeringBehavior.Evasion ?
		_transform.position().add(_transform.deltaPosition()) : _transform.position();
		weight = _weight;
		arrivalRadius = _arrivalRadius;
	}
	
	public PSteeringData(SteeringBehavior _behavior, PTransform _transform)
	{
		behavior = _behavior;
		target =_behavior == SteeringBehavior.Pursuit || _behavior == SteeringBehavior.Evasion ?
		_transform.position().add(_transform.deltaPosition()) : _transform.position();
		weight = 1.0f;
		arrivalRadius = 1.0f;
	}
}
