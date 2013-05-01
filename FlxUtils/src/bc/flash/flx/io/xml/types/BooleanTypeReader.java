package bc.flash.flx.io.xml.types;

import bc.flash.flx.io.xml.TypeReader;

public class BooleanTypeReader implements TypeReader
{
	@Override
	public Object read(String value)
	{
		return Boolean.parseBoolean(value);
	}
}
