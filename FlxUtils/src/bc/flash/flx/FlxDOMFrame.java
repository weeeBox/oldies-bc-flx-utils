package bc.flash.flx;

public class FlxDOMFrame extends FlxElement
{
	private FlxContainer elements;
	
	public FlxDOMFrame()
	{
		elements = new FlxContainer("elements");
		addElement(elements);
	}
}
