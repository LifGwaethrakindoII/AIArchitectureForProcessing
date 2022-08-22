package VoidlessUtilities.Processing.Core;

import java.util.*;
import java.util.concurrent.*;
import VoidlessUtilities.*;
import VoidlessUtilities.Enumerators.ContactState;
import VoidlessUtilities.Processing.AI.IPSightSensor;
import VoidlessUtilities.Processing.AI.PSensor;
import VoidlessUtilities.Processing.Colliders.PCollider;
import VoidlessUtilities.Processing.Physics.IPPhysicsInteractable;
import VoidlessUtilities.Structures.LayerMask;
import processing.core.*;

public class PPhysicsSystem
{
	public static final int LIMIT_MATRIX = LayerMask.LIMIT_MASK;
	public static final float FORCE_GRAVITY = 9.81f;
	public static final int INTERACTABLE_ID_SPHERE = 0;
	public static final int INTERACTABLE_ID_VIEWCONE = 1;
	
	private static PPhysicsSystem Instance;
	private static PVector gravityDirection;
	private static float gravityForce;
	
	private static boolean[][] contactMatrix;
	private static boolean[][] visibleMatrix;
	private static boolean[][] audibleMatrix;
	private static boolean[][] odorableMatrix;
	
	private static CopyOnWriteArrayList<PCollider> colliders;
	private static CopyOnWriteArrayList<PSensor> sensors;
	private static CopyOnWriteArrayList<IPSightSensor> sightSensors;
	
	public static PPhysicsSystem GetInstance()
	{
		if(Instance == null) Instance = new PPhysicsSystem();
		return Instance;
	}
	
	public PPhysicsSystem()
	{
		colliders = new CopyOnWriteArrayList<PCollider>();
		sensors = new CopyOnWriteArrayList<PSensor>();
		contactMatrix = new boolean[LIMIT_MATRIX][LIMIT_MATRIX];
		visibleMatrix = new boolean[LIMIT_MATRIX][LIMIT_MATRIX];
		audibleMatrix = new boolean[LIMIT_MATRIX][LIMIT_MATRIX];
		odorableMatrix = new boolean[LIMIT_MATRIX][LIMIT_MATRIX];
	}
	
	public static void ToggleContactFlag(short a, short b, boolean flag)
	{
		contactMatrix[a][b] = flag;
	}
	
	public static void AddInteractable(IPPhysicsInteractable _interactable)
	{
		if(_interactable.getClass() == PCollider.class) colliders.add((PCollider)_interactable);
	}
	
	public static void RemoveInteractable(IPPhysicsInteractable _interactable)
	{
		if(_interactable.getClass() == PCollider.class) colliders.remove((PCollider)_interactable);
	}
	
	public void Update()
	{
		EvaluateSensors();
		EvaluateColliders();
	}
	
	private void EvaluateSensors()
	{
		boolean intersection = false;
		
		for(PSensor sensor : sensors)
		{
			for(PCollider collider : colliders)
			{
				switch(sensor.interactableID())
				{
					case PPhysics.INTERACTABLE_ID_VIEWCONE:
						intersection = ViewConeSphereIntersection(sensor, collider);
					break;
					
					case PPhysics.INTERACTABLE_ID_FRUSTUM:
						intersection = FrustumSphereIntersection(sensor, collider);
					break;
				}
				
				if(intersection) sensor.AddCollider(collider);
				else sensor.RemoveCollider(collider);
			}
			
			
		}
	}
	
	private void EvaluateColliders()
	{
		int i = 0;
		PCollider b = null;
		boolean contact = false;
		
		/*
		 * The algorithm makes a for loop that takes:
		 * - O(log2(N)) on time and
		 * - O(1) in space.
		 * a.- If there is any collision between collider a and b, it evaluates:
		 * 		1.- If collider a has not any collision registered with collider b,
		 * 		it registers collisions on a and b.
		 * 		2.- Otherwise, it will call Collision Stay callback.
		 * b.- If there is no collision between a and b. Collision Exit callback is
		 * invoked on both a and b.
		 * */
		for(PCollider a : colliders)
		{
			for(int j = i + 1; j < colliders.size(); j++)
			{
				b = colliders.get(j);
				if(!a.active() || !b.active()) continue;
				
				switch(a.interactableID())
				{
					case PPhysics.INTERACTABLE_ID_SPHERE:
					switch(b.interactableID())
					{
						case PPhysics.INTERACTABLE_ID_SPHERE: contact = SphereSphereIntersection(a, b); break;
						default: contact = false; break;
					}
					break;
				}
				
				System.out.println("Intersected: " + contact);
				
				if(contact)
				{
					if(!a.HasRegisteredCollisionWith(b.ID()))
					{
						if(!a.isTrigger()) a.RegisterCollision(b.ID(), true);
						else a.RegisterTrigger(b.ID(), true);
						if(!b.isTrigger()) b.RegisterCollision(a.ID(), true);
						else b.RegisterTrigger(a.ID(), true);
						a.OnContact(b, ContactState.Enter);
						b.OnContact(a, ContactState.Enter);
					}
					else
					{
						a.OnContact(b, ContactState.Stay);
						b.OnContact(a, ContactState.Stay);
					}
				}
				else
				{
					if(a.HasRegisteredCollisionWith(b.ID()))
					{
						if(!a.isTrigger()) a.RegisterCollision(b.ID(), false);
						else a.RegisterTrigger(b.ID(), false);
						if(!b.isTrigger()) b.RegisterCollision(a.ID(), false);
						else b.RegisterTrigger(a.ID(), false);
						a.OnContact(b, ContactState.Exit);
						b.OnContact(a, ContactState.Exit);
					}
				}
				
				i++;
			}
		}
	}
	
	public void AddCollider(PCollider _collider)
	{
		colliders.add(_collider);
	}
	
	public void RemoveCollider(PCollider _collider)
	{
		colliders.remove(_collider);
	}
	
	private boolean SphereSphereIntersection(IPPhysicsInteractable a, IPPhysicsInteractable b)
	{
		float squareDistance = a.position().sub(b.position()).magSq();
		float radiusA = a.data(PPhysics.ID_RADIUS);
		float radiusB = b.data(PPhysics.ID_RADIUS);
		float radiusSum = radiusA + radiusB;
		float radiusDif = radiusA - radiusB;
		radiusSum *= radiusSum;
		radiusDif *= radiusDif;
		
		return squareDistance < radiusSum/* || squareDistance < radiusDif*/;
	}
	
	private boolean SphereSphereContact(IPPhysicsInteractable a, IPPhysicsInteractable b)
	{
		float squareDistance = a.position().sub(b.position()).magSq();
		float radiusA = a.data(PPhysics.ID_RADIUS);
		float radiusB = b.data(PPhysics.ID_RADIUS);
		radiusA *= radiusA;
		radiusB *= radiusB;
		
		return squareDistance <= (radiusA + radiusB);
	}
	
	private boolean ViewConeSphereIntersection(IPPhysicsInteractable a, IPPhysicsInteractable b)
	{
		PVector direction = b.position().sub(a.position());
		float viewLength = a.data(PPhysics.ID_LENGTH);
		if(direction.magSq() > viewLength * viewLength) return false;
		PVector frustumOrientation = new PVector(
				a.data(PPhysics.ID_ORIENTATION_X),
				a.data(PPhysics.ID_ORIENTATION_Y),
				a.data(PPhysics.ID_ORIENTATION_Z));
		float angle = a.data(PPhysics.ID_ANGLE_Y) * VoidlessMath.DEGREES_TO_RADIANS;
		
		///TODO: Add orientation function as quaternion to make relative vectors.
		return false;
	}
	
	private boolean FrustumSphereIntersection(IPPhysicsInteractable a, IPPhysicsInteractable b)
	{
		return false;
	}
}
