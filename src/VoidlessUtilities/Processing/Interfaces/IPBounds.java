package VoidlessUtilities.Processing.Interfaces;

import VoidlessUtilities.Processing.Physics.IPPhysicsInteractable;
import processing.core.*;
import processing.core.*;

public interface IPBounds extends IPPhysicsInteractable
{
	PVector Min();
	void Min(PVector _min);
	
	PVector Max();
	void Max(PVector _max);
}