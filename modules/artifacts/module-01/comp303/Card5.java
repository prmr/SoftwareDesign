package comp303;

/**
 * With this version we further improve encapsulation by
 * constraining values for ranks and suits to instances
 * of an enumerated type in a way that is backward compatible
 * for the client.
 */
public class Card5
{
	/**
 	 * A card's rank.
	 */
	public enum Rank 
	{ ACE, TWO, THREE, FOUR, FIVE, SIX,
		SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
	}
	
	/**
	 * A card's suit.
	 */
	public enum Suit
	{
		CLUBS, DIAMONDS, SPADES, HEARTS
	}
	
	public static final String[] RANKS = {"Ace", "Two", "Three", "Four", "Five",
			"Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	
	public static final String[] SUITS = {"Clubs", "Diamonds", "Spades", "Hearts"};
	
	private Rank aRank;
	private Suit aSuit;
	
	/**
	 * @param pRank The index of the rank in RANKS
	 * @param pSuit The index of the suit in SUITS
	 */
	public Card5(int pRank, int pSuit)
	{
		aRank = Rank.values()[pRank];
		aSuit = Suit.values()[pSuit];
	}
	
	/**
	 * @return The index in RANKS corresponding to the rank of the card.
	 */
	public int getRank()
	{
		return aRank.ordinal();
	}
	
	/**
	 * @return The index in SUITS corresponding to the suit of the card.
	 */
	public int getSuit()
	{
		return aSuit.ordinal();
	}
	
	/**
	 * Assigns a new rank to the card.
	 * @param pRank The new rank.
	 */
	public void setRank(int pRank)
	{
		aRank = Rank.values()[pRank];
	}
	
	/**
	 * Assigns a new suit to the card.
	 * @param pSuit The new suit.
	 */
	public void setSuit(int pSuit)
	{
		aSuit = Suit.values()[pSuit];
	}
	
	@Override
	public String toString()
	{
		return aRank + " of " + aSuit;
	}
}
