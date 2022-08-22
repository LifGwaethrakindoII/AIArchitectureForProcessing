package VoidlessUtilities;

import com.google.gson.*;

public class VoidlessJSON
{
	public static <T> void SerializeToJSON(Gson _gson, T _object, String _path)
	{
		String json = _gson.toJson(_object);
		
		try
		{
			VoidlessText.WriteToTextFile(json, _path);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public static <T> T DeserializeFromJSONAtPath(Gson _gson, String _path, Class<T> _type)
	{
		T result = null;
		String json = null;
		
		try
		{
			json = VoidlessText.LoadFromTextFile(_path);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		if(json != null) result = _gson.fromJson(json, _type);
		return result;
	}
	
	public static <T> T DeserializeFromJSON(Gson _gson, String _json, Class<T> _type)
	{
		return _gson.fromJson(_json, _type);
	}
}
