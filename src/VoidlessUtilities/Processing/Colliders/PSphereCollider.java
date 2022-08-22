package VoidlessUtilities.Processing.Colliders;

import processing.core.*;
import VoidlessUtilities.*;
import VoidlessUtilities.Processing.Core.*;
import VoidlessUtilities.Processing.Physics.*;

public class PSphereCollider extends PCollider implements IPSphereInteractable
{
	public static final int ID_RADIUS = 0;
	
	private float radius;
	
	@Override public float radius() { return radius; }
	
	@Override public void radius(float _radius) { radius = _radius; }
	
	@Override public int interactableID() { return PPhysics.INTERACTABLE_ID_SPHERE; }
	
	@Override public float data(int _ID) { return (_ID == ID_RADIUS) ? radius : 0.0f; }

	public PSphereCollider(boolean _isTrigger)
	{
		super(_isTrigger);
		Reset();	
	}
	
	public PSphereCollider(boolean _isTrigger, float _radius)
	{
		super(_isTrigger);
		Reset();
		radius = _radius;
	}

	@Override public boolean CollisionDetected(PCollider _collider)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean SphereIntersection(PSphereCollider _sphere)
	{
		PVector direction = transform().position().add(position()).sub(_sphere.transform().position().add(_sphere.position()));
		float directionMagnitude = direction.magSq();
		float radiusSum = (radius() + _sphere.radius());
		float radiusDifference = VoidlessMath.Abs(radius() - _sphere.radius());
		
		return (directionMagnitude < (radiusSum * radiusSum) || directionMagnitude > (radiusDifference * radiusDifference));
	}
	
	@Override public void Reset()
	{
		super.Reset();
		radius = 10.0f;
	}
	
	@Override public void Debug()
	{
		super.Debug();
		PSketch.sketch().stroke(HasInteraction() ? PColor.Red() : PColor.Green());
		PSketch.DrawCircle(position(), radius());
	}
	
	@Override protected void UpdateBounds()
	{
		bounds().Min(new PVector(-radius() * 0.5f, -radius() * 0.5f));
		bounds().Max(new PVector(radius() * 0.5f, radius() * 0.5f));
		bounds().position(position().sub(bounds().Max()));
	}
}