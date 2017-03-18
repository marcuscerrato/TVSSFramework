package TVSSUtils;

import java.io.File;
import java.io.FileReader;

import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.video.processing.shotdetector.VideoKeyframe;
import org.openimaj.video.xuggle.XuggleVideo;

import com.opencsv.CSVReader;

import TVSSUnits.Shot;
import TVSSUnits.ShotList;


public class KeyframeReader 
{
	/*Read keyframes from folder and place them inside a ShotList*/
	public static ShotList readFromFolder(String keyframeFolder) throws Exception
	{
		ShotList shotList = new ShotList();
		//Scans the folder for files
		File folder = new File(keyframeFolder);
		int fileQuantity = folder.listFiles().length;
		int shotNumber = -1;
		int keyframeNumber = 0;		
		
		for(int i = 0; i < fileQuantity; i++)
		{
			File keyframeFile = new File(keyframeFolder + "s" + String.format("%04d", shotNumber) + "kf" + String.format("%04d", keyframeNumber++) + ".jpg"); 
			while(!keyframeFile.exists())
			{
				shotNumber++;
				keyframeNumber = 0;
				shotList.addShot(new Shot());
				keyframeFile = new File(keyframeFolder + "s" + String.format("%04d", shotNumber) + "kf" + String.format("%04d", keyframeNumber++) + ".jpg");
			}
			MBFImage keyframe = ImageUtilities.readMBF(keyframeFile);
			VideoKeyframe<MBFImage> videoKeyframe = new VideoKeyframe<MBFImage>(null, keyframe);
			shotList.getShot((int)shotNumber).addKeyFrame(videoKeyframe);		
		}
		
/*		while ((line = reader.readNext()) != null) 
		{
			//Verify if shot changes
			long shotNum = Long.parseLong(line[0]);
			if(shotNum != lastShot)
			{
				lastShot = shotNum;
				shotList.addShot(new Shot());
			}
			//Insert keyframe into actual shot
			long keyframeNumber = Long.parseLong(line[1]);		
			System.out.println("Reading keyframe: "+ keyframeNumber);
			MBFImage keyframe = ImageUtilities.readMBF(new File(keyframeFolder + "s" + String.format("%04d", shotNum) + "kf" + String.format("%04d", keyframeNumber) + ".jpg"));
			VideoKeyframe<MBFImage> videoKeyframe = new VideoKeyframe<MBFImage>(null, keyframe);
			shotList.getShot((int)shotNum).addKeyFrame(videoKeyframe);
		}
		reader.close();
*/		return shotList;
	}
	
	/*Extract keyframes from video and return they inside a ShotList*/
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
