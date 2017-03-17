package TVSSUnits;

import java.util.ArrayList;

import org.openimaj.feature.DoubleFV;
import org.openimaj.feature.local.list.LocalFeatureList;
import org.openimaj.feature.local.list.MemoryLocalFeatureList;
import org.openimaj.image.MBFImage;
import org.openimaj.image.feature.local.keypoints.Keypoint;
import org.openimaj.math.statistics.distribution.Histogram;
import org.openimaj.video.processing.shotdetector.ShotBoundary;
import org.openimaj.video.processing.shotdetector.VideoKeyframe;
import org.openimaj.video.xuggle.XuggleVideo;

import TVSSUtils.VideoPinpointer;

public class Shot
{
	private ShotBoundary<MBFImage>				startBoundary;
	private ShotBoundary<MBFImage>				endBoundary;
	private ArrayList<VideoKeyframe<MBFImage>>	keyFrameList;			/*TODO Must discuss if this field belongs here*/
	private LocalFeatureList<Keypoint> 			siftKeypointList;		/*TODO Must discuss if this field belongs here*/
	private ArrayList<DoubleFV>					mfccDescriptorsList;	/*TODO Must discuss if this field belongs here*/
	private Histogram							featureWordHistogram;
	//private Histogram 							visualWordHistogram;	/*TODO Must discuss if this field belongs here*/
	//private Histogram 							auralWordHistogram;		/*TODO Must discuss if this field belongs here*/
	
	
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
	
/*	public Histogram getVisualWordHistogram() 
	{
		return visualWordHistogram;
	}
	
	public Histogram getAuralWordHistogram() 
	{
		return auralWordHistogram;
	}

	public void setVisualWordHistogram(Histogram visualWordHistogram) 
	{
		this.visualWordHistogram = visualWordHistogram;
	}
	
	public void setAuralWordHistogram(Histogram auralWordHistogram) 
	{
		this.auralWordHistogram = auralWordHistogram;
	}
*/	
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
	

	public Shot(ShotBoundary<MBFImage> startBoundary, ShotBoundary<MBFImage> endBoundary)
	{		
		this.startBoundary = startBoundary;
		this.endBoundary = endBoundary;
		this.keyFrameList = new ArrayList<VideoKeyframe<MBFImage>>();
		this.siftKeypointList = new MemoryLocalFeatureList<Keypoint>();
		this.mfccDescriptorsList = new ArrayList<DoubleFV>();
	}	
	
	public Shot(XuggleVideo source, long startFrameNumber, long endFrameNumber)
	{
		VideoPinpointer.seek(source, startFrameNumber); //Always use this before setCurrentFrameIndex
		source.setCurrentFrameIndex(startFrameNumber);
		this.startBoundary = new ShotBoundary<MBFImage>(source.getCurrentTimecode().clone());		
		
		VideoPinpointer.seek(source, endFrameNumber);	 //Always use this before setCurrentFrameIndex	
		source.setCurrentFrameIndex(endFrameNumber);

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
	}
	
	public Shot()
	{
		this.keyFrameList = new ArrayList<VideoKeyframe<MBFImage>>();
		this.siftKeypointList = new MemoryLocalFeatureList<Keypoint>();
		this.mfccDescriptorsList = new ArrayList<DoubleFV>();
	}
		
}
