package VoidlessUtilities.Processing.Core;

import processing.core.*;
import java.time.*;
import java.time.format.*;
//import java.io.IOException;
import java.util.*;
import VoidlessUtilities.*;

import com.google.gson.*;
//import com.google.gson.annotations.*;

public class PSketch extends PApplet
{
	public static final String[] DEFAULT_NAME = new String[] { "Voidless PSketch" };
	public static final String DEFAULT_SKETCH_NAME = "VoidlessUtilitiesPSketch";
	public static final String PATH_DATA = "Data/";
	public static final String PATH_SCREEN_CAPTURES = "Screen Captures/";
	public static final String PATH_LOGS = "Logs/";
	public static final String DATA_PREFIX = "Data_";
	public static final String EXTENSION_IMAGE = ".jpg";
	public static final String EXTENSION_TEXT = ".txt";
	public static final String FORMAT_DATE = "yyyy-MM-dd_HH-mm-ss";
	public static final String NAME_MOUSE_POINTER = "Mouse_Pointer";
	public static final char KEY_CHAR_DEBUG = 'd';
	public static final char KEY_CHAR_RESET = 'r';
	public static final char KEY_CHAR_PRINT_SCREEN = 'p';
	public static final int WIDTH_SCREEN = 1100;
	public static final int HEIGHT_SCREEN = 800;
	
	private static StringBuilder stringBuilder;
	private static DateTimeFormatter dateTimeFormatter;
	private static Gson gson;
	private static PTime time;
	private static PPhysicsSystem physicsSystem;
	private static PSketch sketch;
	private static ArrayList<PEntity> entities;
	private static PVector mousePosition;
	private static PVector mousePoint;
	private static PVector center;
	private static String sketchName;
	private static String[] name;
	private static int backgroundColor;
	private static boolean debug;
	
	public static StringBuilder stringBuilder() { return stringBuilder; }
	
	public static DateTimeFormatter dateTimeFormatter() { return dateTimeFormatter; }
	
	public static Gson gson() { return gson; }
	
	protected static void time(PTime _time) { time = _time; }
	
	protected static void physicsSystem(PPhysicsSystem _physicsSystem) { physicsSystem = _physicsSystem; }

	public static PSketch sketch() { return sketch; }
	protected static void sketch(PSketch _sketch) { sketch = _sketch; }

	protected static ArrayList<PEntity> entities() { return entities; }
	protected static void entities(ArrayList<PEntity> _entities) { entities = _entities; }

	public static PVector mousePosition() { return mousePosition.copy(); }
	protected static void mousePosition(PVector _mousePosition) { mousePosition = _mousePosition; }
	
	public static PVector mousePoint() { return mousePoint.copy(); }
	protected static void mousePoint(PVector _mousePoint) { mousePoint = _mousePoint; }
	
	public static PVector center() { return center.copy(); }
	
	public static String sketchName() { return sketchName != null ? sketchName : DEFAULT_SKETCH_NAME; }
	protected static void sketchName(String _sketchName) { sketchName = _sketchName; }
	
	public static String[] name() { return name != null ? name : DEFAULT_NAME; }
	protected static void name(String[] _name) { name = _name; }
	
	public static int backgroundColor() { return backgroundColor; }
	public static void backgroundColor(int _backgroundColor) { backgroundColor = _backgroundColor; }
	
	public static boolean debug() { return debug; }
	public static void debug(boolean _debug) { debug = _debug; }

	/*public PSketch()
	{
		
	}*/
	
	protected void Initialize()
	{
		size(WIDTH_SCREEN, HEIGHT_SCREEN);
		stringBuilder = new StringBuilder();
		mousePosition = new PVector(mouseX, mouseY);
		center = new PVector(width * 0.5f, height * 0.5f);
		mousePoint = center;
		entities = new ArrayList<PEntity>();
		dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
		gson = new Gson();
		time = new PTime();
		physicsSystem = new PPhysicsSystem();
		
		entities.add(new PEntity(NAME_MOUSE_POINTER, new PMousePointer()));
	}
	
	public void settings()
	{
		Initialize();
		AwakeEntities();
	}
	
	protected void AwakeEntities()
	{
		for(PEntity entity : entities)
		{
			entity.OnAwake();
		}
	}
	
	protected void StartSketch()
	{
		
	}

	public void draw()
	{
		background(backgroundColor());
		Update();
	}
	
	private void Update()
	{
		mousePosition.set(mouseX, mouseY);
		time.Update();
		physicsSystem.Update();
		UpdateEntities();
	}
  
	public static void main(String[] args)
	{
		sketch = new PSketch();
		runSketch(name(), sketch);
    }
	
	public void mousePressed()
	{
		mousePoint(mousePosition());
	}
	
	public void mouseReleased()
	{
		//mousePoint(mousePosition());
	}
	
	public void keyReleased()
	{
		if(key == KEY_CHAR_DEBUG) debug(!debug());
		if(key == KEY_CHAR_PRINT_SCREEN) SaveImage();
	}
	
	public void exit()
	{
		super.exit();
		SaveLog();
	}
	
	public static PEntity FindEntityWithName(String _name)
	{
		PEntity result = null;
		
		for(PEntity entity : entities)
		{
			if(entity.name() == _name)
			{
				result = entity;
				break;
			}
		}
		
		return result;
	}
	
	public static PEntity FindEntityWithTag(String _tag)
	{
		PEntity result = null;
		
		for(PEntity entity : entities)
		{
			if(entity.tag() == _tag)
			{
				result = entity;
				break;
			}
		}
		
		return result;
	}
	
	public static PEntity FindEntityWithID(int _ID)
	{
		PEntity result = null;
		
		for(PEntity entity : entities)
		{
			if(entity.ID() == _ID)
			{
				result = entity;
				break;
			}
		}
		
		return result;
	}
	
	protected void UpdateEntities()
	{
		for(PEntity entity : entities)
		{
			entity.Update();
			if(debug()) entity.Debug();
		}
	}
	
	public static void AddEntity(PEntity _entity)
	{
		if(!entities.contains(_entity)) entities.add(_entity);
	}
	
	public static void RemoveEntity(PEntity _entity)
	{
		if(entities.contains(_entity)) entities.remove(_entity);
	}
	
	public static void SaveImage()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(PATH_SCREEN_CAPTURES);
		builder.append(sketchName());
		builder.append("/");
		builder.append(sketchName());
		builder.append("_");
		builder.append(dateTimeFormatter.format(LocalDateTime.now()).toString());
		builder.append(EXTENSION_IMAGE);

		System.out.println("Captured Screen, stored at path: " + builder.toString());
		sketch().save(builder.toString());
	}
	
	public static void RegisterLogEntry(String _objectName, String _entry)
	{
		stringBuilder.append(dateTimeFormatter.format(LocalDateTime.now()).toString());
		stringBuilder.append("_");
		stringBuilder.append(_objectName);
		stringBuilder.append(": ");
		stringBuilder.append(_entry);
		stringBuilder.append(VoidlessText.BREAK_LINE);
	}
	
	public static void SaveLog()
	{
		if(stringBuilder.length() <= 0) return;
		
		StringBuilder pathBuilder = new StringBuilder();
		String[] strings = new String[1];
		
		pathBuilder.append(PATH_LOGS);
		pathBuilder.append(sketchName());
		pathBuilder.append("/");
		pathBuilder.append(sketchName());
		pathBuilder.append("_");
		pathBuilder.append(dateTimeFormatter.format(LocalDateTime.now()).toString());
		pathBuilder.append(EXTENSION_TEXT);
		
		strings[0] = stringBuilder.toString();
		sketch().saveStrings(pathBuilder.toString(), strings);
		
		/*try { VoidlessText.WriteToTextFile(stringBuilder.toString(), pathBuilder.toString()); }
		catch(Exception exception) { System.out.println(exception); }*/
	}
	
	public static boolean KeyPressed(char _key)
	{
		return sketch.keyPressed && sketch.key == _key;
	}
	
	public static boolean KeyPressed(int _keyCode)
	{
		return sketch.keyPressed && sketch.keyCode == _keyCode;
	}
	
	public static boolean KeyReleased(char _key)
	{
		return !sketch.keyPressed && sketch.key == _key;
	}
	
	public static boolean KeyReleased(int _keyCode)
	{
		return !sketch.keyPressed && sketch.keyCode == _keyCode;
	}

	public static int GetColor(PColor _color)
	{
		switch(_color.format())
		{
			case RGBA: return sketch.color(_color.r(), _color.g(), _color.b(), _color.a());
			case RGB: return sketch.color(_color.r(), _color.g(), _color.b());
			case GrayscaleAlpha: return sketch.color(_color.g(), _color.a());
			case Grayscale: return sketch.color(_color.g());
			default: return 0;
		}
	}
	
	public static void DrawRect(PVector _position, float _width, float _height)
	{
		sketch().rect(_position.x, _position.y, _width, _height);
	}
	
	public static void DrawCircle(PVector _position, float _radius)
	{
		sketch().ellipse(_position.x, _position.y, _radius, _radius);
	}
	
	public static void DrawEllipse(PVector _position, float _width, float _height)
	{
		sketch().ellipse(_position.x, _position.y, _width, _height);
	}
	
	/*public static <T extends PComponent> PEntity CreateEntity(String _path, PComponentSerializableData<T> ... _data)
	{
		PEntity entity = null;
		
		entity = VoidlessJSON.DeserializeFromJSONAtPath(gson, _path, PEntity.class);
		
		if(entity != null)
		{
			for(PComponent)
		}
		
		return entity;
	}*/
}
