package bc.craqua.resources;

import bc.craqua.util.BcPoint;

public class BcSprite extends BcDisplayObjectContainer
{
	private BcPoint position;
	private boolean visible;
	
	public BcSprite(String id)
	{
		super(id);
	}
	
	public void setPosition(BcPoint position)
	{
		this.position = position;
	}
	
	public BcPoint getPosition()
	{
		return position;
	}
	
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
	
	public boolean isVisible()
	{
		return visible;
	}
}
