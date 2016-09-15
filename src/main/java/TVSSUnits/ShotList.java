package TVSSUnits;

import java.util.ArrayList;

public class ShotList 
{
	private ArrayList<Shot> list;

	public ShotList() 
	{
		this.list = new ArrayList<Shot>();
	}
	
	public void addShot(Shot newShot)
	{
		this.list.add(newShot);
	}
	
	public Shot getShot(int index)
	{
		return this.list.get(index);
	}
	
	public ArrayList<Shot> getList()
	{
		return this.list;
	}
	
	public int listSize()
	{
		return this.list.size();
	}
}
