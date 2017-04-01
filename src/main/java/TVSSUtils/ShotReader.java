package TVSSUtils;

import java.io.FileReader;

import com.opencsv.CSVReader;

import TVSSUnits.Shot;
import TVSSUnits.ShotList;

public class ShotReader 
{
	public static ShotList readFromCSV(String filename) throws Exception
	{		
		ShotList shotList = new ShotList();		
		CSVReader reader = new CSVReader(new FileReader(filename), '\t');
		String [] line;
		while ((line = reader.readNext()) != null) 
		{			
			shotList.addShot(new Shot(Long.parseLong(line[0]), Long.parseLong(line[1])));
		}
		reader.close();
		return shotList;
	}
	
	/*public static ShotList readFromCSV(XuggleVideo sourceVideo, String filename) throws Exception
	{
		ShotList shotList = new ShotList();		
		CSVReader reader = new CSVReader(new FileReader(filename), '\t');
		String [] line;
		while ((line = reader.readNext()) != null) 
		{			
			shotList.addShot(new Shot(sourceVideo, Long.parseLong(line[0]), Long.parseLong(line[1])));
		}
		reader.close();
		return shotList;
	}*/
}
