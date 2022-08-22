package VoidlessUtilities.Processing.Iterators;

import java.util.Iterator;

import VoidlessUtilities.Interfaces.IResettableIterator;
import VoidlessUtilities.Processing.Core.PTime;
import processing.core.*;

public class PWaitSecondsIterator implements IResettableIterator<Float>
{
	private float currentTime;
	private float waitDuration;
	
	public float currentTime() { return currentTime; }
	
	public float waitDuration() { return waitDuration; }
	public void waitDuration(float _waitDuration) { waitDuration = _waitDuration; }

	public PWaitSecondsIterator(float _waitDuration)
	{
		Reset();
		waitDuration = _waitDuration;
	}
	
	@Override public boolean hasNext()
	{
		return currentTime < waitDuration;
	}

	@Override public Float next()
	{
		return currentTime += PTime.deltaTime();
	}

	@Override public void Reset()
	{
		currentTime = 0.0f;
	}

}
