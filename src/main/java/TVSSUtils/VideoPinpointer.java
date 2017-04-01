

/*This class is a Technical adjustment to make setCurrentFrameIndex properly work.
/*Problem is setCurrentFrameIndex doesn't seems to properly change the frame of the video. Instead, 
frames must be advanced manually, one by one. This is slow, so an heuristic was inserted (the if inside the for)
to speed it up.*/

/*For some unkown reason, setCurrentFrameIndex doesn't 
 * set the right index on the video... maybe "Missing reference picture" 
 * messages are someway related*/


package TVSSUtils;

import org.openimaj.video.xuggle.XuggleVideo;

public class VideoPinpointer 
{
	public static void seek(XuggleVideo video, long targetFrame)
	{
		if(targetFrame < video.countFrames())
		{
			while(video.hasNextFrame() && video.getCurrentFrameIndex() < targetFrame)
			{
				video.getNextFrame();
			}
		}else
		{
			while(video.hasNextFrame() && video.getCurrentFrameIndex() < (video.countFrames() - 1))
			{
				video.getNextFrame();
			}
		}
	}
	
/*   public static void seek(XuggleVideo video, long targetFrame)
	{
		int counter = 0;
		long lastFrame = video.getCurrentFrameIndex();
		while(video.getCurrentFrameIndex() < targetFrame && !(counter == 30))
		{
			video.getNextFrame();
			if(video.getCurrentFrameIndex() < (targetFrame - 200))
			{
				try
				{
					video.setCurrentFrameIndex(targetFrame - 100);
				}catch(Exception e){};
			}
			if(video.getCurrentFrameIndex() == lastFrame)
			{
				counter++;
			}
			else
			{
				lastFrame = video.getCurrentFrameIndex();
				counter = 0;
			}
		}		
	}
*/	

}
