package VoidlessUtilities.Processing;

import java.util.*;
import VoidlessUtilities.*;
import VoidlessUtilities.ObjectPooling.ObjectPool;
import VoidlessUtilities.Processing.Core.PComponent;
import processing.core.*;

public class PParticleSystem extends PComponent
{
	private ArrayList<PParticle> particles;
	private ObjectPool<PParticle> particlesPool;
	
	public ArrayList<PParticle> particles(){ return particles; }
	
	public PParticleSystem()
	{
		particlesPool = new ObjectPool<PParticle>(new PParticle());
		particles = new ArrayList<PParticle>();
		Reset();
	}
	
	public PParticleSystem(PParticle _particle)
	{
		particlesPool = new ObjectPool<PParticle>(_particle);
		particles = new ArrayList<PParticle>();
		Reset();
	}
	
	@Override public void Update()
	{
		for(PParticle particle : particles)
		{
			particle.Update();
		}
	}
	
	@Override public void Reset()
	{
		if(particles != null) particles.clear();
	}
	
	public PParticle CreateParticleAt(PVector _position)
	{
		PParticle particle = particlesPool.Recycle();
		particle.position(_position);
		particles.add(particle);
		
		return particle;
	}
}
