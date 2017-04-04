package lecture1;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;

/**
 * Sample hierarchical structure, initially without links,
 * but with aRelated added in, demonstrates the weakness
 * of JSON serialization for graphs.
 */
public class Product2a
{
	private String aName;
	private int aId;
	private HashMap<String,String> aProperties = new HashMap<>();
	private Product2a aRelated;
	
	public Product2a(String pName, int pId)
	{
		aName = pName;
		aId = pId;
	}
	
	public void setRelated(Product2a p)
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
		if( aRelated != null )
		{
			product.put("related", aRelated.toJSONObject());
		}

		return product;
	}
}
