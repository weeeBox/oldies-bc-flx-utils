package bc.flash.flx.library;

import java.util.List;

public interface FlxLibraryContainer
{
	public FlxLibraryFolder addFolder(String name);
	public void add(FlxLibraryItem item);
	public List<FlxLibraryItem> items();
}
