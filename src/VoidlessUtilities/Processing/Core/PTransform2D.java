package VoidlessUtilities.Processing.Core;

import java.util.*;
import processing.core.*;
import VoidlessUtilities.*;
import VoidlessUtilities.Processing.Interfaces.IPComponent;

public class PTransform2D extends PComponent implements IPComponent, Iterable<PTransform2D>
{
	private ArrayList<PTransform2D> children;
	private PTransform2D parent;
	private PVector right;
	private PVector forward;
	private float rotation;
	
	private ArrayList<PTransform2D> children() { return children; }
	
	public PTransform2D parent() { return parent; }
	public void parent(PTransform2D _parent)
	{
		parent = _parent;
		_parent.children().add(this);
	}
	
	public PVector right() { return right; }
	
	public PVector forward() { return forward; }
	
	public float rotation() { return rotation; }
	
	public int childCount() { return children.size(); }
	
	public PTransform2D()
	{
		children = new ArrayList<PTransform2D>();
		parent = null;
		rotation = 0.0f;
	}
	
	@Override
	public void Update()
	{
		CalculateNormals();
	}
	
	public PVector Direction(PVector _direction)
	{
		return _direction;
	}
	
	public PTransform2D GetChild(int  _index)
	{
		return _index < children.size() ? children.get(_index) : null;
	}
	
	public void RemoveChild(int _index)
	{
		if(_index < children.size()) children.remove(_index);
	}

	@Override
	public Iterator<PTransform2D> iterator()
	{
		return children.iterator();
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		return builder.toString();
	}
	
	private void CalculateNormals()
	{
		forward = new PVector
		(
			(float)Math.cos(rotation * VoidlessMath.DEGREES_TO_RADIANS),
			(float)Math.sin(rotation * VoidlessMath.DEGREES_TO_RADIANS)
		);
		right = forward.cross(new PVector(0f, 0f, 1f));
	}

}
