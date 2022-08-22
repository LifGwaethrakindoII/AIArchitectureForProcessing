package VoidlessUtilities.Processing.AI;

import processing.core.*;
import java.util.concurrent.*;

import VoidlessUtilities.Processing.Colliders.PCollider;
import VoidlessUtilities.Processing.Core.PComponent;
import VoidlessUtilities.Processing.Physics.IPPhysicsInteractable;

public abstract class PSensor extends PComponent implements IPPhysicsInteractable
{
	private CopyOnWriteArrayList<PCollider> perceivedColliders;
	private PVector position;
	
	@Override public PVector position() { return position.copy(); }

	@Override public void position(PVector _position) { position.set(_position); }

	@Override public abstract int interactableID();

	@Override public abstract float data(int _ID);
	
	public PSensor()
	{
		Reset();
	}
	
	public void AddCollider(PCollider _collider)
	{
		perceivedColliders.add(_collider);
	}
	
	public void RemoveCollider(PCollider _collider)
	{
		perceivedColliders.remove(_collider);
	}
	
	@Override public void Reset()
	{
		super.Reset();
		perceivedColliders = new CopyOnWriteArrayList<PCollider>();
	}
}
