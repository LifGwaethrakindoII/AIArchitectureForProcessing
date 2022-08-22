package VoidlessUtilities.Processing.Physics;

import processing.core.*;

public interface IPPhysicsInteractable
{
	PVector position();
	void position(PVector _position);
	
	/*PQuaternion rotation();
	void rotation(PQuaternion _rotation);*/
	
	int interactableID();
	
	float data(int _ID);
	
	default boolean Interaction(IPPhysicsInteractable a, IPPhysicsInteractable b)
	{
		return false;
	}
}
