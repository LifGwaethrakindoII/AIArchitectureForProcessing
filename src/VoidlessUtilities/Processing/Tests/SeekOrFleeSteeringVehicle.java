package VoidlessUtilities.Processing.Tests;

import VoidlessUtilities.Processing.*;
import VoidlessUtilities.Processing.AI.PSteeringData;
import VoidlessUtilities.Processing.AI.PSteeringVehicle;
import VoidlessUtilities.Processing.Core.PScriptedBehavior;
import VoidlessUtilities.Processing.Core.PSketch;
import VoidlessUtilities.*;
import VoidlessUtilities.Enumerators.ForceMode;
import VoidlessUtilities.Enumerators.SteeringBehavior;
import processing.core.*;

public class SeekOrFleeSteeringVehicle extends PScriptedBehavior
{
	public static final char KEY_TOGGLE_BEHAVIOR = 't';
	public static final char KEY_SEEK = 's';
	public static final char KEY_FLEE = 'f';
	
	private PSteeringVehicle steeringVehicle;
	private SteeringBehavior behavior;
	private PSteeringData steeringData;
	
	public PSteeringVehicle steeringVehicle()
	{
		if(steeringVehicle == null)
		{
			steeringVehicle = entity().GetComponent(PSteeringVehicle.class);
			if(steeringVehicle == null)
			{
				entity().AddComponent(new PSteeringVehicle());
				steeringVehicle = entity().GetComponent(PSteeringVehicle.class);
			}		
		}
		
		return steeringVehicle;
	}
	
	public SeekOrFleeSteeringVehicle()
	{
		System.out.println("Constructor");
		steeringData = new PSteeringData();
		behavior = SteeringBehavior.Seek;
	}
	
	public void OnAwake()
	{
		System.out.println("Awake");
		steeringData = new PSteeringData();
		steeringData.arrivalRadius(50.0f);
		behavior = SteeringBehavior.Seek;
		
		//PSketch.RegisterLogEntry("Steering_Vehicle", "Estoy bien machin.");
	}
	
	public void Update()
	{
		/*if(PSketch.KeyReleased(KEY_TOGGLE_BEHAVIOR))
		switch(behavior)
		{
			case Seek: behavior = SteeringBehavior.Flee; break;
			case Flee: behavior = SteeringBehavior.Seek; break;
		}*/
		
		if(PSketch.KeyPressed(KEY_SEEK)) behavior = SteeringBehavior.Seek;
		if(PSketch.KeyPressed(KEY_FLEE)) behavior = SteeringBehavior.Flee;
		
		steeringData.target(PSketch.mousePoint());
		steeringData.behavior(behavior);
		steeringVehicle().AddDesiredForce(steeringData);
		steeringVehicle().body().AddForce(steeringVehicle.desiredForce(), ForceMode.Acceleration);
		steeringVehicle().body().RotateTowardsVelocity();
		//PSketch.RegisterLogEntry("Steering_Vehicle", "Ose Perro.");
	}
}
