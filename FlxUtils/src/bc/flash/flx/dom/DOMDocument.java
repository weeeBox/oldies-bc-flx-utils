package bc.flash.flx.dom;

public class DOMDocument extends DOMItem
{
	public Color backgroundColor;
	public Color gridColor;
	public Color guidesColor;
	public int width;
	public int height;
	public int frameRate;
	public String currentTimeline;
	public String xflVersion;
	public String creatorInfo;
	public String platform;
	public String versionInfo;
	public String majorVersion;
	public String buildNumber;
	public String gridSpacingX;
	public String gridSpacingY;
	public String snapAlignBorderSpacing;
	public String timelineLabelWidth;
	public String nextSceneIdentifier;
	public String playOptionsPlayLoop;
	public String playOptionsPlayPages;
	public String playOptionsPlayFrameActions;
	public String hasAccessibleData;
	
	public Group<DOMFolderItem> folders = new Group<DOMFolderItem>();
	public Group<DOMFontItem> fonts = new Group<DOMFontItem>();
	public Group<DOMMediaItem> media = new Group<DOMMediaItem>();
	public Group<Include> symbols = new Group<Include>();
	public Group<DOMTimeline> timelines = new Group<DOMTimeline>();
	public Group<Object> publishHistory = new Group<Object>();
 
}
