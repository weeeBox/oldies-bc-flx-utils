package bc.flash.flx.library;

public class FlxGraphic extends FlxSymbol
{
	public FlxGraphic(String name)
	{
		super(name);
	}

	@Override
	public FlxGraphicInstance createInstance()
	{
		return new FlxGraphicInstance(this);
	}
}
