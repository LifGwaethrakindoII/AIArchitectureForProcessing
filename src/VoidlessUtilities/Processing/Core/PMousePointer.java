package VoidlessUtilities.Processing.Core;

import VoidlessUtilities.Processing.Renderers.PEllipseRenderer;

public class PMousePointer extends PScriptedBehavior
{
	public final static float DEFAULT_RADIUS = 50.0f;
	public final static float DEFAULT_SPEED = 8.0f;
	
	private float radius;
	private float speed;
	private PEllipseRenderer ellipseRenderer;
	
	public float radius() { return radius; }
	public void radius(float _radius) { radius = _radius; }
	
	public float speed() { return speed; }
	public void speed(float _speed) { speed = _speed; }
	
	public PEllipseRenderer ellipseRenderer()
	{
		if(ellipseRenderer == null) ellipseRenderer = entity().GetComponent(PEllipseRenderer.class);
		return ellipseRenderer;
	}
	
	public PMousePointer()
	{
		radius = DEFAULT_RADIUS;
		speed = DEFAULT_SPEED;
	}
	
	public PMousePointer(float _radius, float _speed)
	{
		radius = _radius;
		speed = _speed;
	}
	
	@Override public void OnAwake()
	{
		if(ellipseRenderer() == null)
		entity().AddComponent(new PEllipseRenderer(0, PSketch.sketch().color(255, 255, 255, 255), radius, radius));
	}
	
	@Override public void OnStart()
	{
		
	}
	
	@Override public void Update()
	{
		float newRadius = PSketch.sin(PTime.elapsedTime() * speed) * radius;
		
		transform().position(PSketch.mousePoint());
		ellipseRenderer().radiusX(newRadius);
		ellipseRenderer().radiusY(newRadius);
	}
}
