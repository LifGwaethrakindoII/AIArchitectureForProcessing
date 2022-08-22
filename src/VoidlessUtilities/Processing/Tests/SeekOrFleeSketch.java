package VoidlessUtilities.Processing.Tests;

import VoidlessUtilities.Processing.*;
import VoidlessUtilities.Processing.AI.PSteeringVehicle;
import VoidlessUtilities.Processing.Colliders.PSphereCollider;
import VoidlessUtilities.Processing.Core.PBody;
import VoidlessUtilities.Processing.Core.PEntity;
import VoidlessUtilities.Processing.Core.PSketch;
import VoidlessUtilities.Processing.Renderers.PTrailRenderer;
import VoidlessUtilities.Processing.Renderers.PTriangleRenderer;

public class SeekOrFleeSketch extends PSketch
{	
	protected void Initialize()
	{
		sketchName("SeekOrFleeSketch");
		super.Initialize();
		entities().add(new PEntity
		(
			new PBody(),
			new PSteeringVehicle(10f, 5f),
			new PTriangleRenderer(sketch().color(0, 255, 255), sketch().color(0, 255, 255), 40.0f, 60.0f),
			new SeekOrFleeSteeringVehicle(),
			new PParticleSystem(),
			new PTrailRenderer(),
			new PSphereCollider(true, 50.0f)
		));
		
		entities().get(0).AddComponent(new PSphereCollider(false));
	}
	
	public static void main(String[] args)
	{
		sketch(new SeekOrFleeSketch());
		runSketch(name(), sketch());
	}
}
