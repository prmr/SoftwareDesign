# Module 3 - Object State

## Description

The most difficult thing to reason about when looking at a program is state changes. What operations can have a side-effect, on which path can data flow, what impacts what? Is this module, I will clarify what object state is and how we can manage to keep control over its state in a principled way so we don't let the genie out of the bottle every time we instantiate an object.

## Learning Objectives

After this module you should:

* Understand the difference between a static and dynamic perspective on software;
* Understand the meaning and implications of shared state and unique objects;
* Understand the difference between the concepts of object identity and object equality, and they implications for software design;
* Be able to correctly and effectively use the methods of the Object class;
* Be able to effectively use the Singleton and Flyweight design patterns;
* Be able to effectively use UML State Diagrams to reason about the abstract states of complex stateful objects;
* Be able to correctly use Java's cloning mechanism;

## Notes

### General Concepts and Definitions

There are different way we can look at a software system. One way is in terms of the software elements declared in the source code and the relations between them. For example, a `Deck` class declares a field `aCards` field that is a stack of `Card` instances. This is a **static view** of the system. The static view is best represented by the source code or a UML class diagram. A different, but complementary, way to look at a system, is in terms of *values* and *objects* in a running program. For example, at one point a `Deck` contains three cards, then one card is drawn, which leads to the instance of `Deck` containing two cards. This is the **dynamic (or run-time) view** of the system. The dynamic view corresponds to what we see in a debugger while stepping through the execution of a program. The dynamic view is *cannot* easily be represented by any one diagram. Instead, we rely on object diagrams, *state diagrams* (introduced in this module), and *sequence diagrams* (introduced in Module 5). The static and dynamic views are two complementary perspectives in software design. Sometimes it's best to think of a problem and solution in static terms, sometimes in dynamic terms, and sometimes we really need both. This module focuses on understanding important *dynamic* properties of software.

This duality between the static and dynamic perspective on a software system is akin to the wave-particle duality for representing the phenomenon of light in physics:

> It seems as though we must use sometimes the one theory and sometimes the other, while at times we may use either. […] We have two contradictory pictures of reality; separately neither of them fully explains the phenomena of light, but together they do. - Albert Einstein and Leopold Infeld, The Evolution of Physics, pg. 262-263

So, to paraphrase for software design: It seems as though we must use sometimes the one perspective and sometimes the other, while at times we may use either. We have two *complementary* pictures of a program; separately neither of them fully explains the phenomena of software, but together they do.

An important concept when thinking of a design in terms of run-time objects is that of **object state**. Informally, state refers to the particular pieces of information the object represents at a given moment. To speak more precisely it is generally useful to distinguish between *concrete state* and *abstract state*. The **concrete state** of an object is the collection of values stored in the object's fields. For example, if we consider a `BankAccount` object that is simply a wrapper for an `int`:

```
public class BankAccount
{
	private int aAmount = 0;
	...
```

the cardinality of the space of possible concrete states for `BankAccount` is `2^32`, or about 4 billion states. As soon as objects have fields of reference types, the size of the state space explodes dramatically. For example, the state space of a `Deck` object includes all possible permutations of any number of cards in the deck, a number in the range of 2e68 (two times 10 to the 68th power). In the case of the `BankAccount` object, adding an `aName` field of type `String` blows up the size of the state space to something that is only limited by the physical memory of the computing system. For this reason, when designing software, it is more practical to think in terms of *abstract states*. 

In principle, an **abstract state** is an arbitrarily-defined subset of the concrete state space. For example "even balance" could be an abstract state for the `BankAccount` object that groups the roughly `2^31` states representing an even number of some currency in the account. Likewise for an instance of the `Deck` class, the abstract state "three kings" could represent any possible configuration of the deck where exactly three cards of rank `Rank.KING` are present. These two examples illustrate the fact that because abstract states are *arbitrary* partitions of the state space, they can really be defined as anything. It should however be evident that neither of these two abstract states would be particularly useful for designing a software solution to either the problem of bank management or card game playing. In practice, the software design task is to define abstract states that correspond to characteristics that will help construct a clean solution. A more useful abstract state for `BankAccount` would be "positive balance", and one for `Deck` would be "empty" (no cards in the deck), which in this case corresponds to a single concrete state.

### UML State Diagrams

**UML State Diagrams** represent a *dynamic*, or *run-time* view of a software system. They are useful to represent how objects can *transition* from one abstract state to another during their lifetime as a reaction to external events (typically, method calls). The annotated diagram below provides all the notation I will be using in the course.

![UML State Diagram Cheat Sheet](figures/m03-stateDiagram.png)

The following example illustrates both the notation and purpose of UML state diagrams. It models some of the important abstract states of an instance of a class `Deck` that represents a deck of 52 playing cards. Even this simple diagram captures key information about the design of the `Deck` class.

![UML State Diagram Example 1](figures/m03-stateDiagramEx1.png)

The abstract state `Empty` is annotated as the **start state**, which allows us to infer that the constructor returns a reference to a new deck object with no cards in it. 

In UML State Diagrams, **absence of a transition** usually means that the absent transition is *invalid* for that state. Here we can see that we cannot `draw` cards from an empty deck. Note that the transitions are annotated with names that correspond to methods of the class `Deck`. The only **legal transition** out of the `Empty` state is `shuffle` which brings the object in the `Complete` state. From this it can easily be inferred that "Complete" is a shorthand for "Complete and shuffled" (in this particular design). 

The `shuffle` transition out of the `Complete` state illustrates the idea of **self transitions**, namely, events that do not result in a change of abstract state. The only legal transition out of the `Complete` state is `draw`, which inevitably brings the deck object to an `Incomplete` state. 

It is also possible to annotate transitions with an **action** that describes what happens as the result of the transition. The action that corresponds to the `draw` event is "remove card from the deck". The action information is optional and here I chose to leave it out of the diagram because it is obvious, and therefore adds no value to the diagram. 

The two transitions out of the `Incomplete` state illustrate the importance of **conditions**, because here without the concept of a condition we would not be able to model the distinction between a `draw` event that leads to the `Empty` state, and a `draw` event that keeps the object in the `Incomplete` state. In this course, the language I use for modeling condition does not need to follow a formal specification, but I nevertheless like to specify the conditions using pseudo-code that is very close to what could be reasonably tested on an instance of the object. Here the conditions would assume the presence of an (at least private) `size` method in the `Deck` class.

Finally, this diagram does not include any **end state**. The end state model element is used to specify if an object *must* be in a certain state at the end of its lifetime (i.e., in Java, when it is garbage collected). In many designs, objects can end their life (stop being used) in any state, in which case the end state model element does not apply.

An important benefit of State Diagrams is that they allow us to self-evaluate the impact of design decisions on the complexity of the abstract state space that must be considered when working with an object. Here the state space is very simple (3 states) *because of the decision to bundle the deck initialization code together with the shuffling code*. Separating this behavior into distinct `initialize` and `shuffle` events, or including a `sort` event, leads to a much more complicated behavior for the object.

Another important benefit of State Diagrams (or state modeling in general) is that is supports a systematic reasoning about the behavior of an object of a given class. When modeling the state of an object, a good practice is to visit each state and consider each possible type of event. This simple procedure is an excellent way to avoid overlooking certain paths through a program (e.g., shuffling an incomplete deck).

When getting started with modeling object state with UML State Diagrams, one common tendency is to use the state diagram notation to model a type of "data-flow" information, where states represent "processing", and arrows represent the flow of data between processing stages. This is an incorrect use of the notation. A good tip for avoiding this pitfall is to think about the names of the states. If the names assigned to "states" include verbs or generally feel like they are describing actions (e.g. "draw card"), it is probably a sign that the diagram does not represent a good model of the state space.

Finally, it will be readily apparent that the concept of state diagrams is very similar to that of [Deterministic finite automata (DFA)](https://en.wikipedia.org/wiki/Deterministic_finite_automaton). This is not a coincidence, as both notations are intended to model some sort of stateful phenomenon. However, the purpose of each notation is different. While the purpose of DFAs is to support the formal modeling of computation, the purpose of UML state diagrams is to support software engineering tasks such as software design, testing, and documentation.

<!--

* State diagrams 
* Review of mutability: a single unique state
* Sharing of references, orthogonal from mutability. Example
* Aside: sharing with instances of anonymous classes and lambda expressions
* Uniqueness and identity
* Flyweight pattern
* Singleton 
* Cloning
* Equality

-->

## Reading

* Textbook 2.2, 2.10, 7.1-7.4, 10.5
* Solitaire v0.3: [Card](https://github.com/prmr/Solitaire/blob/v0.3/src/ca/mcgill/cs/stg/solitaire/cards/Card.java) as an example of the Flyweight pattern in action.
* Solitaire v0.3: [CardImages](https://github.com/prmr/Solitaire/blob/v0.3/src/ca/mcgill/cs/stg/solitaire/cards/CardImages.java) as a different example of the Flyweight pattern in action.

## Exercises

Exercises prefixed with **(+)** are optional, more challenging questions aimed to provide you with additional design and programming experience. Exercises prefixed with **(P)** (for "project") will incrementally guide you towards the ultimate completion of a complete Solitaire application.


---

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a>

Unless otherwise noted, the content of this repository is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>. 

Copyright Martin P. Robillard 2017