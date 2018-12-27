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
1. Since version 5.7.0, `.classpath` file in a Katalon Studio project contains the following line:
```
<classpathentry kind="src" output="bin/groovy" path="Include/scripts/groovy"/>
```
This line defines a build path acknowledged Katalon Studio(=Eclipse). All ` *.groovy` files located in this folder will be compiled (when saved) by the Groovy compiler in Katalon Studio.
2. we will locate unit-testing classes in the `Include/scripts/groovy` folder.
3. Open the project with Eclipse and run JUnit in Eclipse. Eclipse will look after activating the test runner and reporting the test results.

The following sections describe my second attempt.

## How to execute my custom keyword

## How to run unit tests for my custom keyword
