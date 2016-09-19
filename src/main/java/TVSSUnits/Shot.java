package TVSSUnits;

import java.util.ArrayList;

import org.openimaj.image.MBFImage;
import org.openimaj.image.feature.local.keypoints.Keypoint;
import org.openimaj.math.statistics.distribution.Histogram;
import org.openimaj.video.processing.shotdetector.ShotBoundary;
import org.openimaj.video.processing.shotdetector.VideoKeyframe;
import org.openimaj.video.xuggle.XuggleVideo;

public class Shot
{
	private ShotBoundary<MBFImage>				startBoundary;
	private ShotBoundary<MBFImage>				endBoundary;
	private ArrayList<VideoKeyframe<MBFImage>>	keyFrameList;			/*TODO Must discuss if this field belongs here*/
	private ArrayList<Keypoint> 				siftKeypointList;		/*TODO Must discuss if this field belongs here*/
	private Histogram 							visualWordHistogram;	/*TODO Must discuss if this field belongs here*/
	
	public ShotBoundary<MBFImage> getStartBoundary()
	{
		return this.startBoundary;	
	}
	
	public ShotBoundary<MBFImage> getEndBoundary()
	{
		return this.endBoundary;
	}	
	
	public ArrayList<VideoKeyframe<MBFImage>> getKeyFrameList()
	{
		return this.keyFrameList;
	}
	
	public ArrayList<Keypoint> getSiftKeypointList() 
	{
		return siftKeypointList;
	}
	
	public Histogram getVisualWordHistogram() 
	{
		return visualWordHistogram;
	}

	public void setVisualWordHistogram(Histogram visualWordHistogram) 
	{
		this.visualWordHistogram = visualWordHistogram;
	}
	
	public void addKeyFrame(VideoKeyframe<MBFImage> keyFrame)
	{
		this.keyFrameList.add(keyFrame);
	}
	
	public void addSiftKeypoint(Keypoint keypoint)
	{
		this.siftKeypointList.add(keypoint);
	}
	

	public Shot(ShotBoundary<MBFImage> startBoundary, ShotBoundary<MBFImage> endBoundary)
	{
		this.startBoundary = startBoundary;
		this.endBoundary = endBoundary;
		this.keyFrameList = new ArrayList<VideoKeyframe<MBFImage>>();
		this.siftKeypointList = new ArrayList<Keypoint>();
	}	
	
	public Shot(XuggleVideo source, long startFrameNumber, long endFrameNumber)
	{
		source.setCurrentFrameIndex(startFrameNumber);
		this.startBoundary = new ShotBoundary<MBFImage>(source.getCurrentTimecode().clone());
		source.setCurrentFrameIndex(endFrameNumber);		
		this.endBoundary = new ShotBoundary<MBFImage>(source.getCurrentTimecode().clone());
		this.keyFrameList = new ArrayList<VideoKeyframe<MBFImage>>();
		this.siftKeypointList = new ArrayList<Keypoint>();
		
	}
		
}
