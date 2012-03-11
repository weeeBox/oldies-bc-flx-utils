package bc.flash.flx;

public class FlxAttribute
{
	private String name;
	private String value;

	public FlxAttribute(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public String getValue()
	{
		return value;
	}
}
