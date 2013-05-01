package bc.flash.flx.io.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import bc.flash.flx.dom.Color;
import bc.flash.flx.io.xml.types.BooleanTypeReader;
import bc.flash.flx.io.xml.types.ColorTypeReader;
import bc.flash.flx.io.xml.types.FloatTypeReader;
import bc.flash.flx.io.xml.types.IntTypeReader;
import bc.flash.flx.io.xml.types.LongTypeReader;
import bc.flash.flx.io.xml.types.StringTypeReader;

public class DOMXmlReader
{
	private String packageName;
	private Map<Class<?>, TypeReader> readersMap;
	
	public DOMXmlReader(String packageName)
	{
		if (packageName == null)
		{
			throw new NullPointerException("'packageName' is null");
		}
		
		this.packageName = packageName;
		readersMap = createReaders();
	}

	private Map<Class<?>, TypeReader> createReaders()
	{
		Map<Class<?>, TypeReader> readers = new HashMap<Class<?>, TypeReader>();
		// TODO: add runtime class resolver
		readers.put(int.class, new IntTypeReader());
		readers.put(float.class, new FloatTypeReader());
		readers.put(boolean.class, new BooleanTypeReader());
		readers.put(long.class, new LongTypeReader());
		readers.put(String.class, new StringTypeReader());
		readers.put(Color.class, new ColorTypeReader());
		return readers;
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

	public void addReader(Class<?> cls, TypeReader reader)
	{
		readersMap.put(cls, reader);
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

	private void readAttributes(Object object, Element element, Field[] fields) throws TypeReadException
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

	private void setAttribute(Field field, Object object, String value) throws TypeReadException
	{
		TypeReader typeReader = findReader(field.getType());
		if (typeReader == null)
		{
			throw new TypeReadException("Unable to resolve reader for class: " + field.getType());
		}
		
		try
		{
			field.set(object, typeReader.read(value));
		}
		catch (TypeReadException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new TypeReadException(e);
		}
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
	
	private TypeReader findReader(Class<?> cls)
	{
		return readersMap.get(cls);
	}
}
