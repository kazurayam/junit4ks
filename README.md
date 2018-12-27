Running JUnit in Katalon Studio
====

by kazurayam

## What is this?

This is a simple [Katalon Studio](https://www.katalon.com/) project for demonstration purpose. You can download the zip file from [Releases](https://github.com/kazurayam/RunningJUnitInKatalonStudio/releases) page, unzip it, open it with your Katalon Studio.

The first version was developed in April 2018 using Katalong Studio version 5.4.2, and later rewritten in December 2018 using version 5.10.0.

This demo will show you how to run unit-tests using JUnit4 in Katalon Studio.

Are you delighted to hear it? Then go on reading this. If you are non-programmer, don't know what JUnit is, quit reading this.

## Background

I wanted to develop a set of custom Keywords in a Katalon Studio project. See [a post in the Katalon forum](https://forum.katalon.com/discussion/comment/19738) for the background story. My custom keywords were expected to be large and complexed, therefore bug-prone. I wanted to execute thorough unit-testing on my Groovy classes using [JUnit4](https://junit.org/junit4/) framework in Katalon Studio.

One day I created a test case of single line:
```
import org.junit.runner.JUnitCore
```
This worked! The import statement succeeded. No error was emitted. I realized that the JUnit classes are available in the Katalon Studio's Java VM. Later I found that the Katalon Studio's distribution contains `plugins/org.junit_4.12.0.jar`.

## My first attempt

OK, all I need to know is where to locate test classes, how to activate the runner, and how to report the result. I have done an experiment. I believe I have got a success. Tag [0.2](https://github.com/kazurayam/RunningJUnitInKatalonStudio/tree/0.2) of this repository contains this version of my attempt. This version was created at April 2018.

The first attempt worked, but was too complicated. I wanted to find out an easier way to do unit-testing for my Custom Keywords.

## My second attempt

Dec 2018, devalex88 (Katalon Developer) proposed a new approach at https://forum.katalon.com/t/how-to-write-katalon-studio-tests-with-intellij-idea-and-other-ides/15940 .

The idea includes:
1. Since version 5.7.0, `.classpath` file in a Katalon Studio project contains a line `<classpathentry kind="src" output="bin/groovy" path="Include/scripts/groovy"/>`. This line defines a build path acknowledged Katalon Studio(=Eclipse). All of `*.groovy` files located in this folder will be compiled (when saved) by the Groovy compiler in Katalon Studio.
2. We will locate `*Test.groovy` files for unit-testing in the `Include/scripts/groovy` folder.
3. We will use Eclipse to run JUnit. You can open a katalon project with Eclipse (not Katalon Studio)! The built-in feature of Eclipse will look after activating the JUnit tests and reporting the results.

The following sections describe my second attempt.

## How to execute my custom keyword

### Prerequisites

1. Use Katalon Studio version 5.7.0 or higher
2. You have [Eclipse IDE](https://www.eclipse.org/downloads/) installed
3. Download the zip file of this project at [Releases](https://github.com/kazurayam/RunningJUnitInKatalonStudio/releases) page and unzip it

### Custom Keyword

I have made a custom keyword [`junittutorial.Calculator`](Keywords/junittutorial/Calculator.groovy)

### Test Case

I have made a Test Case [`TC1`](Scripts/TC1/Script1545891324676.groovy)

### Executing the test Case

When I execute the TC1, I got the following messages in the console:
```
...
2 * 3 makes 6
...
6 / 3 makes 2
...
```
The class `junittutorial.Calulator` is just working.

## How to run unit tests for my custom keyword

### JUnit Test classes

I have made 2 Groovy files as JUnit test:
- [Include/scripts/groovy/junittutorial/CalculatorTest.groovy](Include/scripts/groovy/junittutorial/CalculatorTest.groovy)
- [Include/scripts/groovy/junittutorial/MoreCalculatorTest.groovy](Include/scripts/groovy/junittutorial/MoreCalculatorTest.groovy)

`CalculatorTest.groovy` looks like this:
```
package junittutorial

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test

import junittutorial.Calculator

class CalculatorTest {

	@Test
	void testMultiply() {
		println ">>> Hello from CalculatorTest#testMultiply()"
		Calculator calc = new Calculator()
		int expected = 21
		int actual = calc.multiply(7, 3)
		assertThat(actual, is(expected))
	}
}
```

It is a typical, simple JUnit test case.

### Runing junit tests in Eclipse
