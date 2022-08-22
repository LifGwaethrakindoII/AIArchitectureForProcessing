package VoidlessUtilities.Processing.Core;

import VoidlessUtilities.Processing.Enumerators.PColorFormat;

public class PColor
{
	private PColorFormat format;
	private int r;
	private int g;
	private int b;
	private int a;
	
	public PColorFormat format() { return format; }
	
	public int r() { return r; }
	public void setR(int _r)
	{
		r = _r;
		format.AddFlag(PColorFormat.RGB);
	}
	
	public int g() { return g; }
	public void setG(int _g) { g = _g; }
	
	public int b() { return b; }
	public void setB(int _b)
	{
		b = _b;
		format.AddFlag(PColorFormat.RGB);
	}
	
	public int a() { return a; }
	public void setA(int _a)
	{
		a = _a;
		format.AddFlag(PColorFormat.Alpha);
	}
	
	public PColor(int _r, int _g, int _b, int _a)
	{
		Set(_r, _g, _b, _a);
	}
	
	public PColor(int _r, int _g, int _b)
	{
		Set(_r, _g, _b);
	}
	
	public PColor(int _g, int _a)
	{
		Set(_g, _a);
	}
	
	public PColor(int _g)
	{
		Set(_g);
	}
	
	public void Set(int _r, int _g, int _b, int _a)
	{
		r = _r;
		g = _g;
		b = _b;
		a = _a;
		format = PColorFormat.RGBA;
	}
	
	public void Set(int _r, int _g, int _b)
	{
		r = _r;
		g = _g;
		b = _b;
		a = 0;
		format = PColorFormat.RGB;
	}
	
	public void Set(int _g, int _a)
	{
		g = _g;
		a = _a;
		r = 0;
		b = 0;
		format = PColorFormat.GrayscaleAlpha;
	}
	
	public void Set(int _g)
	{
		g = _g;
		r = 0;
		b = 0;
		a = 0;
		format = PColorFormat.Grayscale;
	}
	
	public static int Color(int c)
	{
		return PSketch.sketch().color(c);
	}
	
	public static int Color(int r, int g, int b)
	{
		return PSketch.sketch().color(r, g, b);
	}
	
	public static int Color(int r, int g, int b, int a)
	{
		return PSketch.sketch().color(r, g, b, a);
	}
	
	public static int Red() { return Color(255, 0, 0); }
	
	public static int Green() { return Color(0, 255, 0); }
	
	public static int Blue() { return Color(0, 0, 255); }
	
	public static int White() { return Color(255, 255, 255); }
	
	public static int Black() { return Color(0, 0, 0); }
}
