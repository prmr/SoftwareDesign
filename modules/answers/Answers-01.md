# Module 1 - Answers

Answers and answer sketches to the Module 1 practice exercises.

## Exercise 1

The code can be found [here](EnumeratedCard.java). With the two helper methods to get the suit and rank of the card, the answer is pretty close to the original one. To have to calculate the rank and suit from indices has lower understandability and robustness than simply storing the information directly. However, the fact that the `Card` is an immutable enumerated type gives us a number of benefits, such as the certainty that there will never be duplicate cards in a program. Personally I would still favor the version seen in class.

## Exercise 2

Notice how you can define the color enumerated type as an inner type of the `Suit` type, which makes sense, because this isn't any color, but the color of a card's suit. Note also that I could have had more compact code in `getColor` by working with the enumerated value's index, but that is brittle. The current code will work even if the order of enumerated values is changed.

```
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
```

## Exercise 3

**Sketch:** There are many different ways to answer this question. For a single Joker, it would make sense to have an additional boolean field `aIsJoker`. The interesting questions to solve become, what should the rank and suit of a Joker be, and what should `getRank` and `getSuit` return for a joker? Experiment with design by contract and exception handling.

## Exercise 4

**Sketch:** An enumerated type seems like the right idea here to capture the type of joker. However, as I mentioned in class, it's not a good idea to consider null to be a legal value for an enumerated type, so I would recommend including an enum value to represent the case where the card is not a joker. The issues of what to do with rank and suit of jokers is the same as for Exercise 3.

## Exercise 5

Assuming instances of class `Card` are immutable, it's ok to do the following, although we will see more elegant solutions in Module 2.

```
public List<Card> getCard()
{
	return new ArrayList<>(aCards);
}
```

## Exercise 6

The code of a partial solution can be found [here](MultiDeck.java). The solution leaves out the copying part of the assignment. To implement the object copying, you will need a way to copy `Deck` and `Card` instances. One way to do this at this point is simply to define copy constructors for these two classes.

## Exercise 7

![Answer to exercise 7](figures/m01-objectDiagram2.png)

## Exercise 8

The class is well encapsulated. The only part of the internal state returned is the inner `String`, but that is an immutable object.