package lecture1;

import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Sample "flat" data structure that can easily be serialized 
 * in Comma-separated value (CSV) format, or similarly
 * using other conventions, more robust and better encapsulated than
 * product1.
 */
public class Product1a
{
	private String aName;
	private int aId;
	
	public Product1a(String pName, int pId)
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
	
	public String serialize()
	{
		return "\"" + aName + "\"," + aId;
	}
	
	public Product1a(String pSerialized)
	{
		try
		{
			CSVParser parser = CSVParser.parse(pSerialized, CSVFormat.DEFAULT);
			List<CSVRecord> records = parser.getRecords();
			if( records.size() != 1 )
			{
				throw new DeserializationException();
			}
			CSVRecord record = records.get(0);
			if(record.size() != 2 )
			{
				throw new DeserializationException();
			}
			aName = record.get(0);
			aId = Integer.parseInt(record.get(1));
		}
		catch(IOException | NumberFormatException e)
		{
			throw new DeserializationException(e);
		}
	}
}
