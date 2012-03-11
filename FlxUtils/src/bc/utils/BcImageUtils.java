package bc.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import bc.craqua.util.BcSize;

public class BcImageUtils
{
	public static BcSize imageSize(File file)
	{
		BufferedImage image = readImage(file);
		if (image == null)
		{
			return null;
		}
		
		return new BcSize(image.getWidth(), image.getHeight());
	}

	public static BufferedImage readImage(File file)
	{
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return image;
	}
}
