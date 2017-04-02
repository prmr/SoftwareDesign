## Module 8 - Concurrency

## Description

*Concurrent programming* is a paradigm that allows developers to write code that can execute in parallel (on multi-core systems) or with the illusion of parallelism (on single-core ones). Concurrent programming is very, very challenging and is ideally only used in support of well-defined and well-understood design problems, such as performing background operations or displaying animations. In fact, many typical concurrent programming problems already have a framework-based solution that hides the use of concurrency primitives. In this module, I will present the foundations of object-oriented concurrent programming and a few examples of the use of concurrency in practice.

## Learning Objectives

After this module you should:

* Understand the concept of a Thread and its usefulness for programming;
* Be able to write basic concurrent programs in Java;
* Understand the causes of basic concurrency errors including race conditions and deadlocks, and the mechanisms that help prevent them.
* Be able to recognize when to and when not to use concurrency in application design;

## Reading

Textbook: Chapter 9;

## Notes

## Exercises

Exercises prefixed with **(+)** are optional, more challenging questions aimed to provide you with additional design and programming experience. Exercises prefixed with **(P)** (for "project") will incrementally guide you towards the ultimate completion of a complete Solitaire application.

**Exercise 1.**

The following exercises in the textbook: 9.1,9.2, 9.5, 9.7, 9.8, 9.9-9.11.

**Exercise 2.**

Write a program where one thread (`NumberIncrementer`) keeps adding to a shared data structure (make it a class `NumberBox`), and another thread (`NumberPrinter`) sleeps, periodically wakes up, and prints whatever number is in the box. Do you need to use synchronization? why or why not?

**Exercise 3.**

Change the program of Exercise 2 so the number-incrementer threads only increments number every, say, 1 second. Use conditions to notify the printer thread that a new number is available.

**Exercise 4. (+)**

Enhance the NumberIncrementer application so it shows the number in the box in a Swing program. First try to implement the functionality with a sleeper thread, as above, then implement a better version of the solution using a [Swing timer](http://docs.oracle.com/javase/8/docs/api/javax/swing/Timer.html). 

---

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a>

Unless otherwise noted, the content of this repository is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>. 

Copyright Martin P. Robillard 2017