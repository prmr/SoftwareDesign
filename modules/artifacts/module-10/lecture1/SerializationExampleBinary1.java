package lecture1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationExampleBinary1
{
	public static void main(String[] args) throws Exception
	{
		Product4 product1 = new Product4("TV", 1);
		Product4 product2 = new Product4("Phone", 2);
		Product4 product3 = new Product4("Radio", 3);
		
		product1.setRelated(product2);
		product2.setRelated(product1);

		product1.setProperty("screen", "bw");
		product1.setProperty("weight", "950");
		product2.setProperty("os", "Win");
		product3.setProperty("default", "CBC");
		
		Product4[] products = {product1, product2, product3};
		write(products);
		
		Product4[] readProducts = read();
		
		print(readProducts);
	}
	
	private static void write(Product4[] pProducts) throws IOException
	{
		try( ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("products.dat")))
		{
			out.writeObject(pProducts);
		}
	}
	
	private static Product4[] read() throws IOException, ClassNotFoundException
	{
		try( ObjectInputStream in = new ObjectInputStream(new FileInputStream("products.dat")))
		{
			return (Product4[])in.readObject();
		}
	}
	
	private static void print(Product4[] pProducts)
	{
		for( Product4 product : pProducts )
		{
			System.out.println(product);
		}
	}
}
