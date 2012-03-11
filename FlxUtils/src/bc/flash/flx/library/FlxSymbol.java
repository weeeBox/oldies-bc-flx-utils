package bc.flash.flx.library;

import java.util.ArrayList;
import java.util.List;

public abstract class FlxSymbol extends FlxLibraryItem
{
	private List<FlxSymbolInstance> children;
	
	public FlxSymbol(String name)
	{
		super(name);
		children = new ArrayList<FlxSymbolInstance>();
	}
	
	public abstract FlxSymbolInstance createInstance();
	
	public void addChild(FlxSymbolInstance child)
	{
		children.add(child);
	}
	
	public List<FlxSymbolInstance> children()
	{
		return children;
	}
}
