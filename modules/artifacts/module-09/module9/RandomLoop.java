package module9;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

/**
 * If you get radically different results on your machine (see slide)
 * please post them on the Lectures forum. 
 */
public class RandomLoop implements Runnable
{
	private static final int MAX_ITERATIONS = 1000000;

	@Override
	public void run()
	{
		for( int i = 0; i < new Random().nextInt(MAX_ITERATIONS); i++ )
		{
			System.out.println("foo");
		}
	}

	public static void main(String[] args)
	{
		Thread counter = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					PrintWriter out = new PrintWriter(new FileWriter("C:\\temp\\data.csv"));
					int nbOfThreads = 0;
					while(true)
					{
						int current = Thread.activeCount();
						if( current != nbOfThreads )
						{
							nbOfThreads = current;
							out.println(System.currentTimeMillis() + "," + current);
						}
						if(current == 2)
						{
							out.close();
							break;
						}
					}
				}
				catch(Exception e){}
			}
		});
		counter.start();
		for( int i = 0; i < 1000; i++ )
		{
			new Thread(new RandomLoop()).start();
		}
	}
}
