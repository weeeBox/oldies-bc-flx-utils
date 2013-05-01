package bc.flash.flx.io.xml.types;

import bc.flash.flx.dom.Color;
import bc.flash.flx.io.xml.TypeReadException;
import bc.flash.flx.io.xml.TypeReader;

public class ColorTypeReader implements TypeReader
{

	@Override
	public Object read(String value) throws TypeReadException
	{
		if (value.startsWith("#"))
		{
			try
			{
				String colorValue = value.substring(1);
				int color = Integer.parseInt(colorValue, 16);
				return new Color(color);
			}
			catch (Exception e)
			{
				throw new TypeReadException(e);
			}
		}
		
		throw new TypeReadException("Unexpected value: " + value);
	}
}
