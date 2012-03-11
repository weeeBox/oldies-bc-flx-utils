package bc.flash.flx.library;

import bc.craqua.util.BcMatrix;

public class FlxDOMBitmapInstance extends FlxLibraryItem
{
	private BcMatrix matrix;
	
	public FlxDOMBitmapInstance(String name)
	{
		super(name);
		matrix = new BcMatrix();
	}
	
	public BcMatrix getMatrix()
	{
		return matrix;
	}
}
