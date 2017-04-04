package lecture1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;

import com.google.gson.Gson;

public class SerializationExampleJSON1
{
	public static void main(String[] args) throws IOException
	{
		Product2 product1 = new Product2("TV", 1);
		Product2 product2 = new Product2("Phone", 2);
		Product2 product3 = new Product2("Radio", 3);

		product1.setProperty("screen", "bw");
		product1.setProperty("weight", "950");
		product2.setProperty("os", "Win");
		product3.setProperty("default", "CBC");
		
		Product2[] products = {product1, product2, product3};

		writeGSON(products);
	}
	
	private static void write(Product2[] pProducts) throws IOException
	{
		JSONArray products = new JSONArray();
		for( Product2 product : pProducts )
		{
			products.put(product.toJSONObject());
		}
		try( PrintWriter out = new PrintWriter(new FileWriter("products.json")))
		{
			products.write(out);
		}
	}
	
	private static void writeGSON(Product2[] pProducts) throws IOException
	{
		Gson gson = new Gson();
		try( PrintWriter out = new PrintWriter(new FileWriter("products-g.json")))
		{
			out.write(gson.toJson(pProducts));
		}
	}
}
