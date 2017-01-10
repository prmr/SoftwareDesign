package comp303;

import comp303.Card.Rank;
import comp303.Card.Suit;

/** This client class is a stand in for any client code. 
 *  This version is adapted to work with Card7 
 */
public final class Client
{ 
	private Client()
	{}
	
	/**
	 * @param pArgs Not used here
	 */
	public static void main(String[] pArgs)
	{
		for( Rank rank : Rank.values() )
		{
			for( Suit suit : Suit.values() )
			{
				System.out.println(new Card(rank,suit));
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	

}
