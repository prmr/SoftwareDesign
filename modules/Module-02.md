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