package bc.flash.flx.io.xml.types;

import bc.flash.flx.io.xml.TypeReader;

public class StringTypeReader implements TypeReader
{
	@Override
	public Object read(String value)
	{
		return value;
	}
}
