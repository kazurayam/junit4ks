Running JUnit in Katalon Studio
====

by kazurayam

## What is this?

This is a simple [Katalon Studio](https://www.katalon.com/) project for demonstration purpose. You can check this out onto your PC and execute with your Katalon Studio. This demo was developed using Katalong Studio version 5.4.2

This demo will show you: *you can run unit-tests with JUnit4 in Katalon Studio*.

Are you delighted to here it? Then go on reading this. If you don't know what JUnit is, quit reading this. It's a waste of time for non programmers.

## Background

I am going to develop a custom Keyword in my Katalon Studio project. See [a post in the Katalon forum](https://forum.katalon.com/discussion/comment/19738) for the background story. My custom keyword is expected to be large, compilcated, and therefore bug-prone. So I want to execute thorough unit-testing on custom keyword with [JUnit4](https://junit.org/junit4/) in Katalon Studio.

I was aware that the Katalon Studio's distribution zip file contains `plugins/org.junit_4.12.0.jar`. I made a test case of one line:
```
import org.junit.runner.JUnitCore
```
This worked. I mean, import succeeded, no error emitted. I realized that the JUnit classes are available in the Katalon Studio's Java VM.

OK, all I need to is to find out where to locate test classes, how to activate JUnit test runner, and how to report the result. I have done an experiment, and succeeded.

## How to run the demo
