package bc.flash.flx.dom;

import java.util.ArrayList;
import java.util.List;

public class Group<T> extends DOMItem
{
	public List<T> items = new ArrayList<T>();
	
	public int size()
	{
		return items.size();
	}
}
