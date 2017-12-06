## Module 8 - Design Patterns

## Description

This module will explore design solutions that incorporate design patterns an inheritance and introduce the Visitor design pattern.

## Learning Objectives

After this module you should:

* Be able to use the Visitor Design Pattern effectively;
* Be able to determine when inheritance can be effectively used within different design patterns including the Visitor, Decorator, Composite, Command, and Strategy patterns.
* Be able to correctly instantiate design patterns with inheritance.

## Notes

### Visitor Design Pattern

### Design Pattern Review Scenario

We will explore how to combine design patterns by creating a design for a hypothetical mobile robotics system. In this system, a `Robot` class represents a three-wheeled robot with two active wheels and one free wheel that can also rotate around a pivot. The `Robot` class provides very basic control primitives through a method `void moveLeftWheel(double pRadians)` a similar method for moving the right wheel, and a similar method for moving both wheels in synch. The `pRadians` parameter specifies how much to turn the wheel, e.g., `pRadians=2*PI` turns the wheel a full circle.

![](figures/m08-robot.png)

We want to expand this design to implement the following requirements:

1. It should be possible to define higher-level commands for the robot in terms of the basic primitives. For example it should be possible to define a "Forward Move" command that moves both wheels by the same amount. The number of different commands should be extensible;
2. Commands should be parameterizable, e.g., "move forward 1 meter" vs 2 meters, etc.
3. Any command should be reversible;
4. Commands should have a name that can be discoverable at run time. For example, a "Move forward 1 meter".
5. It should be possible to aggregate commands into more complex "macro commands". For example, a "Back and Forth" command could involve a forward move followed by a backward move.
6. It should be possible for components in the system other than the robot to be notified of commands issued on a `Robot` object. Three components interested in robot commands include a `CommandLogger` that prints all commands with a time stamp, `CommandRecorder` that can be issued a request to record commands (or to stop recording them), and to control the robot to replay the recording; a `RobotConsole` component that visually shows the path of the robot in a graphical user interface.
7. The system should remember the last command issued to a robot, and provide a convenience method `reexecute()` to re-execute this command. The reexecution of the command should be considered a new, separate command.

In addition to these requirements, the final design should exhibit a number of qualities:
* Effective code reuse: the design should limit code duplication;
* Loose coupling: classes should not depend on interfaces they do no need;
* Robustness: The likelihood of `NullPointerExceptions` should be, well, null.


## Reading

* [A webpage on the Visitor design pattern](https://sourcemaking.com/design_patterns/visitor)

## Exercises

1. Add Visitor support to the class hierarchy below by completing the class diagram. Include a `PrintVisitor` as part of the design. Using a sequence diagram, show a scenario of a traversal through a directory object with the print visitor. The `PrintVisitor` prints the name of each file it visits. Implement a mock-up of the design in code, and use the debugger to validate your sequence diagram.

![](figures/m08-FileSystem.png)

2. Extend the file system class hierarchy to include a `HiddenDirectory` class that is a subclass of `Directory`. For the purpose of this exercise, a hidden directory behaves just like a directory, but prints the name as `"." + getName().` Adjust both the diagrams and the code in consequence.

3. Instead of using a subclass for `HiddenDirectory`, implements this feature using the Decorator Design pattern. Adjust both the diagrams and the code in consequence.

4. Implement a `DeleteVisitor` that find a file with a name passed as parameter to the visitor, and delete all its children, if the file is a directory or a symbolic link that refers to a directory. Once this works, try changing the code so that the specified `IFile` also gets deleted. Is this a good idea?

5. Run the [University Demo](artifacts/module-08/). What is the order of traversal implemented in `SearchVisitor`? What happens if two committees with the same parent node have the same name? Use the debugger to confirm your answer.

6. Experiment with different traversal orders for the visitor. 

7. With the University example, implement a `CommitteeDepthVisitor` that can discover the sub-committee depth of a committee that matches an input query. For example, a root committee would have value 0, a sub-committee, 1, and a sub-sub-committee 3. 

8. Solve the review question by producing a class diagram, sequence diagrams to illustrate a command execution, and implement a mock-up of your solution.

---

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a>

Unless otherwise noted, the content of this repository is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>. 

Copyright Martin P. Robillard 2017