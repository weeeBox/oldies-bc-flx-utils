package bc.flash.flx.library;

import bc.craqua.util.BcMatrix;

public class FlxSymbolInstance
{
	private FlxSymbol symbol;
	private BcMatrix matrix;
	
	public FlxSymbolInstance(FlxSymbol symbol)
	{
		this.symbol = symbol;
		matrix = new BcMatrix();
	}
	
	public FlxSymbol getSymbol()
	{
		return symbol;
	}
	
	public BcMatrix getMatrix()
	{
		return matrix;
	}
}
