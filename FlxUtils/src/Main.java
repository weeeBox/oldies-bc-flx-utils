import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import bc.craqua.resources.BcBitmap;
import bc.craqua.resources.BcDisplayObject;
import bc.craqua.resources.BcDisplayObjectContainer;
import bc.craqua.resources.BcEnemyData;
import bc.craqua.resources.BcEnemyDataReader;
import bc.craqua.resources.BcModel;
import bc.craqua.resources.BcSprite;
import bc.craqua.util.BcMatrix;
import bc.craqua.util.BcPoint;
import bc.flash.flx.FlxDOMBitmapItem;
import bc.flash.flx.FlxDOMDocument;
import bc.flash.flx.FlxDOMFrame;
import bc.flash.flx.FlxDOMLayer;
import bc.flash.flx.FlxDOMTimeline;
import bc.flash.flx.FlxInclude;
import bc.flash.flx.FlxProject;
import bc.flash.flx.library.FlxBitmap;
import bc.flash.flx.library.FlxBitmapInstance;
import bc.flash.flx.library.FlxGraphic;
import bc.flash.flx.library.FlxLibraryFolder;
import bc.flash.flx.library.FlxSymbol;
import bc.flash.flx.library.FlxSymbolInstance;
import bc.flash.flx.template.FlxProjectTemplate;
import bc.flash.flx.utils.FlxUtils;
import bc.utils.BcFileUtils;
import bc.utils.BcImageUtils;

public class Main
{
	private static final String ENEMY_PREFIX = "enemy_";

	public static void main(String[] args) throws IOException
	{
//		String projName = args[0];
//		File outDir = new File(args[1]);
//		File enemyDataFile = new File(args[2]);
//		
//		BcEnemyData enemyData = readEnemyData(enemyDataFile);
//		if (enemyData == null)
//		{
//			throw new IOException("Unable to read enemy data: " + enemyDataFile);
//		}
//		
//		FlxProject project = new FlxProject(projName);
//		
//		String symbolName = enemyData.getName();
//		String folderName = ENEMY_PREFIX + symbolName;
//		FlxLibraryFolder rootFolder = project.getLibrary().addFolder(folderName);
//		
//		List<BcModel> models = enemyData.getModels();
//		for (BcModel model : models)
//		{
//			rootFolder.add(createSymbol(model.getId(), model));
//		}
//		
//		File projDir = new File(outDir, projName);
//		projDir.mkdir();
//		
//		project.write(projDir);
		
		File templateDir = new File("template");
		FlxProjectTemplate template = new FlxProjectTemplate(templateDir);
		template.load();
		
		System.out.println("Done");
	}

	private static FlxSymbol createSymbol(String name, BcDisplayObjectContainer container)
	{
		FlxGraphic symbol = new FlxGraphic(name);
		
		List<BcDisplayObject> children = container.children();
		for (BcDisplayObject child : children)
		{
			if (child instanceof BcSprite)
			{
				BcSprite sprite = (BcSprite) child;
				List<BcDisplayObject> spriteChildren = sprite.children();
				if (spriteChildren.size() == 1 && spriteChildren.get(0) instanceof BcBitmap)
				{
					BcBitmap bcBitmap = (BcBitmap) spriteChildren.get(0);
					BufferedImage image = BcImageUtils.readImage(bcBitmap.getFile());
					
					FlxBitmap flxBitmap = new FlxBitmap(sprite.getId(), image);
					FlxBitmapInstance bitmapInstance = flxBitmap.createInstance();
					setMatrix(bcBitmap, bitmapInstance);
					
					symbol.addChild(bitmapInstance);
				}
				else
				{
					FlxSymbol childSymbol = createSymbol(sprite.getId(), sprite);
					symbol.addChild(childSymbol.createInstance());
				}				
			}
			else if (child instanceof BcBitmap)
			{
				BcBitmap bcBitmap = (BcBitmap) child;
				File file = bcBitmap.getFile();
				BufferedImage image = BcImageUtils.readImage(file);
				
				FlxBitmap flxBitmap = new FlxBitmap(BcFileUtils.nameNoExt(file), image);
				FlxBitmapInstance bitmapInstance = flxBitmap.createInstance();
				setMatrix(bcBitmap, bitmapInstance);
				
				symbol.addChild(bitmapInstance);
			}
		}
		
		return symbol;
	}

	private static void setMatrix(BcBitmap bcBitmap, FlxSymbolInstance symbolInstance)
	{
		BcMatrix matrix = symbolInstance.getMatrix();
		
		BcPoint pivot = bcBitmap.getPivot();
		float scale = bcBitmap.getScale();
		float rotation = bcBitmap.getRotation();
		
		if (pivot.x != 0 || pivot.y != 0) matrix.translate(-pivot.x, -pivot.y);
		if (scale != 1.0) matrix.scale(scale, scale);
		if (rotation != 0) matrix.rotate(rotation);
	}

	private static FlxBitmap createBitmap(BcBitmap bitmapData) throws IOException
	{
		File file = bitmapData.getFile();
		String name = file.getName();
		BufferedImage image = BcImageUtils.readImage(file);

		if (image == null)
		{
			throw new IOException("Unable to read image: " + file);
		}
		
		return new FlxBitmap(name, image);
	}

	private static void addEnemyData(FlxDOMDocument doc, BcEnemyData enemyData)
	{
		String folderName = ENEMY_PREFIX + enemyData.getName();
		
		// folders
		doc.addFolder(folderName);
		
		// resources
		List<BcBitmap> bitmaps = enemyData.getBitmaps();
		for (BcBitmap bm : bitmaps)
		{
			String fileRef = folderName + "\\" + bm.getId();
			File bitmapFile = bm.getFile();
			
			BufferedImage image = BcImageUtils.readImage(bitmapFile);
			
			FlxDOMBitmapItem bitmap = doc.addBitmap(folderName + "/" + bm.getId());
			bitmap.addAttribute("sourceExternalFilepath", String.format(".\\LIBRARY\\%s\\%s", folderName, bitmapFile.getName()));
			bitmap.addAttribute("sourceLastImported", System.currentTimeMillis());
			bitmap.addAttribute("externalFileSize", bitmapFile.length());
			bitmap.addAttribute("allowSmoothing", true);
			bitmap.addAttribute("useImportedJPEGData", false);
			bitmap.addAttribute("compressionType", "lossless");
			bitmap.addAttribute("originalCompressionType", "lossless");
			bitmap.addAttribute("originalCompressionType", "lossless");
			bitmap.addAttribute("quality", 50);
			bitmap.addAttribute("href", fileRef);
			bitmap.addAttribute("frameRight", FlxUtils.pixelToTwips(image.getWidth()));
			bitmap.addAttribute("frameBottom", FlxUtils.pixelToTwips(image.getHeight()));
		}
		
		// symbols
		FlxInclude include = doc.addInclude();
		include.addAttribute("href", String.format("%s/%s.xml", folderName, enemyData.getName()));
		include.addAttribute("loadImmediate", false);
		
		// timelines
		FlxDOMTimeline timeline = doc.addTimeline("Scene 1");
		FlxDOMLayer layer = timeline.addLayer("Layer 1");
		layer.addAttribute("color", "#4FFF4F");
		
		// frame
		FlxDOMFrame frame = layer.addFrame();
		frame.addAttribute("index", "0");
		frame.addAttribute("keyMode", "9728");
	}

	private static BcEnemyData readEnemyData(File file)
	{
		try
		{
			return BcEnemyDataReader.read(file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
