package comp303m02;

/**
 * Stand-in for the rest of the program.
 *
 */
public class Client
{
	public static void main(String[] args)
	{
		Deck deck = new Deck();
		deck.shuffle();
		
		for( Card card : deck)
		{
			System.out.println(card);
		}
		
	}
	
}
