package VoidlessUtilities;

import processing.core.*;

public class VoidlessMath
{
	public final static float PI = 3.14159f;
	public final static float E = 0.0f;
	public final static float RADIANS_TO_DEGREES = (PI / 180.0f);
	public final static float DEGREES_TO_RADIANS = (180.0f / PI);
	public final static float INFINITE = Float.MAX_VALUE;
	public final static float NEGATIVE_INFINITE = Float.MIN_VALUE;
	
	public static int Clamp(int x, int min, int max)
	{
		return x < min ? min : x > max ? max : x;
	}
	
	public static int Min(int x, int min)
	{
		return x < min ? min : x;
	}
	
	public static int Min(int ... numbers)
	{
		int min = Integer.MAX_VALUE;
		
		for(int x : numbers)
		{
			if(x < min) min = x;
		}
		
		return min;
	}
	
	public static int Max(int x, int max)
	{
		return x > max ? max : x;
	}
	
	public static int Max(int ... numbers)
	{
		int max = Integer.MIN_VALUE;
		
		for(int x : numbers)
		{
			if(x > max) max = x;
		}
		
		return max;
	}
	
	public static float Clamp(float x, float min, float max)
	{
		return x < min ? min : x > max ? max : x;
	}
	
	public static float Min(float x, float min)
	{
		return x < min ? min : x;
	}
	
	public static float Min(float ... numbers)
	{
		float min = INFINITE;
		
		for(float x : numbers)
		{
			if(x < min) min = x;
		}
		
		return min;
	}
	
	public static float Max(float x, float max)
	{
		return x > max ? max : x;
	}
	
	public static float Max(float ... numbers)
	{
		float max = NEGATIVE_INFINITE;
		
		for(float x : numbers)
		{
			if(x > max) max = x;
		}
		
		return max;
	}
	
	public static float Lerp(float x0, float xf, float t)
	{
		return (x0 + (t * (xf - x0)));
	}
	
	public static float Abs(float x)
	{
		return x < 0.0f ? -x : x;
	}
	
	public static float NegativeAbs(float x)
	{
		return x < 0.0f ? x : -x;
	}
	
	public static int Power(int x, int p)
	{
		if(p == 0) return 1;
		else if(p == 1) return x;
		else
		{
			for(int i = 0; i < p - 1; i++)
			{
				x *= x;
			}
			
			return p < 0 ? (1 / x) : x;
		}
	}
	
	public static float Power(float x, int p)
	{
		if(p == 0) return 1.0f;
		else if(p == 1) return x;
		else
		{
			for(int i = 0; i < p - 1; i++)
			{
				x *= x;
			}
			
			return p < 0.0f ? (1.0f / x) : x;
		}
	}
	
	public static float Power(float x, float p)
	{
		if(p == 0.0f) return 1.0f;
		else if(p == 1.0f) return x;
		else
		{
			float result = x;
			float flooredPower = (float)Math.floor(p);
			float remaining = p - flooredPower;
			
			for(int i = 0; i < flooredPower - 1; i++)
			{
				result *= x;
			}
			
			result = remaining == 0.0f ? result : Lerp(result, result * x, remaining);	
			return p < 0.0f ? (1.0f / result) : result;
		}
	}
	
	public static float Log(float x, float b)
	{
		float count = 0.0f;

		while(x > (b - 1.0f))
		{
			x /= b;
			count++;
		}

		return count;
	}
	
	public static float Log2(float x)
	{
		return Log(x, 2);
	}
	
	public static float Log10(float x)
	{
		return Log(x, 10);
	}
	
	public static float MembershipFunction(float x, float min, float max)
	{
		return (x - min) / (max -min);
	}
	
	public static float SquarePithagoras(float ... cathetus)
	{
		float summatory = 0.0f;
		
		for(float x : cathetus)
		{
			summatory += (x * x);
		}
		
		return summatory;
	}
	
	public static float Pithagoras(float ... cathetus)
	{
		float summatory = 0.0f;
		
		for(float x : cathetus)
		{
			summatory += (x * x);
		}
		
		return PApplet.sqrt(summatory);
	}
}
