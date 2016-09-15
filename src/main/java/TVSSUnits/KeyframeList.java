package TVSSUnits;

import java.util.ArrayList;

import org.openimaj.image.MBFImage;
import org.openimaj.video.processing.shotdetector.VideoKeyframe;

public class KeyframeList 
{
	private ArrayList<VideoKeyframe<MBFImage>> list;
	
	public KeyframeList() 
	{
		this.list = new ArrayList<VideoKeyframe<MBFImage>>();
	}
	
	public void addKeyframe(VideoKeyframe<MBFImage> keyframe)
	{
		this.list.add(keyframe);
	}
	
	public VideoKeyframe<MBFImage> getKeyframe(int i)
	{
		return this.list.get(i);
	}
	
	public int size()
	{
		return this.list.size();
	}
}
