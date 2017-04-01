package TVSSUnits;

import java.util.ArrayList;

import org.openimaj.feature.DoubleFV;
import org.openimaj.feature.local.list.LocalFeatureList;
import org.openimaj.feature.local.list.MemoryLocalFeatureList;
import org.openimaj.image.MBFImage;
import org.openimaj.image.feature.local.keypoints.Keypoint;
import org.openimaj.math.statistics.distribution.Histogram;
import org.openimaj.video.processing.shotdetector.VideoKeyframe;


public class Shot
{
/*	private ShotBoundary<MBFImage>				startBoundary;
	private ShotBoundary<MBFImage>				endBoundary;
*/
	private long								startBoundary;
	private long								endBoundary;
	private ArrayList<VideoKeyframe<MBFImage>>	keyFrameList;			
	private LocalFeatureList<Keypoint> 			siftKeypointList;		
	private ArrayList<DoubleFV>					mfccDescriptorsList;	
	private Histogram							featureWordHistogram;
	//private Histogram 							visualWordHistogram;	/*TODO Must discuss if this field belongs here*/
	//private Histogram 							auralWordHistogram;		/*TODO Must discuss if this field belongs here*/
	
	
/*	public ShotBoundary<MBFImage> getStartBoundary()
	{
		return this.startBoundary;	
	}
	
	public ShotBoundary<MBFImage> getEndBoundary()
	{
		return this.endBoundary;
	}*/	
	
	public long getStartBoundary()
	{
		return this.startBoundary;
	}
	
	public long getEndBoundary()
	{
		return this.endBoundary;
	}
	
	public ArrayList<VideoKeyframe<MBFImage>> getKeyFrameList()
	{
		return this.keyFrameList;
	}
	
	public LocalFeatureList<Keypoint> getSiftKeypointList() 
	{
		return siftKeypointList;
	}
	
	public Histogram getFeatureWordHistogram()
	{
		return featureWordHistogram;
	}
	
	public void setFeatureWordHistogram(Histogram featureWordHistogram)
	{
		this.featureWordHistogram = featureWordHistogram;
	}
	
	public void addKeyFrame(VideoKeyframe<MBFImage> keyFrame)
	{
		this.keyFrameList.add(keyFrame);
	}
	
	public void addSiftKeypoint(Keypoint keypoint)
	{
		this.siftKeypointList.add(keypoint);
	}
	
	public void addMfccDescriptor(DoubleFV mfccDescriptor)
	{
		this.mfccDescriptorsList.add(mfccDescriptor);
	}
		
	
	public ArrayList<DoubleFV> getMfccList()
	{
		return this.mfccDescriptorsList;
	}
	
	public Shot(long startFrame, long endFrame)
	{
		this.startBoundary = startFrame;
		this.endBoundary = endFrame;
		this.keyFrameList = new ArrayList<VideoKeyframe<MBFImage>>();
		this.siftKeypointList = new MemoryLocalFeatureList<Keypoint>();
		this.mfccDescriptorsList = new ArrayList<DoubleFV>();
	}
	

/*	public Shot(ShotBoundary<MBFImage> startBoundary, ShotBoundary<MBFImage> endBoundary)
	{		
		this.startBoundary = startBoundary;
		this.endBoundary = endBoundary;
		this.keyFrameList = new ArrayList<VideoKeyframe<MBFImage>>();
		this.siftKeypointList = new MemoryLocalFeatureList<Keypoint>();
		this.mfccDescriptorsList = new ArrayList<DoubleFV>();
	}*/
	
/*	public Shot(ShotBoundary<MBFImage> startBoundarySource, ShotBoundary<MBFImage> endBoundarySource, long startFrameNumber, long endFrameNumber)
	{
		this.startBoundary = startBoundarySource;		
		this.endBoundary = endBoundarySource;
		this.keyFrameList = new ArrayList<VideoKeyframe<MBFImage>>();
		this.siftKeypointList = new MemoryLocalFeatureList<Keypoint>();
		this.mfccDescriptorsList = new ArrayList<DoubleFV>();
	}*/
	
/*	public Shot(XuggleVideo source, long startFrameNumber, long endFrameNumber)
	{
		VideoPinpointer.seek(source, startFrameNumber); //Always use this before setCurrentFrameIndex
		//source.setCurrentFrameIndex(startFrameNumber);
		this.startBoundary = new ShotBoundary<MBFImage>(source.getCurrentTimecode().clone());		
		
		VideoPinpointer.seek(source, endFrameNumber);	 //Always use this before setCurrentFrameIndex	
		//source.setCurrentFrameIndex(endFrameNumber);

		this.endBoundary = new ShotBoundary<MBFImage>(source.getCurrentTimecode().clone());
		this.keyFrameList = new ArrayList<VideoKeyframe<MBFImage>>();
		this.siftKeypointList = new MemoryLocalFeatureList<Keypoint>();
		this.mfccDescriptorsList = new ArrayList<DoubleFV>();
		if(this.endBoundary.getTimecode().getFrameNumber() <= this.startBoundary.getTimecode().getFrameNumber() )
		{
			System.out.println("Problema: " + "startFN(" + startFrameNumber + ") endFN(" + endFrameNumber + ")");
			System.out.println("Problema: " + "startTC(" + this.getStartBoundary().getTimecode().getFrameNumber() + ") endTC(" + this.getEndBoundary().getTimecode().getFrameNumber() + ")");
			System.exit(0);
		}
	}*/
	
	public Shot()
	{
		this.keyFrameList = new ArrayList<VideoKeyframe<MBFImage>>();
		this.siftKeypointList = new MemoryLocalFeatureList<Keypoint>();
		this.mfccDescriptorsList = new ArrayList<DoubleFV>();
	}
		
}
