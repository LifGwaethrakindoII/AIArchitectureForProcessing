package VoidlessUtilities.Processing.Renderers;

import VoidlessUtilities.Processing.Core.PSketch;
import VoidlessUtilities.Processing.Structures.VoidlessPVector;
import processing.core.*;

public class PTriangleRenderer extends PRenderer
{
	private float base;
	private float height;
	
	public float base() { return base; }
	public void base(float _base) { base = _base; }
	
	public float height() { return height; }
	public void height(float _height) { height = _height; }
	
	public PTriangleRenderer(int _fillColor, int _stroke, float _base, float _height)
	{
		fillColor = _fillColor;
		strokeColor = _stroke;
		base = _base;
		height = _height;
	}

	@Override
	public void Render()
	{
		/// Note: Establish Right as the Forward Vector.
		PVector p = transform().position();
		PVector relativeLeft = transform().RelativeDirection(VoidlessPVector.Right()).mult(height);
		PVector relativeRight = transform().RelativeDirection(VoidlessPVector.Down()).mult(base * 0.5f);
		PVector relativeUp = transform().RelativeDirection(VoidlessPVector.Up()).mult(base * 0.5f);
		
		PSketch.sketch().fill(fillColor);
		PSketch.sketch().stroke(strokeColor);
		PSketch.sketch().triangle
		(
			p.x + relativeLeft.x,
			p.y + relativeLeft.y,
			p.x + relativeRight.x,
			p.y + relativeRight.y,
			p.x + relativeUp.x,
			p.y + relativeUp.y
		);
	}
	@Override
	protected void UpdateBounds()
	{
		// TODO Auto-generated method stub
		
	}

}
