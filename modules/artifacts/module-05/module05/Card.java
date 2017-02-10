package module05;

/**
 * Same as version 7. My last example.
 */
public class Card implements Cloneable
{	
	
	public Card clone() 
	{
		try
		{
			return (Card) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			return null;
		}
	}

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
	
	private Rank aRank; // Invariant: != null
	private Suit aSuit; // Invariant: != null
	
	/**
	 * @param pRank The index of the rank in RANKS
	 * @param pSuit The index of the suit in SUITS
	 * @pre pRank != null && pSuit != null
	 */
//	public Card(Rank pRank, Suit pSuit)
//	{
//		assert pRank != null && pSuit != null;
//		aRank = pRank;
//		aSuit = pSuit;
//	}
	
	public Card(Rank aRank, Suit aSuit)
	{
		this.aRank = aRank;
		this.aSuit = aSuit;
	}
	@Override
	public boolean equals(Object pObject)
	{
		if( pObject == null ) return false;
		if( pObject == this ) return true;
		if( pObject.getClass() != getClass() ) return false;
		return aRank == ((Card)pObject).getRank() && aSuit == ((Card)pObject).getSuit();
	}
	
	@Override
	public int hashCode()
	{
		return aSuit.ordinal() * Rank.values().length + aRank.ordinal();
	}
	
	public Card( Card pCard )
	{
		aRank = pCard.aRank;
		aSuit = pCard.aSuit;
	}
	
	/**
	 * @return The index in RANKS corresponding to the rank of the card.
	 * @post return != null
	 */
	public Rank getRank()
	{
		return aRank;
	}
	
	/**
	 * @return The index in SUITS corresponding to the suit of the card.
	 * @post return != null
	 */
	public Suit getSuit()
	{
		return aSuit;
	}
	
	public static void main(String[] args)
	{
		Card card = new Card(Rank.ACE, Suit.CLUBS);
	}
	
	/**
	 * Assigns a new suit to the card.
	 * @param pSuit The new suit.
	 * @pre pSuit != null
	 */
	public void setSuit(Suit pSuit)
	{
		aSuit = pSuit;
	}
	
	@Override
	public String toString()
	{
		return aRank + " of " + aSuit;
	}
}
