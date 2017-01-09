# Module 1 - Encapsulation

## Description

One of the major motivation for good design is to keep control of the complexity of a system. An idea that is paramount to simplifying the design of software is encapsulation. Ideally, classes serve to support functionality, but also to isolate distinct computations. This module will cover the most important concepts and techniques necessary to design well-encapsulated classes.

## Learning Objectives

After this module you should:

* Be able to explain the concepts of *encapsulation* and *information hiding*;
* Evaluate the quality of encapsulation realized by a single class;
* Know some of the programming language mechanisms and idioms that support encapsulation, and how to apply them;
* Understand the importance of *immutability* in software design and how to make Java classes immutable.
* Know about the concept of Design by Contract and how to apply it to Java programs using the assert statement;
* Know about *code style*, be able to explain how it contributes to code quality, and be able to apply a code-style checker and interpret its results;
* Be able to create and interpret Object Diagrams.

## Notes

### General Concepts and Definitions

The idea of **encapsulation** is to "to enclose in or as if in a capsule" [[Merriam-Webster](https://www.merriam-webster.com/dictionary/Encapsulation)]. For example, you can think of a nut, which is encapsulated in its shell. The shell, or capsule, serves as protection. In software design we encapsulate both data and computation both to protect them from corruption, and to simplify the design. Encapsulation in software design is related to the principle of **information hiding**, which has been around since the early 1970s. According to [Vogel et al.](http://link.springer.com/book/10.1007/978-3-642-19736-9) "The principle generally states that you only show a client that part of the total information that is really necessary for the client’s task and you hide all remaining information."

One of the first problem we will tackle in this module is to design an abstraction that can conveniently represent a single playing card. In a standard deck of cards there are 52 distinct cards and any given card can be completely defined by its *suit* (Hearts, Spades, Diamonds, Clubs) and its *rank* (Ace, 2, 3,...,10, Jack, Queen, King). In a program we can represent a playing card in many different ways. For example, using a single integer between 0 and 51 where the value of the integer somehow represents the card. Or, we could represent a card using a combination of 8 boolean values (insane but technically possible). Here to apply the principle of information hiding, we would organize our program structure so as to *hide* the decision of how exactly we represent a card in the program.

The fact that we can represent a card in many different ways is also a good illustration of the concept of a **design space**. The design space is an imaginary multi-dimensional vector space where every dimension corresponds to a design quality attribute (like understandability, reusability, ease of implementation, etc.). Within such a design space, each specific design decision corresponds to a point. The figure below illustrates the idea. Each decision is likely to be good in some dimension, but less good in others, something we call a **design trade-off**. Two sub-spaces of the design space that are useful to consider are the space of *possible solutions*, and the space of *acceptable solutions*. Note that the theoretically optimal solution, which best satisfies all dimensions at the same time, is unlikely to be possible. All this to say that design is a decision process for which there is rarely a single "right answer", only answers that are better or worse in some dimensions (including some answers that are pretty bad in most dimensions). In practice, the challenge of choosing a point in the design space is compounded by the fact that assessing the quality of a solution, even within a single dimension, is a very approximate process, and that we don't usually know what the optimal solution is.

![Exploring the design space](figures/m00-DesignSpace.png)

### Designing Basic Abstractions

One of the main activities of software design is to *define abstractions* using programming languages mechanisms. Ideally the abstractions we define will:

* clearly map to concepts in the problem domain (to facilitate program understanding)
* be well-encapsulated (see above)

One of the main mechanisms we use to define abstractions in type-based object-oriented languages is, not surprisingly, the *type*. 

Consider the following bad example:

```
int card = 0; // The ace of clubs
```

Here our decision is to use the integer primitive type as the abstraction to represent a playing card. Notice how this achieves neither of the two desirable properties above. The `int` type maps to the concept of integers (a type of number), not that of a playing card. You could rename the variable to `socialInsuranceNumber` while it still holds a value for a card, and achieve maximum confusion. It is also not well-encapsulated because you could assign the value 53 to the variable and corrupt it.

For this reason it's generally a bad idea to try to shoehorn domain concepts into default types like integer or string. The `int` type should only be used to hold actual integers (and very similar concepts, such as currency), and similarly with strings. 

In other cases we want to use our own type (or one defined by a library):

```
class Card {}
```

If we started to design this type, we would quickly realize that our program also needs to manipulate two other types of values: *suits* and *ranks*. These types of values are a bit different because they are more like labels for domain objects than actual objects. What makes them fell like labels are that there are a finite number of them for a particular type of values (e.g., 4 for suits), and it appears to be useless to have two or more instances representing a given suit (e.g., clubs). In fact values of these types would be used more or less as *constants* in a program.

```
private static int CLUBS = 0;
private static int HEARTS = 1;
...
```

There is a specific mechanism for representing these kinds of values called the [Enumerated Type](https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html), or "enum types" for short. It's better to avoid the single colloquial term "enum" because it's not clear whether it refers to an enumerated type or a value of this type. Enumerated types are a truly powerful feature for software design, and I will use them extensively throughout this course. The one slight weakness of enumerated types in Java is that in addition to the enumerated values:

```
public enum Suit
{
	CLUBS, DIAMONDS, SPADES, HEARTS
}
```

a variable of an enumerated type can also take the value `null`:

```
Suit suit = Suit.CLUBS;
suit = null;
```

This is not a feature, but rather a consequence of how enumerated types are implemented in Java (as reference types). A good programming practice is to never consider the `null` value to be a legal value when using enumerated types.

<!--
* Object copying idiom
* Object encapsulation: It should not be possible to change an object state without going through its methods
* Design by contract
* Scopes and escaping references
* Object diagrams
* Immutability, final fields
* Collection copying -->

## Reading
* Textbook 3.1-3.6
* The Java Tutorian on [Enumerated Types](https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html)
* Solitaire v0.1 [Card.java](https://github.com/prmr/Solitaire/blob/v0.1/src/ca/mcgill/cs/stg/solitaire/cards/Card.java)
* Solitaire v0.1 [Deck.java](https://github.com/prmr/Solitaire/blob/v0.1/src/ca/mcgill/cs/stg/solitaire/cards/Deck.java)
* JetUML v1.0 [MultiLineString](https://github.com/prmr/JetUML/blob/v1.0/src/ca/mcgill/cs/stg/jetuml/framework/MultiLineString.java)

## Exercises

0. Implement class `Card` as a single enumerated type. For the purpose of the Solitaire application, does this look like a superior or inferior solution to version 7 of class `Card` seen in class? Why? Try writing down your answer in specific terms using terminology seen in the course. This will be an excellent practice for the exams.
0. Extend version 7 of class `Card` to support the concept of a "Joker" (a special card that is not in any suit) while keeping the class as well-encapsulated as possible.
0. Further extend your class to support any number of distinct jokers. For example, a "high" joker vs. a "low" joker, or even three jokers, etc. Again, try to keep things well-encapsulated and respectful of the class design guidelines seen in the reading.
0. Add a method `getCards()` to the `Deck` class that returns the cards in the deck without breaking encapsulation.
0. Create a new class called `MultiDeck` that contains a list of decks (some card games require multiple decks). Make the class copyable through a copy constructor. First, make a shallow copy of the decks contained. Use the debugger to confirm that the decks in a multi-deck are shared between an original multi-deck and its copies. Then, use deep copying to make copied multi-decks fully distinct object graphs. Use Design by Contract and the `assert` statement to clarify the class's interface.
0. Create a UML Object Diagram of a `MultiDeck` instance.
0. Study JetUML's [MultiLineString](https://github.com/prmr/JetUML/blob/v1.0/src/ca/mcgill/cs/stg/jetuml/framework/MultiLineString.java) class and assess the quality of its encapsulation. Is it possible to modify its state without going through its methods?

---

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a>

Unless otherwise noted, the content of this repository is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>. 

Copyright Martin P. Robillard 2017