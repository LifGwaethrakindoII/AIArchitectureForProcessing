package VoidlessUtilities;

import java.io.*;

public class VoidlessText
{
	public static final String BREAK_LINE = System.getProperty("line.separator");
	
	public static void WriteToTextFile(String _string, String _filePath) throws IOException
	{
	    BufferedWriter writer = new BufferedWriter(new FileWriter(_filePath));
	    writer.write(_string);
	     
	    writer.close();
	}
	
	public static String LoadFromTextFile(String _filePath) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(_filePath));
		StringBuilder stringBuilder = new StringBuilder();
		char[] buffer = new char[10];
		
		while(reader.read(buffer) != -1)
		{
			stringBuilder.append(new String(buffer));
			buffer = new char[10];
		}
		reader.close();

		return stringBuilder.toString();
	}
}
