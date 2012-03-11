package bc.craqua.resources;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import bc.craqua.util.BcColor;
import bc.craqua.util.BcPoint;
import bc.flash.xml.XMLHelper;
import bc.utils.BcFileUtils;

public class BcEnemyDataReader
{
	public static BcEnemyData read(File file) throws IOException
	{
		Document doc = XMLHelper.readDocument(file);
		if (doc == null)
		{
			throw new IOException("Unable to read xml: " + file);
		}
		
		Element root = doc.getRootElement();
		String elementName = root.getName();
		String name = elementName.endsWith("_data") ? elementName.substring(0, elementName.length() - "_data".length()) : elementName;
		
		File resDir = new File(file.getParent(), BcFileUtils.nameNoExt(file));
		
		BcEnemyData data = new BcEnemyData(name);
		readBitmaps(root, data, resDir);
		readModels(root, data);
		
		return data;
	}

	private static void readBitmaps(Element root, BcEnemyData data, File resDir) throws IOException
	{
		File[] images = BcFileUtils.listFiles(resDir, ".png", ".jpg");
		
		String enemyName = data.getName();
		
		List<Element> elements = root.elements("bitmap");
		for (Element e : elements)
		{
			String id = XMLHelper.attribute(e, "id");
			String marker = enemyName + "_";
			int index = id.lastIndexOf(marker);
			if (index == -1)
			{
				throw new IOException(String.format("Can't extract image filename from id: id=%s name=%s", id, enemyName));
			}
			
			String filename = id.substring(index + marker.length());
			
			File file = findImage(images, filename);
			if (file == null)
			{
				throw new IOException(String.format("Image for bitmap '%s' not found", filename));
			}
			
			BcBitmap bitmap = new BcBitmap(id, file);
			
			bitmap.setPivot(attributePoint(e, "pivot"));
			bitmap.setScale(XMLHelper.attributeFloat(e, "scale"));
			bitmap.setColor(attributeColor(e, "color"));
			
			data.add(bitmap);
		}
	}

	private static void readModels(Element root, BcEnemyData data) throws IOException
	{
		List<Element> elements = root.elements("model");
		for (Element modelElement : elements)
		{
			String id = modelElement.attributeValue("id");
			BcModel model = new BcModel(id);
			
			List<Element> children = modelElement.elements();
			for (Element childElement : children)
			{
				String name = childElement.getName();
				if (name.equals("sprite"))
				{
					model.addChild(readSprite(childElement, data));
				}
				else if (name.equals("bitmap"))
				{
					model.addChild(readBitmap(childElement, data));
				}
				else
				{
					assert false : name;
				}
			}
			
			// TODO
			data.add(model);
		}
	}
	
	private static BcDisplayObject readSprite(Element element, BcEnemyData data) throws IOException
	{
		String id = element.attributeValue("id");
		
		BcSprite sprite = new BcSprite(id);
		sprite.setPosition(attributePoint(element, "position"));
		sprite.setVisible(XMLHelper.attributeBool(element, "visible", true));
		
		List<Element> elements = element.elements();
		for (Element child : elements)
		{
			String childName = child.getName();
			if (childName.equals("sprite"))
			{
				sprite.addChild(readSprite(child, data));
			}
			else if (childName.equals("bitmap"))
			{
				sprite.addChild(readBitmap(child, data));
			}
			else
			{
				assert false : childName;
			}
		}
		
		return sprite;
	}
	
	private static BcBitmap readBitmap(Element element, BcEnemyData data) throws IOException
	{
		String bitmapId = element.attributeValue("data");
		List<BcBitmap> bitmaps = data.getBitmaps();
		for (BcBitmap bitmap : bitmaps)
		{
			if (bitmap.getId().equals(bitmapId))
			{
				return bitmap;
			}
		}
		
		throw new IOException("Unable to locate bitmap data: " + bitmapId);
	}

	private static BcPoint attributePoint(Element e, String name)
	{
		float[] floats = XMLHelper.attributeFloats(e, name, ";\\s");
		if (floats == null)
		{
			return new BcPoint(0, 0);
		}
		
		if (floats.length != 2 && floats.length != 1)
		{
			throw new IllegalArgumentException("Bad point coords: " + XMLHelper.attribute(e, name));
		}
		
		float x = floats[0];
		float y = floats.length == 2 ? floats[1] : 0;
		
		return new BcPoint(x, y);
	}
	
	private static BcColor attributeColor(Element e, String name)
	{
		int argb = XMLHelper.attributeColor(e, name);
		return new BcColor(argb);
	}
	
	private static File findImage(File[] images, String id)
	{
		for (File file : images)
		{
			String name = BcFileUtils.nameNoExt(file);
			if (id.endsWith(name))
			{
				return file;
			}
		}
		
		return null;
	}
}
