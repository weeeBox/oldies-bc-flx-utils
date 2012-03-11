package bc.flash.flx;

public class FlxContainer extends FlxElement
{
	private String name;

	public FlxContainer(String name)
	{
		this.name = name;
	}

	@Override
	public String elementName()
	{
		return name;
	}
}
