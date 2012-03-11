package bc.flash.flx.library;

import java.util.ArrayList;
import java.util.List;

public class FlxLibraryFolder extends FlxLibraryItem implements FlxLibraryContainer
{
	private List<FlxLibraryItem> items;

	public FlxLibraryFolder(String name)
	{
		super(name);
		items = new ArrayList<FlxLibraryItem>();
	}
	
	@Override
	public FlxLibraryFolder addFolder(String name)
	{
		FlxLibraryFolder folder = new FlxLibraryFolder(name);
		add(folder);
		return folder;
	}

	@Override
	public void add(FlxLibraryItem item)
	{
		items.add(item);
	}

	@Override
	public List<FlxLibraryItem> items()
	{
		return items;
	}
}
