Running JUnit in Katalon Studio
====

by kazurayam

## What is this?

This is a simple [Katalon Studio](https://www.katalon.com/) project for demonstration purpose. You can check this out onto your PC and execute with your Katalon Studio. This demo was developed using Katalong Studio version 5.4.2

This demo will show you *how to run unit-tests with JUnit4 in Katalon Studio*.

Are you delighted to here it? Then go on reading this. If you don't know what JUnit is, quit reading this. It's a waste of time for non programmers.

## Background

I am going to develop a custom Keyword in a Katalon Studio project. See [a post in the Katalon forum](https://forum.katalon.com/discussion/comment/19738) for the background story. My custom keyword is expected to be fairly large, complicated, and therefore bug-prone. I want to execute thorough unit-testing on my Groovy classes and methods with [JUnit4](https://junit.org/junit4/) in Katalon Studio.

I was aware that the Katalon Studio's distributed zip file contains `plugins/org.junit_4.12.0.jar`. I tried a test case of single line:
```
import org.junit.runner.JUnitCore
```
This worked! The import statement succeeded. No error was emitted. I realized that the JUnit classes are available in the Katalon Studio's Java VM.

OK, all I need to know is where to locate test classes, how to activate the runner, and how to report the result. I have done an experiment. I believe I have got a success.

## How to run the demo

1. `git clone` this project on your PC
2. Open the project with your Katalon Studio (>ver5.4)
3. You will see the following project structure: ![TestsExplorer](https://github.com/kazurayam/RunningJUnitInKatalonStudio/blob/master/docs/TestsExplorer.PNG)
4. Try to load `Test Cases/test/junittutorial.test/CalculatorTestRunner`
5. Run the test case specifying any Browser (it won't open browser anyway)
6. `CalculateTestRunner` activates the JUnit test runner. It executes junit test cases stored in the `Keywords/junittutorial/test` directory. These junit test cases verify against the Groovy classes under the `Keywords/junittutorial` directory.
7. `CalculateTestRunner` will succeed. In the Console you will find message from `CalculateTestRunner` as follows:
```
07-27-2018 02:42:13 PM - [INFO]   - Run:1, Failure:0, Ignored:0
```
8. Let's try another test case `Test Cases/test/junittutorial.test/AllTestsRunner`. Load and run it.
9. This will fail. In the Console you will find message from `AllTestsRunner` as follows:
```
07-27-2018 02:46:45 PM - [INFO]   - Run:2, Failure:1, Ignored:0
...
07-27-2018 02:46:45 PM - [FAILED] - Test Cases/test/junittutorial.test/AllTestsRunner FAILED because (of) 1 test failed
```

## Design

Read the source codes, please. A real Java/Groovy programmer will find everything needed to run JUnit in Katalon Studio there.

## Outstanding issues

### Reporting
By the following code I could get the test result.
```
Result result = JUnitCore.runClasses(CalculatorTest.class)
```
I had to write a code to report the `result` in Katalon way. Therefore I wrote:
```
WebUI.callTestCase(findTestCase('test/junittutorial.test/Epilogue'), ['result':result])
```
The `Epilogue` is poor and still to be developed. All it does is to print `Run:1, Failure:0, Ignored:0` to console and mark Failure to inform Katalon Studio.

Eclipse surely equips feature to render the JUnit result in GUI. But I would not be able to make use of it.

### I need multiple 'src' directories

In a Java project in Eclipse, I can make 2 or more directories to store program sources codes. For example, one called '`src/main`', another called '`src/test`'. In the `src/main` directory, I will have `junittutorial` package for application classes. In the '`src/test`' directory, I will have the same package `junittutorial` for test classes. With this file tree I can put the application classes and the test classes in a single Java `package`. Simple package namespace is always prefered.

However in Katalon Studio, the `Keywords` directory is the only 1 directory where I could put Groovy source codes. I had to put both of application and test classes together in `Keywords`. Having these two kinds of classes together in a physical directory is very messy. Therefore I had to make another package `junittutorial.test`. I don't like this, but I had to.

### Force compiling Groovy scripts in `Keywords` directory

I am not sure when and how the Groovy scripts in the `Keywords` directory are compiled into \*.class files by Katalon Studio. It seemed that a script is compiled when I saved the source by editor. But I have ever encountered a case where Keywords are not compiled even when I edit and saved it.

It seemd that a Keyword source will be dynamically compiled and loaded via [`CustomKeywords`](https://docs.katalon.com/display/KD/Custom+Keywords). The documentation is very poor. I am not quite sure. But I can guess by practical experiments. So I made a code to ensure dynamic compilation. 
[`Test Cases/test/Prologue`](https://github.com/kazurayam/RunningJUnitInKatalonStudio/blob/master/Scripts/test/Prologue/Script1532666027229.groovy) ensures the Groovy scripts in the `Keywords` directory compiled by Groovy compiler before running tests.
