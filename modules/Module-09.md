## Module 9 - Concurrency

## Description

*Concurrent programming* is a paradigm that allows developers to write code that can execute in parallel (on multi-core systems) or with the illusion of parallelism (on single-core ones). Concurrent programming is very, very challenging and is ideally only used in support of well-defined and well-understood design problems, such as performing background operations or displaying animations. In fact, many typical concurrent programming problems already have a framework-based solution that hides the use of concurrency primitives. In this module, I will present the foundations of object-oriented concurrent programming and a few examples of the use of concurrency in practice.

## Learning Objectives

After this module you should:

* Understand the concept of a Thread and its usefulness for programming;
* Be able to write basic concurrent programs in Java;
* Understand the causes of basic concurrency errors including race conditions and deadlocks, and the mechanisms that help prevent them.
* Be able to recognize when to and when not to use concurrency in application design;

## Notes

## Reading

* [Java Tutorial - Concurrency](https://docs.oracle.com/javase/tutorial/essential/concurrency/)

## Exercises

Exercises prefixed with :star: are optional, more challenging questions aimed to provide you with additional design and programming experience. For maximum learning effectiveness, I recommend peeking at the [answers](answers/Answers-09.md) only after giving the problems an honest try.

1. Write a program where one thread (`NumberIncrementer`) keeps adding to a shared data structure (make it a class `NumberBox`), and another thread (`NumberPrinter`) sleeps, periodically wakes up, and prints whatever number is in the box. Do you need to use synchronization? why or why not?
2. Change the program of Exercise 2 so the number-incrementer threads only increments number every, say, 1 second, and the printer
thread only obtains a number when a new one is available. Use conditions.
3. Enhance the version developed for Exercise 2 so that a third (timer) thread interrupts both counter and printer threads after the number reaches 10.

---

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a>

Unless otherwise noted, the content of this repository is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>. 

Copyright Martin P. Robillard 2017