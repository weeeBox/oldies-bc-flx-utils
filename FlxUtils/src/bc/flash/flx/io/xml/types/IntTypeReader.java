package bc.flash.flx.io.xml.types;

import bc.flash.flx.io.xml.TypeReadException;
import bc.flash.flx.io.xml.TypeReader;

public class IntTypeReader implements TypeReader
{
	@Override
	public Object read(String value) throws TypeReadException
	{
		try
		{
			return Integer.parseInt(value);
		}
		catch (NumberFormatException e)
		{
			throw new TypeReadException(e);
		}
	}
}
