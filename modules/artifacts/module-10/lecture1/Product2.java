package lecture1;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;

/**
 * Sample hierarchical structure..
 */
public class Product2
{
	private String aName;
	private int aId;
	private HashMap<String,String> aProperties = new HashMap<>();
	
	public Product2(String pName, int pId)
	{
		aName = pName;
		aId = pId;
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
	
	public JSONObject toJSONObject()
	{
		// First a properties object where keys become fields
		JSONObject properties = new JSONObject();
		for( String key : aProperties.keySet() )
		{
			properties.put(key, aProperties.get(key));
		}
		
		// Then construct the actual product object
		JSONObject product = new JSONObject();
		product.put("name", aName);
		product.put("id", aId);
		product.put("properties", properties);
		
		return product;
	}
}
