package VoidlessUtilities.Processing.Core;

import processing.core.*;

public class PTime
{
	public final float MILLISECONDS_TO_SECONDS = (1.0f / 1000.0f);
	
	private static float previousMillis;
	private static float deltaTime;
	private static float elapsedTime;
	
	public static float deltaTime() { return deltaTime; }
	
	public static float elapsedTime() { return elapsedTime; }
	
	public PTime()
	{
		previousMillis = PSketch.sketch().millis();
		deltaTime = (1.0f / PSketch.sketch().frameRate);
	}
	
	public void Update()
	{
		float currentMillis = PSketch.sketch().millis();
		
		deltaTime = (currentMillis - previousMillis) * MILLISECONDS_TO_SECONDS;
		elapsedTime = (currentMillis * MILLISECONDS_TO_SECONDS);
		previousMillis = currentMillis;
	}
}
