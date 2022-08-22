package VoidlessUtilities.Processing.Interfaces;

import VoidlessUtilities.Enumerators.ContactState;
import VoidlessUtilities.Processing.Colliders.PCollider;

public interface IPCollisionListener
{
	void OnCollision(PCollider _collider, ContactState _state);
	
	void OnTrigger(PCollider _collider, ContactState _state);
}