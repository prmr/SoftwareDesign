package comp303;

public enum EnumeratedCard
{
	ACE_CLUBS, TWO_CLUBS, THREE_CLUBS, FOUR_CLUBS, FIVE_CLUBS, SIX_CLUBS, SEVEN_CLUBS, 
	EIGHT_CLUBS, NINE_CLUBS, TEN_CLUBS, JACK_CLUBS, QUEEN_CLUBS, KING_CLUBS, 
	ACE_DIAMONDS, TWO_DIAMONDS, THREE_DIAMONDS;
	
	/**
	 * A card's suit.
	 */
	public enum Suit
	{
		CLUBS, DIAMONDS, SPADES, HEARTS;
	}
	
	/**
	 * A card's rank.
	 */
	public enum Rank 
	{ ACE, TWO, THREE, FOUR, FIVE, SIX,
		SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
	}
	
	public Suit getSuit()
	{
		return Suit.values()[ordinal() / Rank.values().length];
	}
	
	public Rank getRank()
	{
		return Rank.values()[ordinal() % Rank.values().length];
	}

}
