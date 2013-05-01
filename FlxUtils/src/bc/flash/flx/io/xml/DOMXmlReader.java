package bc.flash.flx.io.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DOMXmlReader
{
	private String packageName;
	
	public DOMXmlReader(String packageName)
	{
		if (packageName == null)
		{
			throw new NullPointerException("'packageName' is null");
		}
		
		this.packageName = packageName;
	}
	
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
	public <T> T read(Document doc, Class<T> clazz) throws ClassCastException
	{
		Element rootElement = doc.getRootElement();
		return (T) read(rootElement);
	}

	private Object read(Element element)
	{
		String className = element.getName();
		
		try
		{
			Class<?> clazz = findClass(className);
			Object object = clazz.newInstance();
			
			readAttributes(object, element, clazz.getFields());
			
			return object;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	private void readAttributes(Object object, Element element, Field[] fields) throws IllegalArgumentException, IllegalAccessException
	{
		for (Field field : fields)
		{
			String name = field.getName();
			String value = element.attributeValue(name);
			
			if (value != null)
			{
				setAttribute(field, object, value);
			}
		}
	}

	private void setAttribute(Field field, Object object, String value) throws IllegalArgumentException, IllegalAccessException
	{
		field.set(object, value);
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
	
	private Class<?> findClass(String name) throws ClassNotFoundException
	{
		String className = packageName + "." + name;
		return Class.forName(className);
	}
}
