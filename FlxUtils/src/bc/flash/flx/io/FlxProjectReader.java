package bc.flash.flx.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import bc.flash.flx.FlxProject;
import bc.flash.flx.io.xml.XMLObjectReader;

public class FlxProjectReader
{
	public FlxProject read(File file) throws IOException
	{
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(file);
			return read(fis);
		}
		finally
		{
			if (fis != null)
				fis.close();
		}
	}

	public FlxProject read(InputStream is) throws IOException
	{
		Object root = new XMLObjectReader().read(is);
		if (root instanceof FlxProject)
		{
			return (FlxProject) root;
		}
		
		throw new IOException("Unexpected root class: " + root.getClass());
	}
}
