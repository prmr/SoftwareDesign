package module05;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import module05.Card.Rank;
import module05.Card.Suit;

public class Deck implements Iterable<Card>, Cloneable
{
	private Stack<Card> aCards;
	
	public Iterator<Card> iterator()
	{
		return aCards.iterator();
	}

	
	public Deck clone()
	{
		try
		{
			Deck lReturn = (Deck)super.clone();
			lReturn.aCards = new Stack<>();
			for( Card card : aCards )
			{
				lReturn.aCards.add(card);
			}
			return lReturn;
			
		}
		catch (CloneNotSupportedException e)
		{
			return null;
		}
	}
	
	public Deck()
	{
		aCards = new Stack<Card>();
		
	}
	
	public List<Card> getCards()
	{
		return Collections.unmodifiableList(aCards);
	}
	
	public Deck( Deck pDeck )
	{
		aCards = new Stack<Card>();
		for( Card card : pDeck.aCards )
		{
			aCards.add(new Card(card));
		}
	}
	
	public void shuffle()
	{
		aCards.clear();
		for( Suit suit : Suit.values() )
		{
			for( Rank rank : Rank.values())
			{
				aCards.push(new Card(rank, suit));
			}
		}
		Collections.shuffle(aCards);
	}
	
	public boolean isEmpty()
	{
		return aCards.isEmpty();
	}
	
	public Card draw()
	{
		return aCards.pop();
	}
}
