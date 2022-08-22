package VoidlessUtilities.Processing.Interfaces;

import VoidlessUtilities.Enumerators.ContactState;
import VoidlessUtilities.Processing.Colliders.PCollider;

public interface IPTriggerListener
{
	void OnTrigger(PCollider _collider, ContactState _state);
}