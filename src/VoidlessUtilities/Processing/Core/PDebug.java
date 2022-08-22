package VoidlessUtilities.Processing.Core;

import VoidlessUtilities.Processing.Structures.PRay;
import processing.core.*;

public class PDebug
{
	public static void DrawRay(PRay _ray, int _stroke)
	{
		PSketch.sketch().stroke(_stroke);
		PSketch.sketch().line(_ray.origin.x, _ray.origin.y, _ray.origin.x + _ray.direction.x, _ray.origin.y + _ray.direction.y);
	}
	
	public static void DrawRay(PBody _body, int _stroke)
	{
		PVector position = _body.entity().transform().position();
		PSketch.sketch().stroke(_stroke);
		PSketch.sketch().line(position.x, position.y, position.add(_body.velocity()).x, position.add(_body.velocity()).y);
	}
	
	public static void DrawLine(PVector a, PVector b, int _stroke, int _strokeWeight)
	{
		PSketch.sketch().stroke(_stroke);
		PSketch.sketch().strokeWeight(_strokeWeight);
		PSketch.sketch().line(a.x, a.y, b.x, b.y);
	}
}
