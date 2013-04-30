package bc.flash.flx.io.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLObjectReader
{
	public <T> T read(File file, Class<T> clazz) throws IOException
	{
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(file);
			return read(readDocument(fis), clazz);
		}
		finally
		{
			if (fis != null)
				fis.close();
		}
	}

	public <T> T read(InputStream stream, Class<T> clazz) throws IOException
	{
		return read(readDocument(stream), clazz);
	}

	@SuppressWarnings("unchecked")
	public <T> T read(Document doc, Class<T> clazz) throws IOException
	{
		Element rootElement = doc.getRootElement();
		Object object = read(rootElement);
		
		if (clazz.isInstance(object))
		{
			return (T) object;
		}
		
		throw new IOException("Unexpected class: " + object.getClass());
	}

	private Object read(Element rootElement)
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
