package lecture1;

import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Statement;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class SerializationExampleXML1
{
	public static void main(String[] args) throws Exception
	{
		Product3 product1 = new Product3("TV", 1);
		Product3 product2 = new Product3("Phone", 2);
		Product3 product3 = new Product3("Radio", 3);
		
		product1.setRelated(product2);
		product2.setRelated(product1);

		product1.setProperty("screen", "bw");
		product1.setProperty("weight", "950");
		product2.setProperty("os", "Win");
		product3.setProperty("default", "CBC");
		
		Product3[] products = {product1, product2, product3};
		write(products);
		
		Product3[] readProducts = read();
		
		print(readProducts);
	}
	
	private static void write(Product3[] pProducts) throws IOException
	{
		try(XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("products.xml"))))
		{
			configureXMLEncoder(encoder);
			encoder.writeObject(pProducts);
		}
	}
	
	private static Product3[] read() throws IOException
	{
		try(XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("products.xml"))))
		{
			return (Product3[])decoder.readObject();
		}
	}
	
	private static void print(Product3[] pProducts)
	{
		for( Product3 product : pProducts )
		{
			System.out.println(product);
		}
	}
	
	private static void configureXMLEncoder(XMLEncoder pEncoder)
	{
		pEncoder.setPersistenceDelegate(Product3.class, new DefaultPersistenceDelegate()
		{
			protected void initialize(Class<?> pType, Object pOldInstance, Object pNewInstance, Encoder pOut) 
			{
				super.initialize(pType, pOldInstance, pNewInstance, pOut);
				for(Iterator<String> namesIt = ((Product3)pOldInstance).getPropertyNames(); namesIt.hasNext(); )
				{
					String property = namesIt.next();
					pOut.writeStatement( new Statement(pOldInstance, "setProperty", new Object[]{ property, ((Product3)pOldInstance).getProperty(property) }) );
				}
			}
		});
	}
}
