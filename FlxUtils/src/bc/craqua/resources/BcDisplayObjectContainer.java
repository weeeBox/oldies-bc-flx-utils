package bc.craqua.resources;

import java.util.ArrayList;
import java.util.List;

public class BcDisplayObjectContainer extends BcDisplayObject
{
	private List<BcDisplayObject> children;
	
	public BcDisplayObjectContainer(String id)
	{
		super(id);
		children = new ArrayList<BcDisplayObject>();
	}
	
	public void addChild(BcDisplayObject child)
	{
		children.add(child);
	}
	
	public List<BcDisplayObject> children()
	{
		return children;
	}
}
