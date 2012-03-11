package bc.flash.flx;

public class FlxDOMDocument extends FlxElement
{
	private FlxContainer folders;
	private FlxContainer media;
	private FlxContainer symbols;
	private FlxContainer timelines;
	
	public FlxDOMDocument()
	{
		folders = new FlxContainer("folders");
		media = new FlxContainer("media");
		symbols = new FlxContainer("symbols");
		timelines = new FlxContainer("timelines");
		
		addElement(folders);
		addElement(media);
		addElement(symbols);
		addElement(timelines);
	}

	public FlxDOMFolderItem addFolder(String name)
	{
		FlxDOMFolderItem item = new FlxDOMFolderItem(name);
		folders.addElement(item);
		return item;
	}
	
	public FlxDOMBitmapItem addBitmap(String name)
	{
		FlxDOMBitmapItem item = new FlxDOMBitmapItem(name);
		media.addElement(item);
		return item;
	}
	
	public FlxInclude addInclude()
	{
		FlxInclude item = new FlxInclude();
		symbols.addElement(item);
		return item;
	}
	
	public FlxDOMTimeline addTimeline(String name)
	{
		FlxDOMTimeline item = new FlxDOMTimeline(name);
		timelines.addElement(item);
		return item;
	}
	
	public FlxContainer getFolders()
	{
		return folders;
	}

	public FlxContainer getMedia()
	{
		return media;
	}

	public FlxContainer getSymbols()
	{
		return symbols;
	}

	public FlxContainer getTimelines()
	{
		return timelines;
	}
}
