package TVSSUnits;

public class Shot 
{
	private int firstFrame;
	private int lastFrame;
	private Keyframe keyframes[];
	
	public Shot(int firstFrame, int lastFrame, Keyframe keyframes[]) 
	{
		this.firstFrame = firstFrame;
		this.lastFrame = lastFrame;
		this.keyframes = keyframes;
	}

	public int getFirstFrame() 
	{
		return firstFrame;
	}

	public int getLastFrame() 
	{
		return lastFrame;
	}

	public Keyframe[] getKeyframes() 
	{
		return keyframes;
	}
}
