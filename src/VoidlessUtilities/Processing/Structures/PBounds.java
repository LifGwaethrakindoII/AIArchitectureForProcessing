package VoidlessUtilities.Processing.Structures;

import VoidlessUtilities.*;
import VoidlessUtilities.Interfaces.*;
import VoidlessUtilities.ObjectPooling.IReproducible;
import VoidlessUtilities.Processing.Interfaces.*;
import VoidlessUtilities.Processing.Core.*;
import processing.core.*;

public class PBounds implements IPBounds, IResettable, IReproducible<PBounds>, IPDebuggable
{
	private PVector position;
	private PVector min;
	private PVector max;
	private float[] data;
	
	@Override public PVector position() { return position; }
	
	@Override public void position(PVector _position) { position = _position; }
	
	@Override public PVector Min() { return min; }
	
	@Override public PVector Max() { return max; }
	
	@Override public void Min(PVector _min) {	min = _min; }
	
	@Override public void Max(PVector _max) { max = _max; }
	
	/*@Override public PQuaternion rotation() { return null; }
	@Override public void rotation(PQuaternion _rotation) {  }*/
	
	@Override public int interactableID() { return 0; }
	
	@Override public float data(int _ID) { return 0.0f; }
	
	public PBounds()
	{
		Reset();
	}
	
	public PBounds(PVector _position)
	{
		Reset();
		position.set(_position);
	}
	
	public PBounds(PVector _min, PVector _max)
	{
		Reset();
		min.set(_min);
		max.set(_max);
	}
	
	@Override public void Reset()
	{
		data = new float[0];
		max = new PVector();
		min = new PVector();
		position = new PVector();
	}
	
	@Override public PBounds Reproduce()
	{
		PBounds bounds = new PBounds();
		
		return bounds;
	}

	@Override
	public void Debug()
	{
		PSketch.sketch().stroke(PSketch.sketch().color(PColor.White()));
		PSketch.sketch().noFill();
		PSketch.DrawRect(position, VoidlessMath.Abs(min.x - max.x), VoidlessMath.Abs(min.y - max.y));
	}
}