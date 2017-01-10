package comp303;

/**
 * Shows the benefits of information hiding. We change the
 * internal representation of a card from Card3 but the class's 
 * interface remains the same and Client does not have to change.
 */
public class Card4
{
	public static final String[] RANKS = {"Ace", "Two", "Three", "Four", "Five",
			"Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	
	public static final String[] SUITS = {"Clubs", "Diamonds", "Spades", "Hearts"};
	
	private int aRank = 0;
	private int aSuit = 0;
	
	/**
	 * @param pRank The index of the rank in RANKS
	 * @param pSuit The index of the suit in SUITS
	 */
	public Card4(int pRank, int pSuit)
	{
		aRank = pRank;
		aSuit = pSuit;
	}
	
	/**
	 * @return The index in RANKS corresponding to the rank of the card.
	 */
	public int getRank()
	{
		return aRank;
	}
	
	/**
	 * @return The index in SUITS corresponding to the suit of the card.
	 */
	public int getSuit()
	{
		return aSuit;
	}
	
	/**
	 * Assigns a new rank to the card.
	 * @param pRank The new rank.
	 */
	public void setRank(int pRank)
	{
		aRank = pRank;
	}
	
	/**
	 * Assigns a new suit to the card.
	 * @param pSuit The new suit.
	 */
	public void setSuit(int pSuit)
	{
		aSuit = pSuit;
	}
	
	@Override
	public String toString()
	{
		return RANKS[getRank()] + " of " + SUITS[getSuit()];
	}
}
