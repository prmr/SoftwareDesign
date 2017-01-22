package comp303m02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A collection of cards in a player's hand.
 * Incomplete.
 */
public class Hand implements Iterable<Card>
{
	private final List<Card> aCards = new ArrayList<>();
	
	public void add(Card pCard)
	{
		assert pCard != null;
		aCards.add(pCard);
	}

	@Override
	public Iterator<Card> iterator()
	{
		return aCards.iterator();
	}
	

}
