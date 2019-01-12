Running JUnit in Katalon Studio
====

by kazurayam
- 1st release at 18 July 2018
- 2nd release at 13 January 2019

## What is this?

This is a simple [Katalon Studio](https://www.katalon.com/) project for demonstration purpose. You can download the zip file from [Releases](https://github.com/kazurayam/RunningJUnitInKatalonStudio/releases) page, unzip it, open it with your Katalon Studio.

The first version was developed in April 2018 using Katalong Studio version 5.4.2, and later rewritten in January 2019 using version 5.10.1.

This demo will show you how to run JUnit4-based unit tests to verify your Custom Keywords within Katalon Studio.

The 2nd release provides a new Custom Keyword class  `com.kazurayam.ksbackyard.junit.JUnitCustomKeywords` with a method `runWithJUnitRunner`. Your Test Case can call this method as a usual custom keyword and execute your JUnit-based tests within Katalon Studio.

## Background

I wanted to develop a set of Custom Keywords in a Katalon Studio project. See a post in the Katalon Forum: [Taking entire page screenshot using AShot in Katalon Studio](https://forum.katalon.com/t/taking-entire-page-screenshot-using-ashot-in-katalon-studio/12429) for the background story. My custom keywords were expected to be large and complexed, therefore bug-prone. I wanted to execute thorough unit-testing on my Groovy classes using [JUnit4](https://junit.org/junit4/) framework.

One day in April 2018 I created a test case of single line:
```
import org.junit.runner.JUnitCore
```
This worked! The import statement succeeded. No error was emitted. I realized that the JUnit classes are available in the Katalon Studio's Java VM. Later I found that the Katalon Studio's distribution contains `plugins/org.junit_4.12.0.jar`.

### My first attempt

OK, all I need to know is where to locate test classes, how to activate the runner, and how to report the result. I have done an experiment. I believe I have got a success. Tag [0.2](https://github.com/kazurayam/RunningJUnitInKatalonStudio/tree/0.2) of this repository contains this version of my attempt. This version was created at April 2018.

The first attempt worked, but I thought the method was too lenghthy and complicated. I wanted to find out an easier one.

### My second attempt

Dec 2018, devalex88 (Katalon Developer) proposed a new approach at https://forum.katalon.com/t/how-to-write-katalon-studio-tests-with-intellij-idea-and-other-ides/15940 .

The idea includes:
1. Since version 5.7.0, `.classpath` file in a Katalon Studio project contains a line `<classpathentry kind="src" output="bin/groovy" path="Include/scripts/groovy"/>`. This line defines a build path acknowledged Katalon Studio(=Eclipse). All of `*.groovy` files located in this folder will be compiled (when saved) by the Groovy compiler in Katalon Studio.
2. It is OK for us to locate `*Test.groovy` files for unit-testing in the `Include/scripts/groovy` folder.
3. We can use Eclipse to run JUnit. We can open a katalon project with Eclipse (not Katalon Studio)! The built-in feature of Eclipse will look after activating the JUnit tests and reporting the results.

However I gave up this approach, because I foud I could not test my custom keyword, within Eclipse, which interacts with web sites via WebDriver. See [this post](https://forum.katalon.com/t/how-to-write-katalon-studio-tests-with-intellij-idea-and-other-ides/15940/27) for detail.

### My third attempt

In December 2018, I thought that I could test my Custom Keywords using the BDD feature with Cucumber in Katalon Studio. I learned the following manual pages.
- [Cucumber Features file](https://docs.katalon.com/katalon-studio/docs/cucumber-features-file.html)
- [Step Definitions](https://docs.katalon.com/katalon-studio/docs/step-definitions.html)
- [Running Cucumber Features file](https://docs.katalon.com/katalon-studio/docs/running-cucumber-features-file.html)

It seemed that I could achieve what I wanted with it, but unfortunately I was blocked by a small defect in Katalon Studio:
- [The Cucumber version bundled in Katalon Studio 5.10.1 is old, therefore sometimes throws exception](https://forum.katalon.com/t/poor-error-diagnostics-when-cucumber-feature-is-problematic/17474/8)

I could not rely on Cucumber in Katalon Studio.

### My fourth attempt

In January 2019 I read the source of [`com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords`](https://github.com/katalon-studio/katalon-studio-testing-framework/blob/master/Include/scripts/groovy/com/kms/katalon/core/cucumber/keyword/CucumberBuiltinKeywords.groovy) which as disclosed on GitHub. `CucumberBuiltinKeywords` is the core part of Cucumber-Katalon integration. I learned it and found that **I could mimic that to implement JUnit-Katalon integration**. So I made a new custom keyword class  [`com.kazurayam.ksbackyard.junit.JUnitCustomKeywords`](Keywords/com/kazurayam/ksbackyard/junit/JUnitCustomKeywords.groovy). This looks working fine.

A long and winding road it was. I believe I have found out a satisfactory method to test my custom keywords with JUnit4 within Katalon Studio.

## Description of the demo (2nd release)

### Prerequisites

1. Use Katalon Studio version 5.7.0 or higher. I used v5.10.1.
2. Download the zip file of this project tagged with 1.0 at [Releases](https://github.com/kazurayam/RunningJUnitInKatalonStudio/releases) page and unzip it.

### Custom Keywords to test

I have made some custom keyword classes. I want to test them using JUnit4:
- [`junittutorial.Calculator`](Keywords/junittutorial/Calculator.groovy) --- calculator which add/subtract/multiply/divide 2 integers
- [`junittutorial.Greeter`](Keywords/junittutorial/Greeter.groovy) --- this will say hello to someone you sepecified
- [`com.example.MiniScreenshotDriver`](Keywords/com/example/MiniScreenshotDriver.groovy) --- taks entire-page screenshot of a URL and save PNG into file

### Test Case

I have made a few Test Cases in the ordinary `Test Cases` folder. These test cases run JUnit4 in Katalon Studio.
- [`CalculatorTest`](Scripts/test/CalculatorTestRunner/Script1547192368406.groovy)
- [`GreeterTestRunner`](Scripts/test/GreeterTestRunner/Script1547296768493.groovy)
- [`AllCalculatorTestRunner`](Scripts/test/AllCalculatorTestRunner/Script1547284601981.groovy)
- [`MiniScreenshotDriverTestRunner`](Scripts/test/MiniScreenshotDriverTestRunner/Script1547301583006.groovy)

For example, MiniScreenshotDriverTestRunner looks like this:
```
import com.example.MiniScreenshotDriverTest

CustomKeywords.'com.kazurayam.ksbackyard.junit.JUnitCustomKeywords.runWithJUnitRunner'(MiniScreenshotDriverTest.class)
```

It's very short, no mystery here.


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

## How to apply this method to your projects
