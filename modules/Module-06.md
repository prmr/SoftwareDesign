## Module 6 - Inversion of Control

## Description

The idea of inversion of control is one of the most powerful intellectual tools in a software designer tool-box. It allows us to build incredibly sophisticated applications while keeping the overall design complexity down to a manageable level. The Observer pattern is extremely common in software development, and it's realized by most GUI toolkits on most software development platforms, from desktop to web to mobile applications. This module is dedicated to the principle of inversion of control (IoC) and its realization in the Observer pattern, also called the Model-View Controller (MVC) pattern.

## Learning Objectives

After this module you should:

* Be able to use the Observer design pattern effectively;
* Be able to design and implement simple graphical user interfaces with Swing and JavaFX;
* Understand the concept of an application framework;

## Notes

### Motivation

One of the main problems that motivates inversion of control in design is situations where a number of objects need to be kept consistent with a certain state. An example from the programming domain itself is an integrated development environment like Eclipse, which presents different views of the code. For example, the Outline View shows the outline of a class that can also be viewed in the text editor, etc.
I illustrate a simpler instance of this problem with the [LuckyNumber](artifacts/module-06/module6/LuckyNumber.java) toy application. When launched this application shows a number between 1 and 10 in three different ways (or with three different *views*:

![](figures/m06-luckyNumber.png)

Each view of the number also allows the user to change the number, and the change is immediately reflected in all views.

A naive (and inferior) way to implement this functionality is through *complete pairwise dependencies*.

![](figures/m06-dependencies.png)

This design suffers from (at least) the following two inter-related limitations:

* **High coupling**: Each panel explicitly depends on many other panels.
* **Complexity**: Complex idiosyncratic program logic is required to keep the different panels consistent.
* **Low Extensibility**: To add or remove a panel, it is necessary to modify all other panels. 

Furthermore, these limitations all increase quadratically in the number of panels, given that there are `n*(n-1)` directed edges in a complete graph with `n` vertices.

The way out of this design antipattern is to separate program elements responsible for *storing state* from program elements responsible for *viewing state*, from program elements responsible for *changing state*, and to use various mechanisms to achieve loose coupling between these. This profoundly influential idea is commonly known as **the Observer Design Pattern** or (somewhat alternatively) **the Model-View-Controller** architecture (MVC). In this course I stick to the "Observer pattern" terminology, but it's good to be aware that in other contexts people may talk about the MVC and refer to essentially the same thing.

### The Observer Design Pattern

The central idea of the Observer design pattern is to store state of interest in specialized objects, and to allow other objects to *observe* this state. The class diagram below illustrates how this is realized for the LuckyNumber application.

![](figures/m06-basicObserver.png)

In this situation, the object in charge of keeping state is an instance of `Model`. The `Model` class in the Oberver design pattern can alternately be called "Subject", or even "Observable". Here an instance of `Model` simply keeps track of an integer and allows clients to query and mutate this integer. Where things become interesting is that the `Model` class also includes an aggregation to an `Observer` interface, with methods to add and remove `Observer` instances from its collection. This is also called *(de)registering* observers. Classes that define objects that would be interested in observing the state of the model must then declare to implement the `Observer` interface. Through polymorphism, we thus achieve loose coupling between the model and its observers. Specifically:

* The model can be used without any observers;
* The model is aware that it can be observed, but its implementation does not depend on any concrete observer class.
* It is possible to register and de-register observers at run-time.

Two key questions about the relation between a model an its observers are:

* How do the observers learn that there is new information in the model that they need to know about?
* How do they access this information?

The answer to the first question is that whenever the model determines that there is a change in the model worth reporting to observers, it cycles through the observers and calls a certain method on them. This method has to be defined on the `Observer` interface and is usually called a "callback" because of the inversion of control that it represents. We talk of inversion of control because to find out information from the model the observers do not call a method on the model, they instead "wait" for the model to call them. This is often referred to as the "Hollywood Principle" ("don't call us, we'll call you"). That is also why the method that is called by the model on the observer is called a "callback". The following sequence diagram illustrates what happens when we change the model on the "LuckyNumber" application.

![](figures/m06-callbacks1.png)

Here, inside the state-changing method `setNumber(int)`, we added logic to loop through each observer and call the method `newNumber` on each. This `newNumber` method is a "callback" that the observers will expect to be called every time there is a state change in the model. The implementation of the callback dictates how each observer reacts to the change in state. 

Another way to think about callback methods is as "events", with the model being the "event source". With this paradigm, the model generates a series of "events" that correspond to different state changes, and other objects are in charge of reacting to these events. What events correspond to in practice is simply methods calls.

## Reading

* Textbook 5.3, 8.1, 8.4;
* The [JavaFX Tutorial](http://docs.oracle.com/javafx/2/get_started/hello_world.htm)
* Solitaire v0.3 The [DeckView](https://github.com/prmr/Solitaire/blob/v0.3/src/ca/mcgill/cs/stg/solitaire/gui/DeckView.java) class as an example of a GUI observer.

## Exercises

Exercises prefixed with **(+)** are optional, more challenging questions aimed to provide you with additional design and programming experience. Exercises prefixed with **(P)** (for "project") will incrementally guide you towards the ultimate completion of a complete Solitaire application.

---

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a>

Unless otherwise noted, the content of this repository is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>. 

Copyright Martin P. Robillard 2017