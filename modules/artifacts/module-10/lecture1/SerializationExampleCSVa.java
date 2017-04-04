package lecture1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SerializationExampleCSVa
{
	private static final String PRODUCT_FILE_NAME = "products.csv";
	
	public static void main(String[] args) throws IOException
	{
		Product1[] products = {new Product1("Pen", 1),
							  new Product1("Mug", 2),
							  new Product1("Eraser", 3)};
		
		write(products);
		Product1[] products2 = read();
		print(products2);
	}
	
	private static void write(Product1[] pProducts) throws IOException
	{
		try( PrintWriter out = new PrintWriter( new FileWriter(PRODUCT_FILE_NAME)))
		{
			for( Product1 product : pProducts )
			{
				out.println(product.getId() + "," + product.getName());
			}
		}
	}
	
	private static Product1[] read() throws IOException
	{
		List<Product1> products = new ArrayList<>();
		try(BufferedReader in = new BufferedReader(new FileReader(PRODUCT_FILE_NAME)))
		{
			String line = in.readLine();
			while( line != null)
			{
				String[] tokens = line.split(",");
				products.add(new Product1(tokens[1],Integer.parseInt(tokens[0])));
				line = in.readLine();
			}
			return products.toArray(new Product1[products.size()]);
		}
	}
	
	private static void print(Product1[] pProducts)
	{
		for( Product1 product : pProducts )
		{
			System.out.println(product);
		}
	}
}
