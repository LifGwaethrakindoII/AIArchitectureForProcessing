package VoidlessUtilities.Processing.Colliders;

import processing.core.*;

public class PRectCollider extends PCollider
{
	private PVector dimensions;
	
	public PVector dimensions() { return dimensions.copy(); }
	public void dimensions(PVector _dimensions) {dimensions = _dimensions; }
	
	public PRectCollider(boolean _isTrigger, PVector _dimensions)
	{
		super(_isTrigger);
		dimensions = _dimensions;
	}

	@Override
	public boolean CollisionDetected(PCollider _collider)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void Update()
	{
		
	}
	@Override
	public int interactableID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float data(int _ID) {
		// TODO Auto-generated method stub
		return 0;
	}
}
