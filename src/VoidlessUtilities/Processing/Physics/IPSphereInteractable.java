package VoidlessUtilities.Processing.Physics;

import VoidlessUtilities.Processing.Core.PPhysics;

public interface IPSphereInteractable extends IPPhysicsInteractable
{
	default float radius(){ return data(PPhysics.ID_RADIUS); }
	void radius(float _radius);
}
