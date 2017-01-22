package comp303m02;

import java.util.Comparator;

public class Card implements Comparable<Card>
{
	static class CompareBySuitFirst implements Comparator<Card>
	{
		@Override
		public int compare(Card pCard1, Card pCard2)
		{
			if( pCard1.getSuit() == pCard2.getSuit() )
			{
				return pCard1.getRank().compareTo(pCard2.getRank());
			}
			else
			{
				return pCard1.getSuit().ordinal() - pCard2.getSuit().ordinal();
			}
		}
	}
	
	public static Comparator<Card> createByRankComparator()
	{
		return new Comparator<Card>() {

			@Override
			public int compare(Card pCard1, Card pCard2)
			{
				if( pCard1.getRank() == pCard2.getRank() )
					{
						return pCard1.getSuit().compareTo(pCard2.getSuit());
					}
					else
					{
						return pCard1.getRank().ordinal() - pCard2.getRank().ordinal();
					}
			}};
	}
	
	/**
 	 * A card's rank. The Rank object is a part of a Card object.
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
		CLUBS, DIAMONDS, SPADES, HEARTS;
		
		enum Color
		{
			RED, BLACK;
		}
		
		public Color getColor()
		{
			if( this == CLUBS || this == SPADES )
			{
				return Color.BLACK;
			}
			else
			{
				return Color.RED;
			}
		}
		
		public String toString()
		{
			return name().substring(0,1) + name().substring(1, name().length()).toLowerCase();
		}
	}
	
	public static final String[] SUITS = {"Clubs", "Diamonds", "Spades", "Hearts"};
	
	private final Rank aRank;
	private final Suit aSuit;
	
	/**
	 * @param pRank The index of the rank in RANKS
	 * @param pSuit The index of the suit in SUITS
	 * @pre pRank != null && pSuit != null
	 */
	public Card(Rank pRank, Suit pSuit)
	{
		assert pRank != null && pSuit != null;
		aRank = pRank;
		aSuit = pSuit;
	}
	
	public Card( Card pCard )
	{
		aRank = pCard.aRank;
		aSuit = pCard.aSuit;
	}
	
	/**
	 * @return The index in RANKS corresponding to the rank of the card.
	 */
	public Rank getRank()
	{
		return aRank;
	}
	
	/**
	 * @return The index in SUITS corresponding to the suit of the card.
	 */
	public Suit getSuit()
	{
		return aSuit;
	}
	
	@Override
	public String toString()
	{
		return getRank() + " of " + getSuit();
	}

	public int compareTo(Card pCard)
	{
		if( getSuit() == pCard.getSuit() )
		{
			return getRank().compareTo(pCard.getRank());
		}
		else
		{
			return getSuit().ordinal() - pCard.getSuit().ordinal();
		}
	}
}
