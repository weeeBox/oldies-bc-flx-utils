package bc.flash.flx;

public class FlxDOMLayer extends FlxElement
{
	private FlxContainer frames;
	
	public FlxDOMLayer(String name)
	{
		super(name);
		frames = new FlxContainer("frames");
		addElement(frames);
	}
	
	public FlxDOMFrame addFrame()
	{
		FlxDOMFrame item = new FlxDOMFrame();
		frames.addElement(item);
		return item;
	}
}
