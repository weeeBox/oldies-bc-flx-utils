package bc.craqua.resources;

import java.util.ArrayList;
import java.util.List;

public class BcEnemyData
{
	private List<BcBitmap> bitmaps;
	private List<BcModel> models;
	private String name;
	
	public BcEnemyData(String name)
	{
		this.name = name;
		
		bitmaps = new ArrayList<BcBitmap>();
		models = new ArrayList<BcModel>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void add(BcBitmap bitmap)
	{
		bitmaps.add(bitmap);
	}
	
	public void add(BcModel model)
	{
		models.add(model);
	}
	
	public List<BcBitmap> getBitmaps()
	{
		return bitmaps;
	}
	
	public List<BcModel> getModels()
	{
		return models;
	}
}
