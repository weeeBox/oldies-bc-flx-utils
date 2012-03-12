package bc.flash.flx.template;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;

import bc.utils.BcFileUtils;
import bc.utils.xml.XMLHelper;

public class FlxProjectTemplate
{
	private static final String PROJ_DOCUMENT_NAME = "DOMDocument.xml";
	private static final String PROJ_FILE_NAME = "empty.xfl";
	private static final String PROJ_LIB_DIR_NAME = "LIBRARY";

	private File dir;
	private FlxDocumentTemplate document;
	
	public FlxProjectTemplate(File dir)
	{
		this.dir = dir;
	}
	
	public void load() throws IOException
	{
		loadDocument();		
		loadLibrary();
	}

	private void loadDocument() throws IOException
	{
		File file = new File(dir, PROJ_DOCUMENT_NAME);		
		Document xmlDocument = XMLHelper.readDocument(file);
		if (xmlDocument == null)
		{
			throw new IOException("Unable to read xml document: " + file);
		}
		
		document = new FlxDocumentTemplate(xmlDocument);
	}
	
	private void loadLibrary()
	{
		File libDir = new File(dir, PROJ_LIB_DIR_NAME);
		List<File> symbolsFiles = BcFileUtils.listFilesFlatten(libDir, ".xml");
		for (File file : symbolsFiles)
		{
			System.out.println(file);
		}
	}
}
