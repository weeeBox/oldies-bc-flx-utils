package bc.flash.flx;

import java.util.ArrayList;
import java.util.List;

public class FlxElement
{
	private static final String CLASS_PREFIX = "Flx";
	
	private List<FlxAttribute> attributes;
	private List<FlxElement> elements;

	public FlxElement()
	{
		attributes = new ArrayList<FlxAttribute>();
		elements = new ArrayList<FlxElement>();
	}
	
	public FlxElement(String name)
	{		
		this();
		addAttribute("name", name);
	}

	public void addElement(FlxElement element)
	{
		elements.add(element);
	}
	
	public void addAttribute(String name, Object value)
	{
		attributes.add(new FlxAttribute(name, value.toString()));
	}

	public String attributeValue(String name)
	{
		for (FlxAttribute attr : attributes)
		{
			if (attr.getName().equals(name))
			{
				return attr.getValue();
			}
		}
		
		return null;
	}

	public List<FlxElement> elements()
	{
		return elements;
	}
	
	public List<FlxAttribute> attributes()
	{
		return attributes;
	}
	
	public String elementName()
	{
		String name = getClass().getSimpleName();
		if (name.startsWith(CLASS_PREFIX))
		{
			return name.substring(CLASS_PREFIX.length());
		}
		return name;
	}
}