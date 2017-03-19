## Module 7 - Inheritance

## Description

*Inheritance* is a programming language-supported mechanism that allows us to assemble state and computation from different classes into a single object. It is a powerful feature that offers a natural solution to many design problems related to code extensibility and dynamic configuration. At the same time, it is a complex mechanism that can all too easily be misused. In this module, I will offer a review of inheritance and present the major design rules and patterns involving it.

## Learning Objectives

After this module you should:

* Have a deep understanding of the motivation for and conceptual foundations of inheritance;
* Be able to create class hierarchies that involve inheritance;
* Know about common problems with inheritance and how to avoid them;
* Be able to use the Template Method Design Pattern effectively;

## Reading

* Textbook Chapter 6;
* JetUML v1.0 The class hierarchy rooted at interface [Node](https://github.com/prmr/JetUML/blob/v1.0/src/ca/mcgill/cs/stg/jetuml/graph/Node.java)

## Exercises

Exercises prefixed with **(+)** are optional, more challenging questions aimed to provide you with additional design and programming experience. Exercises prefixed with **(P)** (for "project") will incrementally guide you towards the ultimate completion of a complete Solitaire application.

**Exercise 1.**

A bike courier company uses a Scheduler system to schedule bikers for delivery based on various factors (unimportant for this practice question). The company wants the flexibility to install different scheduling algorithms. However, all scheduling algorithms should follow these steps:

   a) check if at least one biker is available, and if not throw an exception;
   
   b) schedule a biker using a given algorithm;
   
   c) notify interested observers that a biker was scheduled.
	
Operations a) and c) are the same for all algorithms, but should be isolated in separate methods. Concrete schedulers should also have the flexibility to throw algorithm-specific types of exceptions if they cannot fulfill a scheduling request. Assume all exceptions for this design are checked. Complete the following UML class diagram to provide a design for these requirements. Use the TEMPLATE METHOD design pattern. When relevant to the design, make sure to include the appropriate modifiers for methods and/or classes (`final`, `public`, `protected`,`private`, `abstract`, etc.). Illustrate support for two different scheduling algorithms. Include the OBSERVER mechanism for biker notification. Write the code necessary to implement the relevant parts of your design.

**Exercise 2. (P)**

In the Solitaire application an instance of `Move` represents a possible move in the game. Design a class hierarchy that captures all the possible implementations of `Move`.

**Exercise 3. (P)**

Study the design of the GUI of the Solitaire application v0.4. As part of your study, create class and sequence diagrams that capture the key design decisions of the implementation. Note how inheritance is used. 

**Exercise 4. (P+)**

Fill in the implementation of the Solitaire GUI on your own based on the design completed as part of Exercise 3, only looking at the code if you get stuck. Feel free to add improvements to the look and feel of the application.

---

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a>

Unless otherwise noted, the content of this repository is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>. 

Copyright Martin P. Robillard 2017