package module9;

/**
 * Demonstrates the use of join and the effect of interrupting
 * either the waiting or working thread. 
 *
 */
public class JoiningHands
{
	public static void main(String[] args)
	{
		final Thread t1 = new Thread( new Runnable()
		{
			public void run()
			{
				try
				{
					Thread.sleep(5000);
					System.out.println("T1 completed normally");
				}
				catch(InterruptedException e)
				{
					System.out.println("T1 interrupted");
				}
			}
		});
		final Thread t2 = new Thread( new Runnable()
		{
			public void run()
			{
				try
				{
					t1.join();
					System.out.println("T2 completed normally");
				}
				catch(InterruptedException e)
				{
					System.out.println("T2 interrupted");
				}
			}
		});
		t1.start();
		t2.start();
//		t1.interrupt(); // Uncomment and t2 will synchronize on t1's abnormally quick termination
		t2.interrupt(); // Uncomment and t2 will be interrupted, but t1 will remain oblivious and terminate normally
	}
}