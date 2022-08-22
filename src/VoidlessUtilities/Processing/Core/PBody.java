package VoidlessUtilities.Processing.Core;

import VoidlessUtilities.*;
import VoidlessUtilities.Enumerators.ContactState;
import VoidlessUtilities.Enumerators.ForceMode;
import VoidlessUtilities.Processing.*;
import VoidlessUtilities.Processing.Colliders.PCollider;
import VoidlessUtilities.Processing.Interfaces.IPCollisionListener;
import VoidlessUtilities.Processing.Structures.NormalizedPVector;
import VoidlessUtilities.Processing.Structures.PRay;
import processing.core.*;

public class PBody extends PComponent implements IPCollisionListener
{
	private NormalizedPVector gravityDirection;
	private PVector lastPosition;
	protected PVector velocity;
	private PVector acceleration;
	private float angularVelocity;
	private float angularAcceleration;
	private float mass;
	private float drag;
	private float angularDrag;
	private boolean useGravity;
	
	@Override
	public void entity(PEntity _entity)
	{
		if(entity() == null)
		{
			super.entity(_entity);
			lastPosition = transform().position();
		}
	}
	
	public NormalizedPVector gravityDirection() { return gravityDirection; }
	public void gravityDirection(NormalizedPVector _gravityDirection) { gravityDirection = _gravityDirection; }
	
	public PVector acceleration() { return acceleration; }
	
	public PVector velocity() { return velocity.copy(); }
	
	public float angularVelocity() { return angularVelocity; }
	
	public float angularAcceleration() { return angularAcceleration; }
	
	public float mass() { return mass; }
	public void mass(float _mass) { mass = _mass; }
	
	public float drag() {return drag; }
	public void drag(float _drag) { drag = _drag; }
	
	public float angularDrag() { return angularDrag; }
	public void angularDrag(float _angularDrag) { angularDrag = _angularDrag; }
	
	public boolean useGravity() { return useGravity; }
	public void useGravity(boolean _useGravity) { useGravity = _useGravity; }
	
	public PBody()
	{
		Reset();
	}
	
	@Override
	public void Update()
	{
		PVector actualPosition = entity().transform().position();
		if(useGravity) AddForce(gravityDirection, ForceMode.Force);
		velocity.add(acceleration);
		transform().position(transform().position().add(velocity));
		//velocity = actualPosition.copy().sub(lastPosition);
		lastPosition = actualPosition;
		acceleration.set(0.0f, 0.0f, 0.0f);
	}
	
	@Override
	public void Reset()
	{
		super.Reset();
		useGravity = false;
		mass = 1.0f;
		velocity = new PVector(0f, 0f, 0f);
		acceleration = new PVector(0f, 0f, 0f);
		gravityDirection = new NormalizedPVector(0f, -1f, 0f);
	}
	
	public void AddForce(PVector _force, ForceMode _mode)
	{	
		switch(_mode)
		{
			case Force:
			acceleration.add(PVector.div(_force, mass).mult(PTime.deltaTime()));
			break;
			
			case Acceleration:
			acceleration.add(_force.mult(PTime.deltaTime()));
			break;
			
			case Impulse:
			acceleration.add(PVector.div(_force, mass));
			break;
			
			case VelocityChange:
			acceleration.add(_force);
			break;
		}
		
	}
	
	public void AddForceAndLimit(PVector _force, float _limit, ForceMode _mode)
	{
		AddForce(_force, _mode);
		acceleration = acceleration.limit(_limit);
	}
	
	public void Limit(float _force)
	{
		velocity.limit(_force);
	}
	
	public void Sleep()
	{
		velocity = velocity.mult(0.0f);
	}
	
	public void RotateTowardsVelocity()
	{
		entity().transform().SetRoll(velocity().heading());
	}

	@Override
	public void OnCollision(PCollider _collider, ContactState _state)
	{
		
	}

	@Override
	public void OnTrigger(PCollider _collider, ContactState _state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void Debug()
	{
		PDebug.DrawRay(new PRay(entity().transform().position(), velocity().mult(25.0f)), PSketch.sketch().color(0, 255, 255));
	}
}
