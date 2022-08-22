package VoidlessUtilities.Processing.Renderers;

import VoidlessUtilities.Processing.*;
import VoidlessUtilities.Processing.Core.PComponent;
import VoidlessUtilities.Processing.Core.PSketch;
import processing.core.*;

public class PEllipseRenderer extends PRenderer
{
	private float radiusX;
	private float radiusY;
	
	public float radiusX() { return radiusX; }
	public void radiusX(float _radiusX) { radiusX = _radiusX; }
	
	public float radiusY() { return radiusY; }
	public void radiusY(float _radiusY) { radiusY = _radiusY; }
	
	public PEllipseRenderer()
	{
		Reset();
	}
	
	public PEllipseRenderer(int _fillColor, int _strokeColor, float _radiusX, float _radiusY)
	{
		Reset();
		fillColor = _fillColor;
		strokeColor = _strokeColor;
		radiusX = _radiusX;
		radiusY = _radiusY;
	}
	
	public void Reset()
	{
		super.Reset();
		radiusX = 30.0f;
		radiusY = 15.0f;
	}

	@Override
	public void Render()
	{
		PSketch.sketch().fill(fillColor);
		PSketch.sketch().stroke(strokeColor);
		PSketch.sketch().ellipse(entity().transform().position().x, entity().transform().position().y, entity().transform().scale().x * radiusX, entity().transform().scale().y * radiusY);
	}
	
	@Override
	protected void UpdateBounds()
	{
		//bounds.Set(entity().transform().position(), new PVector(entity().transform().scale().x * radiusX, entity().transform().scale().y * radiusY));
	}
	
	public PComponent Reproduce()
	{
		PEllipseRenderer ellipseRenderer = new PEllipseRenderer();
		
		ellipseRenderer.fillColor = fillColor;
		ellipseRenderer.strokeColor = strokeColor;
		ellipseRenderer.radiusX = radiusX;
		ellipseRenderer.radiusY = radiusY;
		
		return ellipseRenderer;
	}
}
