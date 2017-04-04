package lecture1;

/**
 * Sample "flat" data structure that can easily be serialized 
 * in Comma-separated value (CSV) format, or similarly
 * using other conventions.
 */
public class Product1
{
	private String aName;
	private int aId;
	
	public Product1(String pName, int pId)
	{
		aName = pName;
		aId = pId;
	}
	
	public String getName()
	{
		return aName;
	}
	
	public int getId()
	{
		return aId;
	}
	
	@Override
	public String toString()
	{
		return aId + "," + aName;
	}
}
