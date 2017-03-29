package module9;

/**
 * Illustrates the motivation for and use of Thread.join()
 */
public class DistributedComputation
{
	public static void main(String[] args)
	{
		NumberAdder adder1 = new NumberAdder(1000000);
		NumberAdder adder2 = new NumberAdder(2000000);
		NumberAdder adder3 = new NumberAdder(3000000);
		NumberAdder adder4 = new NumberAdder(4000000);
		
		// Worker threads
		adder1.start();
		adder2.start();
		adder3.start();
		adder4.start();
		
		// Thread that aggregates and displays the result
		new Thread(new Runnable(){

			@Override
			public void run()
			{
				try
				{
					adder1.join();
					adder2.join();
					adder3.join();
					adder4.join();
					// Warning: not synchronized!
					System.out.println(adder1.getResult() + adder2.getResult() +
							adder3.getResult() + adder4.getResult());
				}
				catch (InterruptedException e)
				{
					return;
				}
				
			}}).start();
	}
}

/**
 * Sum positive integer sequence up to and 
 * including N using a brute force/naive 
 * technique.
 */
class NumberAdder extends Thread
{
	private final int aLast;
	private int aSum = 0;
	
	public NumberAdder(int pLast)
	{
		aLast = pLast;
	}
	
	@Override
	public void run()
	{
		aSum = 0;
		for( int i = 0; i <= aLast; i++ )
		{
			aSum += i;
		}
	}
	
	public int getResult()
	{
		return aSum;
	}
}