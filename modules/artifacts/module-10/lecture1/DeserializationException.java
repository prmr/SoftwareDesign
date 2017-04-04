package lecture1;

@SuppressWarnings("serial")
public class DeserializationException extends RuntimeException
{
	public DeserializationException()
	{
		super();
	}

	public DeserializationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeserializationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public DeserializationException(String message)
	{
		super(message);
	}

	public DeserializationException(Throwable cause)
	{
		super(cause);
	}

}
