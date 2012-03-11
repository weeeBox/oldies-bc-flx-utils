package bc.flash.flx.library;

public class FlxMovieClip extends FlxSymbol
{
	public FlxMovieClip(String name)
	{
		super(name);
	}

	@Override
	public FlxMovieClipInstance createInstance()
	{
		return new FlxMovieClipInstance(this);
	}
}
