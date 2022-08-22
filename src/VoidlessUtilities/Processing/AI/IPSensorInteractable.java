package VoidlessUtilities.Processing.AI;

import VoidlessUtilities.Processing.Physics.IPPhysicsInteractable;

public interface IPSensorInteractable<T extends IPPhysicsInteractable> extends IPPhysicsInteractable
{
	boolean HasRegisteredInteration(T _interactable);
}