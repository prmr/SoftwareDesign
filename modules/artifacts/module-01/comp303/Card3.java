package comp303;

/**
 * Improves on Card2 by really hiding our decision
 * concerning the internal representation of a card,
 * and allowing access to the card's representation in 
 * terms of concepts in the problem space, as opposed
 * to the internal representation.
 * A better example of information hiding.
 */
public class Card3
{
	public static final String[] RANKS = {"Ace", "Two", "Three", "Four", "Five",
			"Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	
	public static final String[] SUITS = {"Clubs", "Diamonds", "Spades", "Hearts"};
	
	private int aId = 0; 
	
	/**
	 * @param pRank The index of the rank in RANKS
	 * @param pSuit The index of the suit in SUITS
	 */
	public Card3(int pRank, int pSuit)
	{
		aId = pSuit * RANKS.length + pRank;
	}
	
	/**
	 * @return The index in RANKS corresponding to the rank of the card.
	 */
	public int getRank()
	{
		return aId % RANKS.length;
	}
	
	/**
	 * @return The index in SUITS corresponding to the suit of the card.
	 */
	public int getSuit()
	{
		return aId / RANKS.length;
	}
	
	/**
	 * Assigns a new rank to the card.
	 * @param pRank The new rank.
	 */
	public void setRank(int pRank)
	{
		aId = getSuit() * RANKS.length + pRank;
	}
	
	/**
	 * Assigns a new suit to the card.
	 * @param pSuit The new suit.
	 */
	public void setSuit(int pSuit)
	{
		aId = pSuit * RANKS.length + getRank();
	}
	
	@Override
	public String toString()
	{
		return RANKS[getRank()] + " of " + SUITS[getSuit()];
	}
}
