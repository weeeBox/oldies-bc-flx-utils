package bc.flash.xml;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLHelper
{
	public static Document readDocument(File file)
	{
		SAXReader reader = new SAXReader();
		try
		{
			return reader.read(file);
		}
		catch (Exception e)
		{
		}
		return null;
	}
	
	public static void addAttributes(Element element, String...attributes)
	{
		assert attributes.length % 2 == 0;
		
		for (int i = 0; i < attributes.length;)
		{
			String attrName = attributes[i++];
			String attrValue = attributes[i++];
			
			element.addAttribute(attrName, attrValue);
		}
	}
	
	public static String attribute(Element e, String name)
	{
		return e.attributeValue(name);
	}
	
	public static boolean attributeBool(Element e, String name)
	{
		return attributeBool(e, name, false);
	}
	
	public static boolean attributeBool(Element e, String name, boolean defaultValue)
	{
		String value = attribute(e, name);
		if (value == null)
		{
			return defaultValue;
		}
		
		return Boolean.parseBoolean(value);
	}
	
	public static int attributeInt(Element e, String name)
	{
		return attributeInt(e, name, 0);
	}
	
	public static int attributeIntRadix(Element e, String name, int radix)
	{
		return attributeIntRadix(e, name, radix, 0);
	}
	
	public static int attributeInt(Element e, String name, int defaultValue)
	{
		return attributeIntRadix(e, name, 10, defaultValue);
	}
	
	public static int attributeIntRadix(Element e, String name, int radix, int defaultValue)
	{
		String str = attribute(e, name);
		if (str == null)
		{
			return defaultValue;
		}
		
		return Integer.parseInt(str, radix);
	}
	
	public static int attributeColor(Element e, String name)
	{
		return attributeColor(e, name, 0x00000000);
	}
	
	public static int attributeColor(Element e, String name, int defaultValue)
	{
		return attributeIntRadix(e, name, 16, defaultValue);
	}
	
	public static float attributeFloat(Element e, String name)
	{
		String str = attribute(e, name);
		return Float.parseFloat(str);
	}
	
	public static float[] attributeFloats(Element e, String name, String sep)
	{
		String str = attribute(e, name);
		if (str == null)
		{
			return null;
		}
		
		String[] tokens = str.split(sep);
		float[] floats = new float[tokens.length];
		for (int i = 0; i < tokens.length; i++)
		{
			floats[i] = Float.parseFloat(tokens[i]);
		}
	
		return floats;
	}
}
