package module9;

/**
 * Basic demonstration of thread creation in Java.
 * 
 */
public class IndecisivePerson
{
	public static void main(String[] args)
	{
		Thread t1 = new Thread( new Runnable() {

			@Override
			public void run()
			{
				while(!Thread.interrupted())
				{
					System.out.println("Maybe");
					try
					{
						Thread.sleep(0);
					}
					catch (InterruptedException e)
					{
						return;
					}
				}
				
			}}
				
				);
		
		Thread t2 = new Thread( new Runnable() {

			@Override
			public void run()
			{
				while(!Thread.interrupted())
				{
					System.out.println("Not");
					try
					{
						Thread.sleep(0);
					}
					catch (InterruptedException e)
					{
						return;
					}
				}
				
			}}
				
				);
		t1.start();
		t2.start();
		new Thread(new Runnable(){

			@Override
			public void run()
			{
				try
				{
					Thread.sleep(2000);
				}
				catch (InterruptedException e)
				{
					return;
				}
				t1.interrupt();		
				t2.interrupt();
			}}).start();
	}
}

class MyThread implements Runnable
{
	private final String aMessage;
	
	public MyThread(String pMessage)
	{
		aMessage = pMessage;
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			System.out.println(aMessage);
		}
	}
	
}