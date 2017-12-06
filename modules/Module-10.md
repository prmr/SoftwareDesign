## Module 10 - Serialization

## Description

Sometimes, the data in a running program needs to be transferred out of the program, for example to be stored in a file or transmitted over a network. In this module, I will cover the main techniques for serializing object graphs using a variety of frameworks including Java's binary serialization and JavaBeans frameworks and JSON serialization APIs. This module will also conclude the course with an introduction to the agile development practice called refactoring.

## Learning Objectives

After this module you should:

* Understand the basic problems and trade-offs related to the serialization of objects graphs;
* Be able to make an informed decision about the best serialization strategy for a given design situation;
* Be able to serialize simple object graphs in Java using JDK frameworks and a JSON API;
* Know about the concepts of code smells and refactoring
* Be able to recognize common bad smells in code;
* Be able to perform a selection of common object-oriented refactorings;

## Notes

## Reading

* Introduction to [the JSON format](http://json.org/);
* Documentation for [XMLEncoders](http://www.oracle.com/technetwork/java/persistence4-140124.html);
* JetUML v1.0 [PersistenceService](https://github.com/prmr/JetUML/blob/v1.0/src/ca/mcgill/cs/stg/jetuml/framework/PersistenceService.java) class as an example of XML serialization.
* Chapter 3 and descriptions of refactorings seen in class in [Fowler's book](https://mycourses2.mcgill.ca/d2l/le/content/203788/viewContent/2563582/View)


## Exercises

Exercises prefixed with :star: are optional, more challenging questions aimed to provide you with additional design and programming experience.

1. Serialize the `Corporation` object created in `labtest01.Driver` using the four types of serialization seen in class. Note that saving the graph in CSV will require some hand-crafted encoding conventions, such as repeating field values. Using the debugger, inspect the deserialized object graph to investigate whether the sharing of the identity of `Item` objects is respected or not.
2. Once you have a file with the serialized version of the corporation in all four formats, add a boolean field to the `Item` class, for example `aOnSale`. Then, attempt to deserialize the saved versions. What happens then? Solve the problem for each technique using the most appropriate mechanism.
3. On the Solitaire sample application, use the Eclipse Extract Interface refactoring tool on Deck and extract the methods size and draw. Call the new interface `CardSource` for example. Notice how the tool automatically figures out that the interface to a `Deck` instance can be narrowed down to a `CardSource` in `WorkingStackManager`.

---

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a>

Unless otherwise noted, the content of this repository is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>. 

Copyright Martin P. Robillard 2017