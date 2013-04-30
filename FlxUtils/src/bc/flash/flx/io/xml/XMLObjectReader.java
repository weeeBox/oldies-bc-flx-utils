package bc.flash.flx.io.xml;

import java.io.IOException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class XMLObjectReader
{
	public Object read(InputStream stream) throws IOException
	{
		return read(readDocument(stream));
	}

	public Object read(Document doc)
	{
		return null;
	}

	private Document readDocument(InputStream stream) throws IOException
	{
		try
		{
			return new SAXReader().read(stream);
		}
		catch (Exception e)
		{
			throw new IOException(e);
		}
	}
}
