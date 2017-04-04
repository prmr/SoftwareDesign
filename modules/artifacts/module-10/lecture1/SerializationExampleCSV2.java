package lecture1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SerializationExampleCSV2
{
	private static final String PRODUCT_FILE_NAME = "products.csv";
	
	public static void main(String[] args) throws IOException
	{
		Product1a[] products = {new Product1a("Pen, and etrase", 1),
							  new Product1a("Mug", 2),
							  new Product1a("Eraser", 3)};
		
		write(products);
		Product1a[] products2 = read();
		print(products2);
	}
	
	private static void write(Product1a[] pProducts) throws IOException
	{
		try( PrintWriter out = new PrintWriter( new FileWriter(PRODUCT_FILE_NAME)))
		{
			for( Product1a product : pProducts )
			{
				out.println(product.serialize());
			}
		}
	}
	
	private static Product1a[] read() throws IOException
	{
		List<Product1a> products = new ArrayList<>();
		try(BufferedReader in = new BufferedReader(new FileReader(PRODUCT_FILE_NAME)))
		{
			String line = in.readLine();
			while( line != null)
			{
				products.add(new Product1a(line));
				line = in.readLine();
			}
			return products.toArray(new Product1a[products.size()]);
		}
	}
	
	private static void print(Product1a[] pProducts)
	{
		for( Product1a product : pProducts )
		{
			System.out.println(product);
		}
	}
}
