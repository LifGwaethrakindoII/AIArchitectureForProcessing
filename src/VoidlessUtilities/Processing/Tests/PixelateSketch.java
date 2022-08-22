package VoidlessUtilities.Processing.Tests;
import VoidlessUtilities.Processing.*;
import VoidlessUtilities.Processing.AI.PSteeringVehicle;
import VoidlessUtilities.Processing.Colliders.PSphereCollider;
import VoidlessUtilities.Processing.Core.PBody;
import VoidlessUtilities.Processing.Core.PDebug;
import VoidlessUtilities.Processing.Core.PEntity;
import VoidlessUtilities.Processing.Core.PSketch;
import VoidlessUtilities.Processing.Renderers.PTrailRenderer;
import VoidlessUtilities.Processing.Renderers.PTriangleRenderer;
import processing.opengl.PShader;

public class PixelateSketch extends PSketch
{	
	private PShader shader;
	
	protected void Initialize()
	{
		try
		{
			shader = loadShader("D:\\My Documents\\Processing\\Shaders\\Pixelate.frag");
		}
		catch(Exception e)
		{
			print("Error catched: " + e);
		}
		
		
		if(shader == null) return;
		
		shader.set("resolution", (width), (height));
		shader(shader);
	}
	
	public static void main(String[] args)
	{
		sketch(new PixelateSketch());
		runSketch(name(), sketch());
	}
	
	public void Update()
	{
		noStroke();
		fill(0);
		
		if(shader == null) return;
		resetShader();
	}
}
