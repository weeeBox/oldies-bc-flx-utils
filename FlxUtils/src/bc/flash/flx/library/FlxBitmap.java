package bc.flash.flx.library;

import java.awt.image.BufferedImage;

public class FlxBitmap extends FlxSymbol
{
	private BufferedImage image;

	public FlxBitmap(String name, BufferedImage image)
	{
		super(name);
		this.image = image;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	@Override
	public FlxBitmapInstance createInstance()
	{
		return new FlxBitmapInstance(this);
	}
}
