package bc.flash.flx.io.xml.types;

import bc.flash.flx.io.xml.TypeReadException;
import bc.flash.flx.io.xml.TypeReader;

public class FloatTypeReader implements TypeReader
{
	@Override
	public Object read(String value) throws TypeReadException
	{
		try
		{
			return Float.parseFloat(value);
		}
		catch (NumberFormatException e)
		{
			throw new TypeReadException(e);
		}
	}
}
