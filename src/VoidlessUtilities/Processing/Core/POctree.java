package VoidlessUtilities.Processing.Core;

import java.util.concurrent.*;
import processing.core.*;
import VoidlessUtilities.Processing.Interfaces.*;
import VoidlessUtilities.Processing.Physics.*;
import VoidlessUtilities.Processing.Structures.*;
import VoidlessUtilities.Interfaces.*;
import VoidlessUtilities.VoidlessMath;
import VoidlessUtilities.Collections.*;

public class POctree implements IResettable, IPDebuggable
{
	public static final int SIZE_CHILDREN = 8;
	public static final int DEFAULT_LIFE_SPAN = 8;
	public static final int DEFAULT_INITIAL_LIFE = -1;
	public static final float DEFAULT_ENCLOSING_REGION = 10.0f;
	public static final float DEFAULT_MIN_REGION = 1.0f;
	
	private CopyOnWriteArrayList<IPPhysicsInteractable> objects;
	private StackQueue<IPPhysicsInteractable> pendingInsertions;
	private POctree[] childNodes;
	private POctree parent;
	private PBounds bounds;
	private int lifeSpan; 		/// How many frames will the Octree wait before deleting an empty Tree branch.
	private int currentLife; 	/// Current Wait frame the Octree is [-1 by default]
	private byte activeNodes; 	/// Bit-flag that says which Nodes are active.
	private boolean treeBuilt;
	private boolean treeReady; 	/// Is the tree ready?
	
	public CopyOnWriteArrayList<IPPhysicsInteractable> objects() { return objects; }
	
	public POctree[] childNodes() { return childNodes; }
	
	public POctree parent() { return parent; }
	
	public PBounds bounds() { return bounds; }
	
	public byte activeNodes() { return activeNodes; }
	
	public POctree()
	{
		Reset();
	}
	
	public POctree(PBounds _bounds)
	{
		Reset();
		bounds = _bounds;
	}
	
	private void UpdateTree()
	{
		IPPhysicsInteractable current = pendingInsertions.PeekFirst();
	
		while(current != null)
		{
			if(!treeBuilt) objects.add(current);
			else Insert(current);
			
			pendingInsertions.Dequeue();
			current = pendingInsertions.PeekFirst();
		}
			
		treeBuilt = true;
	}
	
	private void BuildTree()
	{
		if(objects.size() <= 1) return;
		
		PVector dimensions = bounds.Max().sub(bounds.Min());
		
		if(dimensions.magSq() <= 0.0f)
		{
			/// Find Enclosing Cube...
			dimensions = bounds.Max().sub(bounds.Min());
		}
		
		if(dimensions.x <= DEFAULT_MIN_REGION && dimensions.y <= DEFAULT_MIN_REGION && dimensions.z <= DEFAULT_MIN_REGION) return;
		
		PVector half = dimensions.mult(0.5f);
		PVector center = bounds.Min().sub(half);
		PBounds[] octant = new PBounds[SIZE_CHILDREN];
		
		/// Create Subdivided regions for each octant.
		octant[0] = new PBounds();
		octant[1] = new PBounds();
		octant[2] = new PBounds();
		octant[3] = new PBounds();
		octant[4] = new PBounds();
		octant[5] = new PBounds();
		octant[6] = new PBounds();
		octant[7] = new PBounds();
	}
	
	public static PBounds FindEnclosingCube(PBounds _bounds)
	{
		PVector offset = new PVector().sub(_bounds.Min());
		
		_bounds.Min(_bounds.Min().add(offset));
		_bounds.Max(_bounds.Max().add(offset));
		
		int highestDimension = (int)Math.ceil(VoidlessMath.Max(_bounds.Max().x, _bounds.Max().y, _bounds.Max().z));
		
		for(int bit = 0; bit < 32; bit++)
		{
			if(highestDimension == (1 << bit))
			{
				_bounds.Max(new PVector(highestDimension, highestDimension, highestDimension));
				_bounds.Min(_bounds.Min().sub(offset));
				_bounds.Max(_bounds.Max().sub(offset));
				
				return new PBounds();
			}
		}
		
		return null;
	}
	
	public void Insert(IPPhysicsInteractable _object)
	{
		objects.add(_object);
	}

	@Override
	public void Reset()
	{
		if(objects == null) objects = new CopyOnWriteArrayList<IPPhysicsInteractable>();
		else objects.clear();
		
		if(childNodes == null) childNodes = new POctree[SIZE_CHILDREN];
		
		if(bounds == null) bounds = new PBounds();
		
		lifeSpan = DEFAULT_LIFE_SPAN;
		currentLife = DEFAULT_INITIAL_LIFE;
		activeNodes = 0;
	}

	@Override
	public void Debug()
	{
		/// Debug.
		
		/// Recursively debug all children.
		if(childNodes.length > 0)
		for(POctree child : childNodes)
		{
			child.Debug();
		}
	}
}
