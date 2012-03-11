package bc.flash.flx;

public abstract class FlxItem extends FlxElement
{
	private static final String ITEM_ID_BASE="4f5afde7";
	private static int itemIdNext;
	
	private String itemId;
	public FlxItem(String name)
	{
		super(name);
		itemId = nextItemId();
		addAttribute("itemID", itemId);
	}
	
	public String getItemId()
	{
		return itemId;
	}
	
	private static String nextItemId()
	{
		return String.format("%s-%08d", ITEM_ID_BASE, ++itemIdNext);
	}
}
