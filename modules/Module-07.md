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
* The [Java Tutorial - Inheritance](https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html)
* JetUML v1.0 The class hierarchy rooted at interface [Node](https://github.com/prmr/JetUML/blob/v1.0/src/ca/mcgill/cs/stg/jetuml/graph/Node.java)

## Notes

### Review of Inheritance

So far we have seen many situations where we can leverage polymorphism to realize various design features. Generally speaking polymorphism makes a design *extensible* and *reusable*. For example, the small design below is extensible because it is possible to add new types of employees without disrupting the existing design. Similarly, features that work with one type of employee can be reused on other types of employees.

![](figures/m07-polymorphism.png)

However, one limitation of this design becomes immediately apparent when we try to implement it. In this design, the root of the type hierarchy is an *interface*, so it defines services without providing any implementation for them. However, the kinds of services it defines, such as being able to return a name for an employee, are likely to be implemented in an identical way by each class. For example:

```java
public class Programmer implements Employee
{
   private String aName;
   private int aSalary;
   ...
	
public class Manager implements Employee
{
   private String aName;
   private int aSalary;
   private int aBonus;	
   ...
```

So here we could say that the design induces *code duplication*, which is generally not desirable. There is an extensive 
literature on the topic of [code clones](https://en.wikipedia.org/wiki/Duplicate_code), but the bottom line is that they should be avoided.

One programming language mechanism readily available to avoid code duplication is *inheritance*. In Java and similar language, inheritance allows programmers to define a class (the subclass) with respect to a base class (or superclass). This avoid repeating declaration, since the declarations of the base class will be automatically taken into account when creating instances of the subclass.

![](figures/m07-inheritance.png)

In UML inheritance is denoted by a *solid line* with a white triangle pointing from the subclass to the superclass. In Java the subclass-superclass relation is declared using the `extends` keyword:

```java
public class Manager extends Employee
{
   private int aBonus;
```

To understand the effect of inheritance on a program, it's important to remember that a class is essentially a *template for creating objects*. Defining a subclass (e.g., `Manager`) as an extension of a superclass (e.g., `Employee`) means that when objects of the subclass are instantiated with the `new` keyword, the objects will be created by collating all the declarations of the subclass and all of its superclasses. The result will be a *single* object. The run-time type of this object will be the type specified in the `new` statement. However, just like interface implementation, class extension induces a suptyping relation. In this sense, an object can always be assigned to a variable of any of its superclasses (in addition to its implementing interfaces):

```java
Employee alice = new Manager();
```

In the code above, a new object of (run-time) type `Manager` is created and assigned to a variable named `alice` of (compile-time) type `Employee`. This is legal because `Manager` is a subtype of `Employee`, just as in the initial case with the interface. Note that when an instance of `Manager` is assigned to a variable of type `Employee`, it does not "become" an employee or "lose" any of the manager-specific fields. In Java, once an object is created, its run-time type remains unchanged, and in this example it would be possible to safely assign the object back to a variable of type `Manager`:

```java
Manager manager = (Manager) alice;
```

In this module, the distinction between **compile-time** type and **run-time** type will become increasingly important. The run-time type of an object is the (most specific) type of an object when it is instantiated, usually through the `new` keyword. It is the type that is returned by method `getClass()`. The run-time type of an object never changes for the duration of the object's life-time. In contrast, the compile-time (or static) type of an object is the type of the *variable* in which an object is stored. In a correct program the static type of an object can correspond to its run-time type, or to any supertype of its run-time type. The static type of an object can be different at different points in the program, depending on the variables in which an object is stored. Consider the following example:

```java
1  public static boolean isManager(Object pObject)
2  {
3     return pObject instanceof Manager;
4  }
5
6  public static void main(String[] args)
7  {
8     Employee alice = new Manager();
9     Manager manager = (Manager) alice;
10    boolean isManager1 = isManager(alice);
11    boolean isManager2 = isManager(manager);
12 }
```

Here at line 8 an object is created that is of run-time type `Manager` and assigned to a variable of (static) type `Employee`. As stated above, the run-time type of this object remains `Manager` throughout the execution of the program. However, at line 9 the static type of the object is `Manager`, and at line 3 it is `Object` (a formal parameter is a kind of variable, so the type of a parameter acts like a type of variable).

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