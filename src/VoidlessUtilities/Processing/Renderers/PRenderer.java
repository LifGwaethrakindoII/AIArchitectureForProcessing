package VoidlessUtilities.Processing.Renderers;

import VoidlessUtilities.Processing.*;
import VoidlessUtilities.Processing.Core.PComponent;
import VoidlessUtilities.Processing.Structures.PBounds;
import processing.core.*;

public abstract class PRenderer extends PComponent
{
	protected PBounds bounds;
	protected int fillColor;
	protected int strokeColor;
	
	public PBounds bounds() { return bounds; }
	
	public int strokeColor() { return strokeColor; }
	public void strokeColor(int _strokeColor) { strokeColor = _strokeColor; }
	
	public int fillColor() { return fillColor; }
	public void fillColor(int _fillColor) { fillColor = _fillColor; }
	
	public PRenderer()
	{
		Reset();
	}
	
	public void Update()
	{
		Render();
		UpdateBounds();
	}
	
	@Override public void Reset()
	{
		bounds = new PBounds();
		fillColor = 255;
		strokeColor = 255;
	}
	
	protected abstract void UpdateBounds();
	
	public abstract void Render();
}
