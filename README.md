Running JUnit in Katalon Studio
====

by kazurayam

## What is this?

This is a simple [Katalon Studio](https://www.katalon.com/) project for demonstration purpose. You can check this out onto your PC and execute with your Katalon Studio. This demo was developed using Katalong Studio version 5.4.2

This demo will show you: *you can run unit-tests with JUnit4 in Katalon Studio*.

Are you delighted to here it? Then go on reading this. If you don't know what JUnit is, quit reading this. It's a waste of time for non programmers.

## Background

I am going to develop a custom Keyword in a Katalon Studio project. See [a post in the Katalon forum](https://forum.katalon.com/discussion/comment/19738) for the background story. My custom keyword is expected to be fairly large, complicated, and therefore bug-prone. So I want to execute thorough unit-testing on my Groovy classes and methods with [JUnit4](https://junit.org/junit4/) in Katalon Studio.

I was aware that the Katalon Studio's distribution zip file contains `plugins/org.junit_4.12.0.jar`. I tried a test case of single line:
```
import org.junit.runner.JUnitCore
```
This worked! I mean, import succeeded, no error emitted. I realized that the JUnit classes are available in the Katalon Studio's Java VM.

OK, all I need to is to find out where to locate test classes, how to activate the runner, and how to report the result. I have done an experiment. I believe I have got a success.

## How to run the demo

1. `git clone` this project on your PC
2. Open the project with your Katalon Studio (>ver5.4)
3. You will see the following project structure: ![TestsExplorer](https://github.com/kazurayam/RunningJUnitInKatalonStudio/blob/master/docs/TestsExplorer.PNG)
4. Try to load `Test Cases/test/junittutorial.test/CalculatorTestRunner`
5. Run the test case specifying any Browser (it won't open browser anyway)
6. This will succeed. In the Console you will find message from `CalculateTestRunner` as follows:
```
07-27-2018 02:42:13 PM - [INFO]   - Run:1, Failure:0, Ignored:0
```
7. Let's try another test case `Test Cases/test/junittutorial.test/AllTestsRunner`. Load and run it.
8. This will fail. In the Console you will find message from `AllTestsRunner` as follows:
```
07-27-2018 02:46:45 PM - [INFO]   - Run:2, Failure:1, Ignored:0
...
07-27-2018 02:46:45 PM - [FAILED] - Test Cases/test/junittutorial.test/AllTestsRunner FAILED because (of) 1 test failed
```

## Design

Read the source codes, please. You will find everything you need to know there.

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

### I need multiple 'src' directory

In a Java project in Eclipse, you can make 2 or more directories to store program sources codes: one called '`main`', another called '`test`' for example. In the `src/main` directory I will have `junittutorial` package for application classes. In the '`src/test`' directory I will have the same package `junittutorial` for test classes. With this file tree I can put allication classes and test classes in a single package. Simple namespace is always prefered.

However in Katalon Studio, the `Keywords` directory is the only src directory where I could put Groovy source codes. I had to put both of application and test classes together in `Keywords`. Therefore I had to bring the test classes into another package `junittutorial.test`. Needless complication of namespace looks dirty to me.

### Force compiling Groovy scripts in `Keywords` directory

I am not yet sure when and how the Groovy scripts in the `Keywords` directory compiled into \*.class files. It seemed that a script is compiled when I saved the source by editor. But I have ever encountered a case where Keywords are not compiled into \*.class file, and got strange errors.

[`Test Cases/test/junittutorial.test/Prologue`](https://github.com/kazurayam/RunningJUnitInKatalonStudio/blob/master/Scripts/test/junittutorial.test/Prologue/Script1532666027229.groovy) ensures the Groovy scripts in the `Keywords` directory compiled by Groovy compiler before running tests.
