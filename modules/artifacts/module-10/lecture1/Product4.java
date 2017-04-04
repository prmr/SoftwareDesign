package lecture1;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

/**
 */
public class Product4 implements Serializable
{
	private String aName;
	private int aId;
	private transient HashMap<String,String> aProperties = new HashMap<>();
	private Product4 aRelated;
	
	public Product4(String pName, int pId)
	{
		aName = pName;
		aId = pId;
	}
	
	public void setRelated(Product4 p)
	{
		aRelated = p;
	}
	
	public void setProperty(String pKey, String pValue)
	{
		aProperties.put(pKey, pValue);
	}
	
	public String getProperties(String pKey)
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
