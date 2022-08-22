package VoidlessUtilities.Processing.Renderers;

import VoidlessUtilities.Processing.Core.PSketch;

public class PCircleRenderer extends PRenderer
{
	private float radius;
	
	public float radius() { return radius; }
	
	public PCircleRenderer()
	{
		Reset();
	}
	
	public PCircleRenderer(int _fillColor, int _strokeColor, float _radius)
	{
		fillColor(_fillColor);
		strokeColor(_strokeColor);
		radius = _radius;
	}

	@Override public void Render()
	{
		PSketch.sketch().fill(fillColor);
		PSketch.sketch().stroke(strokeColor);
		PSketch.DrawCircle(transform().position(), radius);
	}
	
	@Override public void Reset()
	{
		super.Reset();
		fillColor(1);
		strokeColor(1);
		radius = 20.0f;
	}

	@Override protected void UpdateBounds()
	{
		
	}
}
