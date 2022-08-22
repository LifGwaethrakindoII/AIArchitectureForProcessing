package VoidlessUtilities.Processing.Core;

import java.util.*;

import VoidlessUtilities.*;
import VoidlessUtilities.Processing.Structures.PQuaternion;
import VoidlessUtilities.Processing.Structures.PRay;
import VoidlessUtilities.Processing.Structures.VoidlessPVector;
import processing.core.*;

public class PTransform extends PComponent implements Iterable<PTransform>
{
	private static final float DEFAULT_NORMAL_LENGTH = 25f;
	
	private transient PTransform parent;
	private ArrayList<PTransform> children;
	private PQuaternion rotation;
	private PVector position;
	private PVector eulerRotation;
	private PVector scale;
	private PVector deltaPosition;
	private PVector lastFramePosition;
	private PVector left;
	private PVector right;
	private PVector down;
	private PVector up;
	private PVector back;
	private PVector forward;
	private float normalProjectionLength;
	
	public ArrayList<PTransform> children() { return children; }
	
	public PTransform parent() { return parent; }
	public void parent(PTransform _parent)
	{
		parent = _parent;
		_parent.AddComponent(this);
	}
	
	public PVector position() { return position.copy(); }
	public void position(PVector _position)
	{
		position = _position;
		LimitOnBoundaries();
	}
	
	public PQuaternion rotation() { return rotation; }
	public void rotation(PQuaternion _rotation) { rotation = _rotation; }
	
	public PVector deltaPosition() { return deltaPosition.copy(); }
	
	public PVector eulerRotation() { return eulerRotation.copy(); }
	public void eulerRotation(PVector _eulerRotation) { eulerRotation = _eulerRotation; }
	
	public PVector scale() { return scale.copy(); }
	public void scale(PVector _scale) { scale = _scale; }
	
	public PVector localPosition() { return parent == null ? position.copy() : position.copy().sub(parent.localPosition()); }
	public void localPosition(PVector _localPosition) { position = parent == null ? _localPosition : _localPosition.sub(parent.localPosition()); }
	
	public PVector localScale() { return parent == null ? scale.copy() : scale.copy().sub(parent.localScale()); }
	public void localScale(PVector _localScale) { scale = parent == null ? _localScale : _localScale.sub(parent.localScale()); }
	
	public PVector left() { return left.copy(); }
	public PVector right() { return right.copy(); }
	public PVector down() { return down.copy(); }
	public PVector up() { return up.copy(); }
	public PVector back() { return back.copy(); }
	public PVector forward() { return forward.copy(); }
	
	public int childCount() { return children.size(); }
	
	public void normalLength(float _normalLength) { normalProjectionLength = _normalLength; }
	
	public PTransform()
	{
		parent = null;
		children = new ArrayList<PTransform>();
		position = new PVector(0f, 0f, 0f);
		deltaPosition = new PVector();
		lastFramePosition = position();
		eulerRotation = new PVector(0f, 0f, 0f);
		rotation = PQuaternion.identity();
		scale = new PVector(1f, 1f, 1f);
		left = new PVector(0f, 0f, 0f);
		right= new PVector(0f, 0f, 0f);
		down = new PVector(0f, 0f, 0f);
		up = new PVector(0f, 0f, 0f);
		back = new PVector(0f, 0f, 0f);
		forward = new PVector(0f, 0f, 0f);
		normalProjectionLength = DEFAULT_NORMAL_LENGTH;
	}
	
	public void AddComponent(PTransform _transform)
	{
		children.add(_transform);
	}
	
	public void AddChildren(PTransform... _children)
	{
		for(PTransform child  : _children)
		{
			child.parent(this);
		}
	}
	
	public void RemoveChild(PTransform _transform)
	{
		children.remove(_transform);
	}
	
	public void RemoveChild(int _index)
	{
		children.remove(_index);
	}
	
	public void RemoveChildren()
	{
		for(int i = 0; i < children.size(); i++)
		{
			children.get(i).parent(null);
			children.remove(i);
		}
	}
	
	@Override
	public void Update()
	{
		deltaPosition = position().sub(lastFramePosition);
		UpdateRotation();
		UpdateOrientationNormals();
		lastFramePosition = position();
		
		LimitOnBoundaries();
	}
	
	@Override
	public void Debug()
	{
		PDebug.DrawRay(new PRay(position, forward().mult(normalProjectionLength)), PSketch.sketch().color(0f, 0f, 255f));
		PDebug.DrawRay(new PRay(position, right().mult(normalProjectionLength)), PSketch.sketch().color(255f, 0f, 0f));
		PDebug.DrawRay(new PRay(position, up().mult(normalProjectionLength)), PSketch.sketch().color(0f, 255f, 0f));
	}
	
	@Override
	public Iterator<PTransform> iterator()
	{
		return children.iterator();
	}
	
	public void Rotate(float _degrees, PVector _axis)
	{
		eulerRotation.add(RelativeDirection(_axis).mult(_degrees));
	}
	
	public void RotatePitch(float _degrees) { eulerRotation.x += (_degrees * VoidlessMath.RADIANS_TO_DEGREES); }
	
	public void RotateYaw(float _degrees) { eulerRotation.y += (_degrees * VoidlessMath.RADIANS_TO_DEGREES); }
	
	public void RotateRoll(float _degrees) { eulerRotation.z += (_degrees * VoidlessMath.RADIANS_TO_DEGREES); }
	
	public void SetPitch(float _degrees) { eulerRotation.x = (_degrees * VoidlessMath.RADIANS_TO_DEGREES); }
	
	public void SetYaw(float _degrees) { eulerRotation.y = (_degrees * VoidlessMath.RADIANS_TO_DEGREES); }
	
	public void SetRoll(float _degrees) { eulerRotation.z = (_degrees * VoidlessMath.RADIANS_TO_DEGREES); }
	
	public PVector RelativeDirection(PVector _axis)
	{
		return PQuaternion.Multiply(rotation, _axis);
	}
	
	public PVector RelativePoint(PVector _point)
	{
		return position().add(PQuaternion.Multiply(rotation, _point));
	}
	
	private void UpdateRotation()
	{
		float cosX = PApplet.cos((eulerRotation.x * VoidlessMath.DEGREES_TO_RADIANS) * 0.5f);
		float cosY = PApplet.cos((eulerRotation.y * VoidlessMath.DEGREES_TO_RADIANS) * 0.5f);
		float cosZ = PApplet.cos((eulerRotation.z * VoidlessMath.DEGREES_TO_RADIANS) * 0.5f);
		float sinX = PApplet.sin((eulerRotation.x * VoidlessMath.DEGREES_TO_RADIANS) * 0.5f);
		float sinY = PApplet.sin((eulerRotation.y * VoidlessMath.DEGREES_TO_RADIANS) * 0.5f);
		float sinZ = PApplet.sin((eulerRotation.z * VoidlessMath.DEGREES_TO_RADIANS) * 0.5f);
		
		rotation.w((cosX * cosY * cosZ) + (sinX * sinY * sinZ));
		rotation.x((sinX * cosY * cosZ) - (cosX * sinY * sinZ));
		rotation.y((cosX * sinY * cosZ) + (sinX * cosY * sinZ));
		rotation.z((cosX * cosY * sinZ) - (sinX * sinY * cosZ));
	}
	
	private void UpdateOrientationNormals()
	{
		right = PQuaternion.Multiply(rotation, VoidlessPVector.Right());
		left = right().mult(-1.0f);
		up = PQuaternion.Multiply(rotation, VoidlessPVector.Up());
		down = up().mult(-1.0f);
		forward = PQuaternion.Multiply(rotation, VoidlessPVector.Forward());
		back = forward().mult(-1.0f);
	}
	
	private void LimitOnBoundaries()
	{
		position.set(
			VoidlessMath.Clamp(position.x, 0.0f, PSketch.sketch().width),
			VoidlessMath.Clamp(position.y, 0.0f, PSketch.sketch().height));
	}
	
	@Override
	public PComponent Reproduce()
	{
		PTransform transform = new PTransform();
		
		transform.position(position());
		transform.rotation(rotation());
		transform.eulerRotation(eulerRotation());
		transform.scale(scale());
		transform.normalLength(normalProjectionLength);
		
		for(PTransform child : children)
		{
			transform.children().add((PTransform)child.Reproduce());
		}
				
		return transform;
	}
	
	public void CopyTransform(PTransform _transform)
	{
		position(_transform.position());
		rotation(_transform.rotation());
		eulerRotation(_transform.eulerRotation());
		scale(_transform.scale());
	}
}
