package bc.flash.flx.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import bc.flash.flx.FlxProject;
import bc.flash.flx.dom.DOMDocument;
import bc.flash.flx.io.xml.DOMXmlReader;

public class FlxProjectReader
{
	private static final String DOCUMENT_FILENAME = "DOMDocument.xml";

	public FlxProject read(File projDir) throws IOException
	{
		if (projDir == null)
		{
			throw new NullPointerException("'projDir' is null");
		}
		
		if (!projDir.exists())
		{
			throw new FileNotFoundException("Project directory doesn't exist: " + projDir);
		}
		
		if (!projDir.isDirectory())
		{
			throw new IOException("File is not a directory: " + projDir);
		}
		
		String projName = projDir.getName();
		FlxProject proj = new FlxProject(projName);
		
		File docFile = new File(projDir, DOCUMENT_FILENAME);
		proj.document = readDocument(docFile);
		
		return proj;
	}

	private DOMDocument readDocument(File file) throws IOException
	{
		return new DOMXmlReader("bc.flash.flx.dom").read(file, DOMDocument.class);
	}
}
