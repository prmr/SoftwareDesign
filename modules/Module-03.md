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

<!--

* Understanding state: abstract vs concrete state 
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