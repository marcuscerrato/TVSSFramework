package TVSSUtils;

import java.util.ArrayList;

import org.openimaj.image.MBFImage;
import org.openimaj.video.processing.shotdetector.HistogramVideoShotDetector;
import org.openimaj.video.processing.shotdetector.ShotBoundary;
import org.openimaj.video.processing.shotdetector.ShotDetectedListener;
import org.openimaj.video.processing.shotdetector.VideoKeyframe;
import org.openimaj.video.timecode.VideoTimecode;
import org.openimaj.video.xuggle.XuggleVideo;

import TVSSUnits.Shot;
import TVSSUnits.ShotList;

public class ShotSegmentator 
{
	private XuggleVideo videoSource;
	private HistogramVideoShotDetector shotDetector;
	private ShotList shotList;
	/*private KeyframeList keyframeList;*/
	
	public ShotSegmentator(XuggleVideo videoSource) 
	{
		this.videoSource = videoSource;
	}

	private class DetectedShotHandler implements ShotDetectedListener<MBFImage>
	{
		@Override
		public void shotDetected(ShotBoundary<MBFImage> sb, VideoKeyframe<MBFImage> vk) 
		{		
			/*if(videoSource != null && videoSource.getCurrentFrame() != null)
			{
				keyframeList.addKeyframe(new VideoKeyframe<MBFImage>(videoSource.getCurrentTimecode(), videoSource.getCurrentFrame()));
			}*/
		}

		@Override
		public void differentialCalculated(VideoTimecode vt, double d, MBFImage frame) {
			// There isn't anything to do here.			
		}		
	}
	
	public ShotList segmentVideo()
	{
		this.shotList = new ShotList();
		/*this.keyframeList = new KeyframeList();*/
		this.shotDetector = new HistogramVideoShotDetector(videoSource);
		DetectedShotHandler detectedShotHandler = new DetectedShotHandler();
		shotDetector.addShotDetectedListener(detectedShotHandler);
		shotDetector.setFindKeyframes(false); //if true, uses lots of memory 
		shotDetector.process();
		ArrayList<ShotBoundary<MBFImage>> boundariesList = (ArrayList<ShotBoundary<MBFImage>>)shotDetector.getShotBoundaries();
		shotDetector.close();		 
		for(int i = 0; i < (boundariesList.size() - 1); i++)  // uses -1 because we use the next boundary every step
		{
			shotList.addShot(new Shot(boundariesList.get(i).getTimecode().getFrameNumber(), boundariesList.get(i + 1).getTimecode().getFrameNumber() ) );
/*			if(i < this.keyframeList.size())
			{
				shotList.getShot(i).addKeyFrame(this.keyframeList.getKeyframe(i));
			}
*/		}
		return shotList;
	}
}
