# Module 9 - Answers

Answers and answer sketches to the Module 8 practice exercises.

## Exercise 1

You need to synchronize class `Box` to avoid data races and to ensure the visibility of new values across threads.

```java
public class Box
{
	private final Lock lock = new ReentrantLock();
	
	private int aNumber = 0;
	
	public void increment()
	{
		lock.lock();
		try { aNumber ++; }
		finally { lock.unlock(); }
	}
	
	public int get()
	{
		lock.lock();
		try{ return aNumber; } 
		finally { lock.unlock(); }
	}
	
	public static void main(String[] args)
	{
		final Box box = new Box();
		new Thread(new Runnable() 
		{
			public void run()
			{
				while(true)
				{
					box.increment();
				}
			}
		}).start();
		new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					while(true)
					{
						System.out.println(box.get());
						Thread.sleep(1000);
					}
				}
				catch(InterruptedException e )
				{
					return;
				}
			}
		}).start();
	}
}
```

## Exercise 2

This version should print the sequence of positive integers, one per second.

```java
public class Box
{
	private final Lock lock = new ReentrantLock();
	private final Condition valueAvailable = lock.newCondition();
	
	private int aNumber = 0;
	
	public void increment()
	{
		lock.lock();
		try 
		{ 
			aNumber ++;
			valueAvailable.signalAll();
		}
		finally { lock.unlock(); }
	}
	
	public int get()
	{
		lock.lock();
		try
		{ 
			valueAvailable.await();
			return aNumber; 
		} 
		catch( InterruptedException e )
		{
			return aNumber;
		}
		finally { lock.unlock(); }
	}
	
	public static void main(String[] args)
	{
		final Box box = new Box();
		new Thread(new Runnable() 
		{
			public void run()
			{
				try
				{
					while(true)
					{
						Thread.sleep(1000);
						box.increment();
						
					}
				}
				catch( InterruptedException e)
				{
					return;
				}
			}
		}).start();
		new Thread(new Runnable()
		{
			public void run()
			{
				while(true)
				{
					System.out.println(box.get());
				}
			}
		}).start();
	}
}
```

## Exercise 3

While using a timer API would be superior, for this small exercise the following revised version of the `main` method will do the job:

```java
	public static void main(String[] args)
	{
		final Box box = new Box();
		final Thread counter = new Thread(new Runnable() 
		{
			public void run()
			{
				try
				{
					while(!Thread.interrupted())
					{
						Thread.sleep(1000);
						box.increment();
						
					}
				}
				catch( InterruptedException e)
				{
					return;
				}
			}
		});
		final Thread printer = new Thread(new Runnable()
		{
			public void run()
			{
				while(!Thread.interrupted())
				{
					System.out.println(box.get());
				}
			}
		});
		final Thread timer = new Thread(new Runnable() 
		{
			public void run()
			{
				while( box.get() < 10 )
				{}
				counter.interrupt();
				printer.interrupt();
			}
		});
		timer.start();
		counter.start();
		printer.start();
	}
```