package comp303m02;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

import comp303m02.Card.Rank;
import comp303m02.Card.Suit;

/**
 * A Deck of playing cards.
 *
 */
public final class Deck implements Iterable<Card>
{
	private  Stack<Card> aCards = new Stack<>();
	
	public static void main(String[] args)
	{
		Deck deck = new Deck();
		deck.shuffle();
	}
	
	@Override
	public Iterator<Card> iterator()
	{
		return aCards.iterator();
	}
	
	public void shuffle()
	{
		aCards.clear();
		for(Suit suit : Suit.values())
		{
			for(Rank rank : Rank.values())
			{
				aCards.push(new Card(rank,suit));
			}
		}
		Collections.shuffle(aCards);
		
	}
	
	/* Three different options are shown in parallel here. Clearly in a real program
	 * you would select only one.
	 */
	public void sort()
	{				
		Collections.sort(aCards, new Comparator<Card>() {

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
		}});
		
		Collections.sort(aCards, (pCard1, pCard2) -> pCard1.getRank().compareTo(pCard2.getRank()));
		
		Collections.sort(aCards, new Card.CompareBySuitFirst());
	}
	
	public Card draw()
	{
		return aCards.pop();
	}
	
	public boolean isEmpty()
	{
		return aCards.isEmpty();
	}
	
	public Card peek()
	{
		assert !isEmpty();
		return aCards.peek();
	}
}
