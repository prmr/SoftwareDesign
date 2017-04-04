package lecture1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;

import com.google.gson.Gson;

public class SerializationExampleJSON2
{
	public static void main(String[] args) throws Exception
	{
		Product2a product1 = new Product2a("TV", 1);
		Product2a product2 = new Product2a("Phone", 2);
		Product2a product3 = new Product2a("Radio", 3);

		product1.setProperty("screen", "bw");
		product1.setProperty("weight", "950");
		product2.setProperty("os", "Win");
		product3.setProperty("default", "CBC");
		
		product1.setRelated(product3);
		product2.setRelated(product3);
		product3.setRelated(product1);
		
		Product2a[] products = {product1, product2, product3};

		writeGSON(products);
	}
	
	private static void write(Product2a[] pProducts) throws IOException
	{
		JSONArray products = new JSONArray();
		for( Product2a product : pProducts )
		{
			products.put(product.toJSONObject());
		}
		try( PrintWriter out = new PrintWriter(new FileWriter("products2.json")))
		{
			products.write(out);
		}
	}
	
	private static void writeGSON(Product2a[] pProducts) throws IOException
	{
		Gson gson = new Gson();
		try( PrintWriter out = new PrintWriter(new FileWriter("products-g.json")))
		{
			out.write(gson.toJson(pProducts));
		}
	}
}
