# Module 0 - Preparation

## Description

How does one approach a software development project? Start hacking and hope for the best? Probably not. This would just be [inviting disaster](http://spectrum.ieee.org/static/the-staggering-impact-of-it-systems-gone-wrong). In this pseudo-module, I will present an overview of the important steps we need to take to prepare to develop quality software, from understanding the problem to mapping out a development process to choosing and setting up our tools to sketching an application.

## Notes

Before jumping into software design, it's important to understand what software design is, and the place it occupies in the more general context of software development.

There are many definitions of software design, each with a different focus. For example the [Wikipedia](https://en.wikipedia.org/wiki/Software_design) definition (as of 3 Jan 2017), is rather comprehensive, covering everything from requirements and problem domain analysis to aspects of the user experience. I personally prefer the definition offered by the book [Software Designers in Action](http://dl.acm.org/citation.cfm?id=2535028) because it emphasizes the close relation between design and code, which is one of the tenets of this course.

> In its basic form, software design concerns determining the abstraction structure of the intended software. However, the abstraction structures in software design tend to be deeper than those in other disciplines, whether an expressive domain such as dance, or an engineering domain such as electronics. The problem is that the *behavior* of the code must be designed as much, if not more so, than the structure of the code in terms of its overall components and connectors, or classes and associations. As a result, the final design of a program is its source code, as even just minor variations in the code can radically alter its behavior. Because of this, the distinction between design and manufacture is blurred, make software design a highly interwoven process, with design and implementation in a continuous and close relationship - van der Hoek and Petre 2014

As for the question of where software design fits in, the important thing to know is that design is only one of the many activities that take place during the development of a software system. There is an abundant literature on different [process models](https://en.wikipedia.org/wiki/Software_development_process) for software development. Basically a process model describes (and sometimes prescribes) how the different "steps" required to create a system are organized, and different process models offer different ways of doing things for different reasons. In the early days of the software engineering discipline it was believed that a "planning-heavy" process, exemplified by the [Waterfall model](https://en.wikipedia.org/wiki/Software_development_process#Waterfall_development), was the desirable way to build high-quality software. However, in the mid-1990s this belief was challenged by a movement towards a more self-organizing approach to software development, also called [agile development](https://en.wikipedia.org/wiki/Agile_software_development). In practice ideas about how to best develop software keep evolving, and in the end the important things are to have a process in the first place, and for that process to be well-adapted to the type of systems being developed and the organization that develops it, although that in itself is not so easy to achieve or even evaluate. 

Acquiring experience with a software development process is not the main focus of COMP 303, but rather that of COMP 361. However, general knowledge on software processes is good to have to be oriented in the general and confusing realm of software development. One concept of the software development process literature that is however relevant in COMP 303 is the idea of a *software development practice*. In the context of software development, a practice is a well-understood way of doing something to achieve a certain software development benefit. A practice you already know is "version control". You may also have heard of ["pair programming"](https://en.wikipedia.org/wiki/Pair_programming). In the course I will introduce and refer to a number of software development practices that directly support good design, including [Refactoring](https://en.wikipedia.org/wiki/Code_refactoring) and the use of [coding conventions](https://en.wikipedia.org/wiki/Coding_conventions). The following [subway map of agile practices](https://www.agilealliance.org/agile101/subway-map-to-agile-practices/) offers a neat visualizations of a sample of software development practices.

## Reading

* Articles referred to in the notes

## Exercises

The goal for this module is to set up your development environment and checking that everything works. 

0. Ensure that you have a working version of [Eclipse Java Neon](http://www.eclipse.org/) and [Java 8](https://www.java.com/en/)
0. Install the Checkstyle Eclipse Plug-in. In Eclipse, select `Help | Eclipse Marketplace...` and enter `Checkstyle` in the `Find:` box. From the results, install `Checkstyle Plug-In...`.
0. Similar as the above, install the EclEmma Eclipse-Plug-In.
0. Check-out the [COMP303Starter](https://github.com/prmr/COMP303Starter) project into you workspace. In the Eclipse package explorer, right-click and select `Import... | Git -> Projects from Git | Clone URI` and under URI enter `https://github.com/prmr/COMP303Starter`, then click-through, requesting to import existing Eclipse projects. Once imported, the project should compile without errors.
0. Download [JetUML](http://cs.mcgill.ca/~martin/jetuml/) and run it. Draw a simple diagram, save it, then load it. Alternatively, you can check-out the project code, as above, and run the tool from your workspace.

To ensure that everything works:

0. Right-click on the file `Welcome.java` and select `Run As... | Java Application`. You should see a small GUI application appear. Try the different buttons which should do the obvious thing.
0. Right-click on the file `TestAlternatingLabelProvider.java` and select `Run As... | JUnit Test`. A green bar should appear.
0. Right-click again on the file `TestAlternatingLabelProvider.java` and select `Coverage As... | JUnit Test`. A green bar should appear, along with a view showing coverage information. If you go back to the source code file, most lines should be highlighted in green. The meaning of this will be explained in class in a later module.
0. Access the "Problems" view in Eclipse. You should see two warnings of the type "Checkstyle Problem".

Once everything works as described above, try the following:

0. Fix the code to make the Checkstyle warnings go away. To see the full list of coding rules checked by the tool, right-click on the project and select `Properties | Checkstyle | Configure` and play around with the viewer.
0. Change line 37 of file `AlternatingLabelProvider.java` to return `aLabel1` instead of `aLabel2`, and re-run the test. The test should fail.
0. Learn some [seriously useful shortcuts](http://www.vogella.com/tutorials/EclipseShortcuts/article.html). My personal favorites are: `CTRL-SHIFT-R`, `CTRL-SPACE`, `CTRL-O`, `ALT-SHIFT-R`.

---

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a>

Unless otherwise noted, the content of this repository is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>. 

Copyright Martin P. Robillard 2017