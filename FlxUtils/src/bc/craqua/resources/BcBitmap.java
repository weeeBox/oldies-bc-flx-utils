package bc.craqua.resources;

import java.io.File;

import bc.craqua.util.BcColor;
import bc.craqua.util.BcPoint;

public class BcBitmap extends BcDisplayObject
{
	private BcPoint pivot;
	private float rotation;
	private float scale;
	private BcColor color;
	private File file;
	
	public BcBitmap(String id, File file)
	{
		super(id);
		this.file = file;
	}

	public File getFile()
	{
		return file;
	}
	
	public BcPoint getPivot()
	{
		return pivot;
	}

	public void setPivot(BcPoint pivot)
	{
		this.pivot = pivot;
	}

	public float getRotation()
	{
		return rotation;
	}

	public void setRotation(float rotation)
	{
		this.rotation = rotation;
	}

	public float getScale()
	{
		return scale;
	}

	public void setScale(float scale)
	{
		this.scale = scale;
	}

	public BcColor getColor()
	{
		return color;
	}

	public void setColor(BcColor color)
	{
		this.color = color;
	}
}
