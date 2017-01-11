package comp303;

/**
 * A class that represents a deck of cards composed of multiple decks
 * of cards. This version does not implement the copy-constructor 
 * part the exercise.
 * 
 * @author Martin P. Robillard
 */
public class MultiDeck
{
	private Deck[] aDecks;
	
	/**
	 * Creates an initialized, shuffled multi-deck from pNumberOfDecks
	 * decks.
	 * @param pNumberOfDecks The number of decks in the multi-deck
	 * @pre pNumberOfDecks > 0
	 */
	public MultiDeck(int pNumberOfDecks)
	{
		assert pNumberOfDecks > 0;
		aDecks = new Deck[pNumberOfDecks];
		for( int i =0; i < aDecks.length; i++ )
		{
			aDecks[i] = new Deck();
			aDecks[i].shuffle();
		}
	}
	
	/**
	 * @return The next card in the multi-deck
	 * @pre !isEmpty()
	 */
	public Card draw()
	{
		assert !isEmpty();
		for( Deck deck : aDecks )
		{
			if( deck.isEmpty() )
			{
				continue;
			}
			return deck.draw();
		}
		assert false;
		return null;
	}
	
	/**
	 * @return True if all decks in this multi-deck are empty
	 */
	public boolean isEmpty()
	{
		for( Deck deck : aDecks )
		{
			if( !deck.isEmpty() )
			{
				return false;
			}
		}
		return true;
	}
}
