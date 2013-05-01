package bc.flash.flx.dom;

public class Color
{
	public int value;
	
	public Color(int value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return Integer.toString(value, 16);
	}
}
