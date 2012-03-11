package bc.flash.flx;

public class FlxDOMFolderItem extends FlxItem
{
	public FlxDOMFolderItem(String name)
	{
		super(name);
		addAttribute("isExpanded", true);
	}	
}
