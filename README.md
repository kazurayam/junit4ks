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

1. git clone this project on your PC
2. open the project with your Katalon Studio (>ver5.4)
3. You will see the following project structure:
