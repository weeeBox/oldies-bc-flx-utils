package bc.flash.flx;

import java.io.File;
import java.io.IOException;

import bc.flash.flx.library.FlxLibrary;
import bc.utils.BcFileUtils;

public class FlxProject
{
	private FlxDOMDocument document;
	private FlxLibrary library;
	private String name;
	
	public FlxProject(String name)
	{
		this.name = name;
		
		document = new FlxDOMDocument();
		library = new FlxLibrary();
	}
	
	public FlxLibrary getLibrary()
	{
		return library;
	}
	
	public FlxDOMDocument getDocument()
	{
		return document;
	}
	
	public void write(File dir) throws IOException
	{
		File projFile = new File(dir, name + ".xfl");
		BcFileUtils.writeLines(projFile, "PROXY-CS5");
		
		File docFile = new File(dir, "DOMDocument.xml");
		File libDir = new File(dir, "LIBRARY");
		libDir.mkdir();
	}
}
