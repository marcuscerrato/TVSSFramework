package TVSSUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.apache.commons.lang.RandomStringUtils;
import org.openimaj.audio.SampleChunk;
import org.openimaj.video.xuggle.XuggleAudio;

public class AudioStreamSelector 
{
	public static XuggleAudio separateAudioStream(XuggleAudio inputAudio, int numStreams, int selectedStream) throws Exception
	{
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		long sampleFrames = 0;
		int cardinality = 0;
		SampleChunk sc = null;
		while( (sc = inputAudio.nextSampleChunk() ) != null )
		{    			
			if(cardinality % numStreams == selectedStream)
			{
        		byteArrayOutputStream.flush();
       			byteArrayOutputStream.write(sc.getSamples());
        		byteArrayOutputStream.flush();
        		sampleFrames += sc.getNumberOfSamples();
			}
			cardinality++;
		}
    	ByteArrayInputStream bais = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    	AudioInputStream ais = new AudioInputStream(bais, inputAudio.getFormat().getJavaAudioFormat(), sampleFrames/inputAudio.getFormat().getNumChannels());
    	String tempFilename = RandomStringUtils.randomAlphanumeric(8) + ".wav";
    	File tempFile = new File(tempFilename);
    	AudioSystem.write(ais, AudioFileFormat.Type.WAVE, tempFile);
    	XuggleAudio singleStreamAudio = new XuggleAudio(tempFile);
    	tempFile.delete();
		return singleStreamAudio;		
	}
}
