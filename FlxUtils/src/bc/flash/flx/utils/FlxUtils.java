package bc.flash.flx.utils;

public class FlxUtils
{
	public static int pixelToTwips(int pixels)
	{
		return 20 * pixels;
	}
	
	public static int twipsToPixels(int twips)
	{
		return twips / 20;
	}
}
