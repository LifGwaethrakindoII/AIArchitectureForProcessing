package VoidlessUtilities.Processing.Renderers;

import VoidlessUtilities.Processing.PParticle;
import VoidlessUtilities.Processing.PParticleSystem;
import VoidlessUtilities.Processing.Core.PDebug;
import VoidlessUtilities.Processing.Core.PSketch;
import VoidlessUtilities.Processing.Iterators.PWaitSecondsIterator;
import processing.core.*;

public class PTrailRenderer extends PRenderer
{
	private float minWait;
	private float maxWait;
	private int strokeWidth;
	private PParticleSystem particleSystem;
	private PWaitSecondsIterator waitIterator;

	public PParticleSystem particleSystem()
	{
		if(particleSystem == null)
		{
			particleSystem = entity().GetComponent(PParticleSystem.class);
			if(particleSystem == null)
			{
				entity().AddComponent(new PParticleSystem());
				particleSystem = entity().GetComponent(PParticleSystem.class);
			}
		}
		
		return particleSystem;
	}
	
	public int strokeWidth() { return strokeWidth; }
	public void strokeWidth(int _strokeWidth) { strokeWidth = _strokeWidth; }
	
	public PTrailRenderer()
	{
		Reset();
	}
	
	public PTrailRenderer(int _strokeColor, int _fillColor, int _strokeWidth)
	{
		Reset();
		strokeColor = _strokeColor;
		fillColor(_fillColor);
		strokeWidth = _strokeWidth;
	}
	
	@Override protected void UpdateBounds() {
		// TODO Auto-generated method stub	
	}

	@Override public void Render()
	{
		if(waitIterator.hasNext()) waitIterator.next();
		else
		{
			waitIterator.Reset();
			waitIterator.waitDuration(PSketch.sketch().random(minWait, maxWait));
			particleSystem().CreateParticleAt(entity().transform().position());
		}
		
		RenderTrail();
	}
	
	private void RenderTrail()
	{
		PSketch.sketch().fill(fillColor);
		PSketch.sketch().stroke(strokeColor);
		PSketch.sketch().strokeWeight(strokeWidth);
		
		PVector a = null;
		PVector b = null;
		boolean turnA = true;
		
		for(PParticle particle : particleSystem().particles())
		{
			if(particle.active())
			{
				if(turnA) a = particle.position();
				else b = particle.position();
				turnA = !turnA;
			}
			
			if(a != null && b != null) PDebug.DrawLine(a, b, strokeColor, strokeWidth);
		}
	}
	
	@Override public void Reset()
	{
		super.Reset();
		minWait = 0.35f;
		maxWait = 0.5f;
		strokeWidth = 10;
		waitIterator = new PWaitSecondsIterator(PSketch.sketch().random(minWait, maxWait));
		strokeWidth(1);
	}
}
