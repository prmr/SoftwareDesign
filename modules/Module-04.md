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

### Test Suite Organization

A collection of tests for a project is known as a **test suite**. A common question is how to organize our tests in a sensible matter. There are different approaches, but in Java a common way to organize tests is to have one *test class* per *project class*, where the test class collects all the tests that test methods of that class. Furthermore, it is common practice to located all the testing code in a different *source folder* with a package structure that *mirrors the project's package structure*. The rationale for this idiom is that in Java classes in the same package are in the same *package scope* independently of their location on the disk. This means that classes and methods in the test package can refer to non-public (but non-private) members of the original project. This often simplifies the test code. The figure below illustrates this idea.

![Test Suite Organization](figures/m04-TestSuiteOrganization.png)

### Test Fixtures

In test classes that group multiple test methods, it will often be convenient to define a number of "default" objects or values to be used as receiver objects and/or oracles. This practice will avoid the duplication of setup code in each test method. Baseline objects used for testing are often referred to as a *test fixture*, and declared as fields of a test class. However, when designing a test suite with JUnit, is it extremely important to know that JUnit **provides no ordering guarantee of any kind* for the execution of unit tests. In consequence, unit tests must be independent of each other so that they can be executed in any order. This further implies that no test method should rely on the fixture being left in a given state by another test. In most cases this precludes the use of the class constructor to initialize the fixuture, because the constructor is only called once. The workaround is to nominate a method of the test class to execute before any test method. In JUnit this method is nominated using te `@Before` annotation. Fixture initialization code should therefore be located in this method. The use of test fixtures in JUnit is illustrated by most test classes in both sample projects. For example, in the Solitaire application's [TestSuitStackManager](https://github.com/prmr/Solitaire/blob/master/test/ca/mcgill/cs/stg/solitaire/model/TestSuitStackManager.java) class, the fixture consists of a single field `aSuitStackManager` which is initialized with a fresh object in method `setup`:

```
@Before
public void setup()
{
	aSuitStackManager = new SuitStackManager();
}
```

which is executed before the execution of every `@Test`-annotated method.

### Tests and Exceptional Conditions

An important point when writing unit tests is that what we are testing is *that the UUT does what it's supposed to*. This means that when using design by contract, it does not make sense to test code with input that does not respect the UUT's preconditions, because the resulting behavior is *unspecified*. For example, for method [peek](https://github.com/prmr/Solitaire/blob/master/src/ca/mcgill/cs/stg/solitaire/model/SuitStackManager.java#L104), which has as precondition `assert !aStacks.get(pIndex).isEmpty();`, it would be awkward to try to write a test for an empty stack. What would be the oracle?

The situation is very different, however, when exceptional behavior *is explicitly part of the interface*. For instance, for method [peek](https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html#peek--) of the Java `Stack` class, peeking an empty stack *should* result in an `EmptyStackException`. If it does not, then the `peek` method does not do what is expected, and this means it is faulty.

This situation raises the question of how to test for exceptional conditions. In JUnit there are two idioms. One is to use the `expected` property of the `@Test` annotation:

```
@Test(expected = EmptyStackException.class)
public void testPeekEmpty1()
{
	Stack<String> stack = new Stack<>();
	stack.peek();
}
``` 

With this feature, JUnit will automatically *fail the test* if the execution of the corresponding test method completes *without* raising an exception of the specified type. This testing idiom is very useful, but limited in the sense that the exceptional behavior must be the last thing to happen in the test. In cases where it is desirable to execute additional testing code after testing the exceptional behavior, the following idiom can be used:

```
@Test
public void testPeekEmpty2()
{
	Stack<String> stack = new Stack<>();
	try
	{
		stack.peek();
		fail();
	}
	catch(EmptyStackException e )
	{}
}
```

This idiom is a bit convoluted, but does exactly what we want. If the UUT (the `peek` method here) is faulty in the sense that it does not raise an exception when it should, the program will keep executing normally and reach the following statement, which will force a test failure. If the UUT is (at least partially) correct in that it does raise the exception when it should, control-flow will immediately jump to the catch clause, thereby skipping the `fail();` statement. It is then possible to add additional code below the catch clause.

### Encapsulation and Unit Testing

A common question when writing tests is, how can we test private methods? There are possible avenues for answering that question, but experts disagree about which one is the right one:

* Private methods are internal elements of other, accessible methods, and therefore are not really "units" that should be tested. Following this logic, the code in private methods should be tested indirectly through the execution of the accessible methods that call them;
* The `private` access modifier is a tool to help us structure the project code, and tests can ignore it.

Although I understand the rationale of the first approach, I personally fall in the second camp. There are many cases where a neat little method can be restricted to a class's scope, but it would still be valuable to test it in isolation. This situation is exemplified by the tests added to test [this fix](https://github.com/prmr/JetUML/issues/188).

To by-pass the `private` access modifier, it is necessary to use [metaprogramming](https://docs.oracle.com/javase/tutorial/reflect/).

The following code sample illustrates the main steps:

```
public class TestEditorFrame 
{ 
	private Method aCreateFileFilter;
	private EditorFrame aEditorFrame; 
	
	@Before
	public void setup() throws Exception
	{
		aCreateFileFilter = EditorFrame.class.getDeclaredMethod("createFileFilter", String.class);
		aCreateFileFilter.setAccessible(true);
		aEditorFrame = new EditorFrame(UMLEditor.class);
	}
```

This test class definition includes, as part of its fixture, a field that stores a reference to an instance of class `Method` that represents the private method we want to test. The field is initialized in the `setup` method, and made accessible outside the class scope using the `setAccessible` method. This last part is what bypasses the `private` keyword.

In the test class, we can then define a *convenience method* that launches the execution of the UUT (`createFileFilter`):

```
private FileFilter createFileFilter(String pFormat)
{
	try
	{
		return (FileFilter) aCreateFileFilter.invoke(aEditorFrame, pFormat);
	}
	catch(InvocationTargetException | IllegalAccessException pException)
	{
		TestCase.fail();
		return null;
	}
}
```

In the methods of the test class, calling `createFileFilter` now has the same effect as calling `createFileFilter` on an instance of `EditorFrame`. This way the rest of the test looks pretty normal:

```
@Test
public void testCreateFileFilteAcceptDirectory()
{
	FileFilter filter = createFileFilter("PNG");
	File temp = new File("foo");
	temp.mkdir();
	assertTrue(temp.isDirectory());
	assertTrue(filter.accept(temp));
	temp.delete();
}
```

except that it does not directly call the UUT, but a wrapper that uses metaprogramming to call the UUT while bypassig the access restriction of the `private` keyword.

### Testing with Stubs

The key to unit testing is to tests small parts of the program *in isolation*. But what happens if the part we want to test triggers the execution of a large chunk of the program. This situation is illustrated in the following design, which is a simplified version of the Solitaire sample application. The `GameEngine` has an `automove()` method that triggers the computation of the next move by dynamically delegating the task to a strategy, which could be any of the three options. Here we would like to write a unit test for the `GameEngine.automove()` method.

![Test Suite Organization](figures/m04-Stubs.png)

In this task we face at least three questions:

* Calling the `void executeMove(...)` method on any strategy will trigger the execution of presumably complex behavior by the strategy, which possible depends on many other parts of the code. This does not align well with the concept of unit testing, where we test small pieces of code in isolation.
* How can we know which strategy would be used by the game engine? Presumably we need to determine an oracle for the results.
* How is this different from testing the strategies individually?

The way out of this conundrum is the realization that the responsibility of `GameEngine.automove()` is *not* to compute the next move, but rather *to delegate this to a strategy*. So, to write a unit test that tests that the UUT does what it is expected, we *only need to verify that it relays the automove computation to a strategy*. This can be achieved with the writing of a *stub* (a.k.a. [mock object](https://en.wikipedia.org/wiki/Mock_object), although there's no agreement on terminology).

An object stub is a greatly simplified version of an object that mimics its behavior sufficiently to support the testing of a UUT that uses this object. Using stubs is heavily dependent on types and polymorphism (see Module 2). Continuing with our `automove` situation, here we simply want to test that the method calls a strategy, so we will define a dummy strategy in the test method:

```
public class TestGameEngine
{
	@Test
	public void testAutoPlay() throws Exception
	{
		class StubStrategy implements PlayingStrategy
		{
			private boolean aExecuted = false;
			
			public boolean hasExecuted() { return aExecuted; }
			
			@Override
			public void executeMove(GameEngine pGameEngine)
			{
				aExecuted = true;				
			}
		}
```

This strategy does nothing except remember that its `executeMove` method has been called. We can then use an instance of this stub instead of a "real" strategy in the rest of the test. To inject the stub in the game engine, we again rely on metaprogramming:

```
@Test
public void testAutoPlay() throws Exception
{
	...
	Field strategyField = GameEngine.class.getDeclaredField("aStrategy");
	strategyField.setAccessible(true);
	StubStrategy strategy = new StubStrategy();
	GameEngine engine = GameEngine.instance();
	strategyField.set(engine, strategy);
```

at which point completing the test is simply a matter of calling the UUT (`automove`) and verifying that it did properly call the strategy:

```
engine.autoMove();
assertTrue(strategy.hasExecuted());
```

The use of mock objects in unit testing can get extremely sophisticated, and framework exists to support this task (e.g., [jMock](http://www.jmock.org/)). In this course, the use of stubs/mocks will be limited to simple situations like the one illustrated here.

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