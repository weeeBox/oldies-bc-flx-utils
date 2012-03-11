package bc.flash.flx;

public class FlxDOMTimeline extends FlxElement
{
	private FlxContainer layers;
	
	public FlxDOMTimeline(String name)
	{
		super(name);
		layers = new FlxContainer("layers");
		addElement(layers);
	}
	
	public FlxDOMLayer addLayer(String name)
	{
		FlxDOMLayer item = new FlxDOMLayer(name);
		layers.addElement(item);
		return item;
	}
}
