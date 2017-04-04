package lecture1;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Product object in the JavaBeans framework.
 */
public class Product3
{
	private String aName = "default";
	private int aId = -1;
	private HashMap<String,String> aProperties = new HashMap<>();
	private Product3 aRelated;
	
	public Product3()
	{}
	
	public Product3(String pName, int pId)
	{
		aName = pName;
		aId = pId;
	}
	
	public String getName()
	{
		return aName;
	}

	public void setName(String pName)
	{
		aName = pName;
	}

	public int getId()
	{
		return aId;
	}

	public void setId(int pId)
	{
		aId = pId;
	}

	public Product3 getRelated()
	{
		return aRelated;
	}

	public void setRelated(Product3 pRelated)
	{
		aRelated = pRelated;
	}
	
	public void setProperty(String pKey, String pValue)
	{
		aProperties.put(pKey, pValue);
	}
	
	public String getProperty(String pKey)
	{
		return aProperties.get(pKey);
	}
	
	public Iterator<String> getPropertyNames()
	{
		return aProperties.keySet().iterator();
	}
	
	@Override
	public String toString()
	{
		return aId + "," + aName + " " + aProperties;
	}
}
