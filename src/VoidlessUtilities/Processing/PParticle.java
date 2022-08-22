package VoidlessUtilities.Processing;

import VoidlessUtilities.*;
import VoidlessUtilities.Events.*;
import VoidlessUtilities.Interfaces.IResettable;
import VoidlessUtilities.ObjectPooling.IPoolObject;
import VoidlessUtilities.ObjectPooling.IReproducible;
import VoidlessUtilities.Processing.Core.PSketch;
import VoidlessUtilities.Processing.Iterators.PWaitSecondsIterator;
import processing.core.*;

public class PParticle implements IReproducible<PParticle>, IPoolObject, IResettable
{
	private IPoolObjectEventEmitter emitter;
	private PWaitSecondsIterator waitIterator;
	private PVector position;
	private int strokeColor;
	private int fillColor;
	private float radius;
	private float currentLifeTime;
	private float lifeTimeLimit;
	private boolean active;
	
	public IPoolObjectEventEmitter emitter() { return emitter != null ? emitter = new PoolObjectEventEmitter() : emitter; }
	
	public PVector position() { return position; }
	public void position(PVector _position) { position = _position; }
	
	public int strokeColor() { return strokeColor; }
	public void strokeColor(int _strokeColor) { strokeColor = _strokeColor; }
	
	public int fillColor() { return fillColor; }
	public void fillColor(int _fillColor) { fillColor = _fillColor; }
	
	public float radius() { return radius; }
	public void radius(float _radius) { radius = _radius; }
	
	public float currentLifeTime() { return currentLifeTime; }
	
	public float lifeTimeLimit() { return lifeTimeLimit; }
	public void lifeTimeLimit(float _lifeTimeLimit) { lifeTimeLimit = _lifeTimeLimit; }
	
	@Override public boolean active() { return active; }

	@Override public void active(boolean _active) { active = _active; }
	
	public PParticle()
	{
		emitter = new PoolObjectEventEmitter();
		position = new PVector();
		Reset();
	}

	public void Update()
	{
		if(active)
		{
			PSketch.sketch().fill(fillColor);
			PSketch.sketch().stroke(strokeColor);
			PSketch.DrawCircle(position, radius);
			
			if(waitIterator.hasNext()) waitIterator.next();
			else OnDeactivation();
		}
	}
	
	@Override
	public PParticle Reproduce()
	{
		PParticle particle = new PParticle();
		
		particle.position(position);
		particle.strokeColor(strokeColor);
		particle.fillColor(fillColor);
		particle.radius(radius);
		particle.lifeTimeLimit(lifeTimeLimit);
		
		return particle;
	}

	@Override
	public void OnCreation()
	{
		Reset();
		active(false);
	}

	@Override
	public void OnRecycle()
	{
		Reset();
		active(true);
	}

	@Override
	public void OnDeactivation()
	{
		active(false);
		emitter.Emit(this);
	}

	@Override
	public void OnDestruction()
	{
		active(false);
		emitter.Emit(this);
	}

	@Override
	public void Reset()
	{	
		strokeColor = 255;
		fillColor = 255;
		radius = 1.0f;
		lifeTimeLimit = 20.0f;
		currentLifeTime = lifeTimeLimit;
		waitIterator = new PWaitSecondsIterator(lifeTimeLimit);
	}	
}
