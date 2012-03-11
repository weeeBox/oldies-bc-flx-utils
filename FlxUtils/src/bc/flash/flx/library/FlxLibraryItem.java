package bc.flash.flx.library;

public abstract class FlxLibraryItem
{
	private String name;

	public FlxLibraryItem(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
