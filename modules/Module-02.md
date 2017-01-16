# Module 2 - Types and Polymorphism

## Description

One of the main mechanisms at our disposal for designing object-oriented applications is *polymorphism*, which in the case of Java and similar languages is heavily tied to the notion of a type system. In this module I will do a brief review of the foundations of polymorphism, introduce the idea of interface programming, and present common low-level design problems that are easily solved with polymorphism.

## Learning Objectives

After this module you should:

* Be able to explain the concept of subtype polymorphism in object-oriented programming;
* Understand and be able to use the Interface Segregation Principle;
* Be able to create and interpret UML Class Diagrams;
* Know the difference between an interface type and a class's interface;
* Be able to implement existing interfaces to provide required functionality;
* Understand the ideas of anonymous classes and function objects and be able to use them effectively;
* Know about the concept of Design Patterns;
* Be able to effectively use the *Iterator* and *Strategy* design patterns;
* Know and be able to effectively use some of the common Java utility interfaces, including `Comparable` and `Comparator`.

## Notes

### General Concepts and Definitions

In Module 1 we saw how to define well-encapsulated classes, but conveniently left out the question of how objects of these classes would interact. In Module 2 we start facing this question. Interactions between objects are mediated through *interfaces*. The term "interface" is heavily overloaded in programming: it can have different meanings depending on the context. 

A **interface to a class or to an object** consists of the methods of that class (or of the object's class) that are *accessible* to another class or object. What methods are *accessible* depends on the *scoping rules* that apply to a class member. In the context of Module 2 we will keep things simple and assume that the interface to a class or object is the set of its public methods. This is not strictly true, but for Module 2 we don't need the additional distinctions. Note that I refer to the interface of a *class or object*. This simply means that the concept of interface applies to both. If we're thinking of a design solution in terms of class definitions, then the interface is that of the class. If we're thinking of a design solution in terms of a collection of instantiated objects of the class, then it's the object's interface that's relevant.

Consider the following program:

```
class Game
{
	Deck aDeck;
	...
	
public final class Deck
{
	private  Stack<Card> aCards = new Stack<>();
	...
	public Deck( Deck pDeck ) {...}
	public void shuffle() {...]
	public Card draw() {...}
	public boolean isEmpty() {...}
}
```

Here the interface of class `Deck` consists of a constructor and three methods. The code in other classes can interact with objects of class `Deck` by calling these and only these methods. Here we would say that the interface of class `Deck` is *fused with the class definition*. In other words, the interface of class `Deck` is just a consequence of how we defined class `Deck`: there is no way to get the three services that correspond to the three methods of the class, without interacting with an instance of class `Deck`.

There are, however, many situations were we want to *decouple* the interface of a class from its implementation. These are situations where we want to design the system so one part of the program can depend on the availability of some services, without being tied to the exact details of how these services are implemented.

Consider the case of manipulating icons in a program:

```
class Game
{
	Icon aIcon = ...;
	
	public void showIcon()
	{
		if(aIcon.getIconWidth() > 0 && aIcon.getIconHeight() > 0 )
		{
			aIcon.paintIcon(...);
		}
	}
...
```

In practice, the information representing an icon can come from different types of sources, including image files of different formats and [algorithms that compute image information on the fly](https://github.com/prmr/JetUML/blob/v1.0/src/ca/mcgill/cs/stg/jetuml/framework/ToolBar.java#L129). In this and similar cases, it is extremely useful to be able to specify an interface without tying it to a specific class. This is where **Java interfaces** come in.

In Java, interfaces provide a **specification** of the methods that it should be possible to invoke on the objects of a class. For instance the interface [Icon](http://docs.oracle.com/javase/8/docs/api/javax/swing/Icon.html) *specifies* three method signatures and documents their expected behavior.

To tie a class with an interface, we use the `implements` keyword.

```
public class ImageIcon implements Icon
```

The `implements` keyword has two related meanings:
* It provides a formal guarantee that instances of the class type will have concrete implementations for all the methods in the interface type. This guarantee is enforced by the compiler.
* It creates a **subtype** ("is-a") relationship between the implementing class and the interface type: here we can now say than an `ImageIcon` "is-a" type of `Icon`.

The subtype relation between a concrete class and an interface is what enables the use of [polymorphism](https://en.wikipedia.org/wiki/Polymorphism_(computer_science)). In plain language, polyphorphism is the ability to have different shapes. Here, an abstractly specified `Icon` can have different concrete shapes that correspond to the different implementations of the `Icon` interface. For polymorphism to makes sense in the context of a Java program it's important to remember that according to the rules of the Java type system it is possible to assign a value to a variable if the value is of the same type *or a subtype* of the type of the variable. Because the interface implementation relation defines a subtype relation, concrete classes declared to implement an interface can be assigned to variables declared to be of the interface type. Another illustration of the use of polymorphism is the use of concrete vs. abstract collection types:

```
List<String> list = new ArrayList<>();
```

Here `List` is an interface that specifies the obvious services (add, remove, etc.), and `ArrayList` is a concrete implementation of this service. But we can just as well replace `ArrayList` with `LinkedList` and the code will still compile. Even though the details of the list implementation differ between `ArrayList` and `LinkedList`, they both provide exactly the methods required by the `List` interface, so it's permissible to swap them.

Polymorphism as supported by Java interfaces supports two very useful quality features in software design:
* **Loose coupling**, because the code using a set of methods is not tied to a specific implementation of these methods.
* **Extensibility**, because we can easily add new implementations of an interface (new "shapes" in the polymorphic relation).

### The Comparable Interface

Common design questions related to interfaces include: *do I need a separate interface?* and *what should this interface specify?* There are no general or template answers to that question, in each case the task is to determine if interfaces can help us solve a design problem or realize a particular feature. One good illustration of both the point and usefulness of interfaces in Java is the [Comparable](http://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html) interface.

In Module 1 we solved the problem of shuffling a collection easily with the use of a library:

```
Collections.shuffle(aCards); // Where aCards is a Stack<Card> instance
```

As you can imagine the library method `shuffle` randomly reorders the objects in the argument collection. This is an example of **code reuse** because you can reuse the library method to re-order any kind of collection. Here reuse is trivially possible because to shuffle a collection, *we don't need to know anything about the items being shuffled*.

But what if we want to reuse code to *sort* the cards in the deck? Sorting, like many classic computing problems, is supported by many existing high-quality implementations. In most realistic software development contexts it would not be acceptable to hand-craft your own sorting algorithm.

The `Collections` class conveniently supplies us with a number of sorting functions, but if we opportunistically try 

```
Collections.sort(aCards); // Where aCards is a Stack<Card> instance
```

without further thinking, we are rewarded with a moderately cryptic compilation error. This should not be surprising, though, because how exactly is a library method supported to know how we want to sort our cards? Not only is it impossible for the designers of library methods to anticipate all the user-defined types that can be invented, but even for a given type like `Card`, different orderings are possible (e.g., by rank, then suit, or vice-versa).

The [Comparable](http://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html) interface helps solve this problem by defining a piece of behavior related specifically to the comparison of objects, in the form of a single `int compareTo(T)` method. Given the existence of this interface, the internal implementation of `Collections.sort` can now rely on it to compare the objects it should sort. You can imagine that the internal code of the `sort` implementation would have statements like:

```
if( object1.compareTo(object2) > 0 )...
```

So here it really does not matter what the object is, *as long as it is comparable with another object*. This is a great example of how interfaces and polymorphism support loose coupling: the code of `sort` depends on the *minimum possible* set of behavior required from its argument objects.

To make it possible for us to sort a stack of cards, we therefore have to provide this *comparable* behavior and declare it through an interface implementation indication:

```
public class Card implements Comparable<Card>
{
	@Override
	public int compareTo(Card pCard)
	{
		return pCard.getRank().ordinal() - getRank().ordinal();
	}
```

Note that this minimal implementation sorts cards by ascending rank, but leaves the order of suits undefined, which in practice leads to unpredictability. In the exercises you will improve this behavior to sort cards by taking suits into account as well. 

Note also that the type-checking mechanism makes it possible for the compiler to detect that a `Stack<Card>` object cannot be passed to `Collections.sort` unless the `Card` class declares to implement the `Comparable` interface. How this happens is outside the scope of this module because it requires a good understanding of the typing rules for Java generic types, something we will see later.

<!--

* Sorting cards, using Collections. Using Comparable, using comparator
* Interface segregation principle
* UML class diagram primer
* Function objects
* Anonymous classes
* Closures
* Iterating over a deck
* How the forall loop works
* Design patterns
* Iterator
* Return to strategy

-->

## Reading
* Textbook 4.1-4.5, 5.1, 5.2, 5.4.3
* Solitaire v0.3 [PlayingStrategy.java](https://github.com/prmr/Solitaire/blob/v0.3/src/ca/mcgill/cs/stg/solitaire/ai/PlayingStrategy.java) as a simple example of a Strategy interface;
* JetUML v1.0 [SegmentationStyle.java](https://github.com/prmr/JetUML/blob/v1.0/src/ca/mcgill/cs/stg/jetuml/framework/SegmentationStyle.java) as a more elaborate example use of the Strategy Design Pattern.

## Exercises



---

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a>

Unless otherwise noted, the content of this repository is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>. 

Copyright Martin P. Robillard 2017