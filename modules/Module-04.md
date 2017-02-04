# Module 4 - Unit Testing

## Description

How can we have confidence that our code is working properly? Every time we write or change a line of code, we could be introducing a lethal bug. *Unit testing* is a practice wherein we automatically execute our code to check that it does what we think it should. With unit testing, we can build a possibly large collection of tests that can quickly be run, for instance every time we change the code, to make sure we didn't break anything that used to work. In this module, I will introduce mechanisms that facilitate unit testing (reflection and type annotations) and provide you with basic techniques for designing unit tests and evaluating their quality.

## Learning Objectives

After this module you should:

* Be able to explain the foundational concepts of testing using the proper terminology;
* Understand type annotations and program reflection and be able to use them effectively;
* Be able to write unit tests with JUnit;
* Be able to approach more advanced testing problems requiring reflection or mock objects;
* Be able to understand the output of a test coverage tool such as EclEmma;
* Be able to understand basic test suite adequacy criteria and the relations between them;

## Notes

### General Concepts and Definitions

Software quality problems are often caused by programmers writing code that doesn't quite do what they expect. For example, this [bug](https://github.com/prmr/JetUML/issues/188) in JetUML made it impossible to see the directory structure in a file chooser object because of an incorrect *compound* (i.e., multi-part) condition.

One way to detect bugs, and to gain confidence that a part of a program does what we expect, is to *test* it. [Testing](https://en.wikipedia.org/wiki/Software_testing) in the general sense is a software quality assurance technique that can take many forms. In the context of an introduction to software design, I will be using a specific testing approach called [Unit testing](https://en.wikipedia.org/wiki/Unit_testing). The idea of unit testing is to test a very small part of the program in isolation. This way, if the test fails, it is easy to know where to look for problems.

In practice, a **unit test** consists in one execution of a **unit under test (UUT)** with some **input data** and the comparison of the result of the execution against some **oracle**. For example, the statement:

```
Math.abs(5) == 5
```

technically qualifies as a test. Here the UUT is the library method `Math.abs(int)`, the input data is the integer 5, and the oracle is, in this case, also the value 5. When testing non-static method, it's important to remember that the input data includes the receiver object (the object that receives the method call).

Although it is possible to test a system manually, in practice unit testing is done automatically. Since in software development the way to automate anything is to write a program to do it, to automate software testing we also write code to test other code. 

This task is typically supported by a **unit testing framework** like [xUnit](https://en.wikipedia.org/wiki/XUnit), which in the case of Java means [JUnit](https://en.wikipedia.org/wiki/JUnit). JUnit automates a lot of the mundane parts of unit testing, including collecting tests, running them, and reporting the results.

### Basics of JUnit

In JUnit a unit test maps to a method. The code below illustrates a very simple unit test with JUnit.

```
public class AbsTest
{
	@Test
	public void testAbsPositive()
	{
		assertEquals(5,Math.abs(5));
	}
	
	@Test
	public void testAbsNegative()
	{
		assertEquals(5,Math.abs(-5));
	}
	
	@Test
	public void testAbsMax()
	{
		assertEquals(Integer.MAX_VALUE,Math.abs(Integer.MIN_VALUE));
	}
}
```

The `@Test` [Annotation](https://docs.oracle.com/javase/tutorial/java/annotations/index.html) indicates that the method should be run as a unit test. This annotation is defined in the JUnit library, which must be added to a project's classpath before it is visible. The test method should typically contain at least one call to a UUT. The way to automatically verify that the execution of a UUT has the expected effect is to execute various calls to `assert*` methods. Assert methods are different from the `assert` statement in Java. They are declared as static methods of the class `org.junit.Assert*` and all they do is basically verify a predicate and, if the predicate is not true, report a *test failure*. The JUnit framework includes a GUI component called a *test runner*. To execute the JUnit test running from within Eclipse, right-click on a project that contains at least one jUnit test method, and select `Run As | JUnit Test`. When executing, all the JUnit test runner does is inspect the program, collect all methods flagged as unit tests using annotations, and invoke them, then report whether the tests passed on failed.

For additional instructions on how to use JUnit, read [this tutorial](http://www.vogella.com/tutorials/JUnit/article.htm). To fully understand how JUnit works it is necessary to first read the [Annotations](https://docs.oracle.com/javase/tutorial/java/annotations/index.html) and [Reflection](https://docs.oracle.com/javase/tutorial/reflect/) tutorials.

## Reading

* Textbook 3.7, 7.2, 7.6;
* The [Java Tutorial - Annotations](https://docs.oracle.com/javase/tutorial/java/annotations/index.html)
* The [Java Tutorial - Reflection](https://docs.oracle.com/javase/tutorial/reflect/)
* The [Vogella Unit Testing Tutorial](http://www.vogella.com/tutorials/JUnit/article.html)
* Solitaire v0.3 [TestGameModel](https://github.com/prmr/Solitaire/blob/v0.3/test/ca/mcgill/cs/stg/solitaire/model/TestGameModel.java) as a sample unit test demonstrating the use of reflection and simple mock objects.

## Exercises

Exercises prefixed with **(+)** are optional, more challenging questions aimed to provide you with additional design and programming experience. Exercises prefixed with **(P)** (for "project") will incrementally guide you towards the ultimate completion of a complete Solitaire application.

---

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a>

Unless otherwise noted, the content of this repository is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>. 

Copyright Martin P. Robillard 2017