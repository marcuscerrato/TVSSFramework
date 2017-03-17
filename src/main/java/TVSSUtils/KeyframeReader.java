package TVSSUtils;

import java.io.FileReader;

import org.openimaj.image.MBFImage;
import org.openimaj.video.processing.shotdetector.VideoKeyframe;
import org.openimaj.video.xuggle.XuggleVideo;

import com.opencsv.CSVReader;

import TVSSUnits.Shot;
import TVSSUnits.ShotList;


public class KeyframeReader 
{
	public static ShotList readFromCSV(XuggleVideo sourceVideo, String filename) throws Exception
	{
		ShotList shotList = new ShotList();		
		CSVReader reader = new CSVReader(new FileReader(filename), '\t');
		String [] line;
		long lastShot = -1;
		while ((line = reader.readNext()) != null) 
		{
			//Verify if shot changes
			long shotNum = Long.parseLong(line[0]);
			if(shotNum != lastShot)
			{
				lastShot = shotNum;
				shotList.addShot(new Shot(sourceVideo, 0, 0));
			}
			//Insert keyframe into actual shot
			long keyframeNumber = Long.parseLong(line[1]);

			VideoPinpointer.seek(sourceVideo, keyframeNumber); //Always use this before setCurrentFrameIndex
			sourceVideo.setCurrentFrameIndex(keyframeNumber);
			
			System.out.println("Reading keyframe: "+ keyframeNumber);
			VideoKeyframe<MBFImage> videoKeyframe = new VideoKeyframe<MBFImage>(sourceVideo.getCurrentTimecode().clone(), sourceVideo.getCurrentFrame().clone());
			shotList.getShot((int)shotNum).addKeyFrame(videoKeyframe);
		}
		reader.close();
		return shotList;
	}

}
