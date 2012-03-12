package bc.flash.flx.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import bc.flash.flx.FlxAttribute;
import bc.flash.flx.FlxDOMDocument;
import bc.flash.flx.FlxElement;
import bc.utils.xml.XMLHelper;

public class FlxDOMWriter
{
	private static String[] rootAttributes = 
	{
		"currentTimeline", "1", 
		"xflVersion", "2.0", 
		"creatorInfo", "Adobe Flash Professional CS5", 
		"platform", "Windows", 
		"versionInfo", "Saved by Adobe Flash Windows 11.0 build 485", 
		"majorVersion", "11", 
		"buildNumber", "485", 
		"nextSceneIdentifier", "2", 
		"playOptionsPlayLoop", "false", 
		"playOptionsPlayPages", "false", 
		"playOptionsPlayFrameActions", "false"
	};
	
	public void write(FlxDOMDocument doc, File file) throws IOException
	{
		Document xmlDoc = DocumentHelper.createDocument();
		Element rootElement = xmlDoc.addElement(QName.get("DOMDocument", Namespace.get("http://ns.adobe.com/xfl/2008/")));
		rootElement.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		XMLHelper.addAttributes(rootElement, rootAttributes);

		List<FlxElement> elements = doc.elements();
		for (FlxElement e : elements)
		{
			addElement(rootElement, e);
		}
		
		write(xmlDoc, file);
	}
	
	private void addElement(Element parent, FlxElement flxElement)
	{
		Element element = parent.addElement(flxElement.elementName());
		List<FlxAttribute> attributes = flxElement.attributes();
		for (FlxAttribute attr : attributes)
		{
			element.addAttribute(attr.getName(), attr.getValue());
		}
		
		List<FlxElement> elements = flxElement.elements();
		for (FlxElement child : elements)
		{
			addElement(element, child);
		}
	}

	private static void write(Document doc, File file) throws IOException
	{
		FileOutputStream stream = null;
		try
		{
			stream = new FileOutputStream(file);
			XMLWriter writer = new XMLWriter(stream, OutputFormat.createPrettyPrint());
			writer.write(doc);
		}
		finally
		{
			if (stream != null)
				stream.close();
		}
	}
}
