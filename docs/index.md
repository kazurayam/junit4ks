# Running JUnit 4 in Katalon Studio

by kazurayam

- 1st release at 18 July 2018

- 2nd release at 13 January 2019

- 3rd release at 10 Feb 2019

- 4th release at 20 Feb 2019

- 5th release v1.6.2 at 21 Feb 2024

- 6th release v1.6.3 at July 2025

- 7th update at April 2026

## Problem to solve

I wanted to develop a set of Custom Keywords in a Katalon Studio project. For the background story, see a post in the Katalon Forum: [Taking entire page screenshot using AShot in Katalon Studio](https://forum.katalon.com/t/taking-entire-page-screenshot-using-ashot-in-katalon-studio/12429). My custom keywords were expected to be large and complexed, therefore bug-prone. I wanted to do unit-testing thoroughly using [JUnit 4](https://junit.org/junit4/).

## Solution

Unfortunately, Katalon Studio does not offer a toolset to do unit-testing by JUnit for custom Keywords. Therefore I invent a framework for myself.

## How to inject the Junit4ks files into your own Katlaon project

1.  Here I assume you have a Katalon Studio project adlready created. Any project will do.

2.  Visit the [Releases v1.6.3](https://github.com/kazurayam/junit4ks/releases/tag/1.6.3) page. Locate the link `injectJunit4ks-build.gradle`. Download the file into the project’s root directory.

3.  You need to renamed the `injectJunit4ks-build.gradle` file to `build.gradle`.

4.  In a Terminal window, you want to do the following operation in the command line:

<!-- -->

    $ cd <yourProjecDir>
    $ gradle injectJunit4ks

1.  The `injectJunit4ks` task will unzip the archive and copy the `Keywords` and others into your own Katalon Studio project.

2.  Once the zip’s contet files are extracted and injected into your project, the `build.gradle` is no longer needed. You should remove the `build.gradle` file.

## What’s in the zip file

In your own Katalon Studio project directory, you would find a new file created at:

    $ pwd
    $ <yourProjectDir>
    $ tree build
    build
    ├── dist-downloaded
    │   └── junit4ks-distribution-1.6.3.zip

The zip file contains the following contents:

    $ tree .
    .
    ├── Include
    │   └── scripts
    │       └── groovy
    │           └── junittutorial
    │               ├── CalculatorFailingTest.groovy
    │               ├── CalculatorTest.groovy
    │               └── CalculatorWithIgnoreRestTest.groovy
    ├── Keywords
    │   ├── com
    │   │   └── kazurayam
    │   │       └── junit4ks
    │   │           ├── IgnoreRest.groovy
    │   │           ├── IgnoreRestHelper.groovy
    │   │           ├── IgnoreRestRule.groovy
    │   │           ├── IgnoreRestSupportRunner.groovy
    │   │           ├── JUnitCustomKeywords.groovy
    │   │           ├── JUnitRunnerResult.groovy
    │   │           ├── JUnitRunnerResultImpl.groovy
    │   │           ├── LegacyXmlReportGeneratingListener.groovy
    │   │           └── RunListener4ks.groovy
    │   └── junittutorial
    │       └── Calculator.groovy
    ├── Scripts
    │   └── test
    │       └── junittutorial
    │           ├── CalculatorFailingTestRunner
    │           │   └── Script1751710481301.groovy
    │           ├── CalculatorTestRunner
    │           │   └── Script1547192368406.groovy
    │           └── CalculatorTestWithIgnoreRestRunner
    │               └── Script1552166240495.groovy
    ├── Test Cases
    │   └── test
    │       └── junittutorial
    │           ├── CalculatorFailingTestRunner.tc
    │           ├── CalculatorTestRunner.tc
    │           └── CalculatorTestWithIgnoreRestRunner.tc
    └── Test Suites
        └── test
            └── junittutorial
                ├── runAll.groovy
                └── runAll.ts
    ----

The `*.groovy` files under the `Keywords` directory is the implementation of the `junit4ks` library.

The files under the `Test Cases`, `Scripts`, `Test Suite` and `Include` directory are the sample code that utilizes the `junit4ks`.

## How to run the demonstration

Close and reopen your Katalon Studio project in which the `junit4ks` files have been injected.

![project with junit4ks injected](https://kaurayam.github.io/junit4ks/images/project_with_junit4ks_injected.png)

Open the `Test Suites/test/junittutorial/runAll`. Runt it by clicking the gree button ![green button](https://kazurayam.github.io/junit4ks/images/run_katalon_test.png). The Test Suite would pass.

When it finished, you can see the test result output in the Log Viewer and Console in Katalon Studio GUI.
![testcase execution log](https://kazurayam.github.io/junit4ks/images/testcase_execution_log.png)

When any test fails, a Java Stack Trace will be printed into the Log Viewer.

The output into the Log Viewer is the sole output from the junit4ks. It will not generate any beautifully formated reports.

## Source codes

Have a look at the following sample codes.

- [Test Cases/test/junittutorial/CalculratorTestRunner](https://github.com/kazurayam/junit4ks/tree/develop/Scripts/test/junittutorial/CalculatorTestRunner/Script1547192368406.groovy)

- [Include/scripts/groovy/junittutorial/CalculatorTest.groovy](https://github.com/kazurayam/junit4ks/tree/develop/Include/scripts/groovy/junittutorial/CalculatorTest.groovy)

- [Keywords/junittutorial/Calculator.groovy](https://github.com/kazurayam/junit4ks/tree/develop/Keywords/junittutorial/Calculator.groovy)

The `CalculatorTest.groovy` is a unit-test for the custom keyword `Calcurator.groovy`. You can run the unit-test by running the `CalcluratorTestRunner` with by clicking the gree button ![green button](https://kazurayam.github.io/junit4ks/images/run_katalon_test.png).

## API doc

The groovydoc of junit4ks is \[here\](<https://kazurayam.github.io/junit4ks/api/index.html>)

## Hitory

### I found JUnit4 was bundled in Katalon Studio

One day in April 2018 I created a test case of single line:

    import org.junit.runner.JUnitCore

This worked! The import statement succeeded. No error was emitted. I realized that the JUnit classes are available in the Katalon Studio’s Java VM. Later I found that the Katalon Studio’s distribution contains `plugins/org.junit_4.12.0.jar`.

### My first attempt

OK, all I need to know is where to locate test classes, how to activate the runner, and how to report the result. I have done an experiment. I believe I have got a success. Tag \[0.2\](<https://github.com/kazurayam/RunningJUnitInKatalonStudio/tree/0.2>) of this repository contains this version of my attempt. This version was created at April 2018.

The first attempt worked, but I thought the method was too lenghthy and complicated. I wanted to find out an easier one.

### My second attempt

Dec 2018, devalex88 (Katalon Developer) proposed a new approach at <https://forum.katalon.com/t/how-to-write-katalon-studio-tests-with-intellij-idea-and-other-ides/15940> .

The idea includes:
1. Since version 5.7.0, `.classpath` file in a Katalon Studio project contains a line `<classpathentry kind="src" output="bin/groovy" path="Include/scripts/groovy"/>`. This line defines a build path acknowledged Katalon Studio(=Eclipse). All of `*.groovy` files located in this folder will be compiled (when saved) by the Groovy compiler in Katalon Studio.
2. It is OK for us to locate `*Test.groovy` files for unit-testing in the `Include/scripts/groovy` folder.
3. We can use Eclipse to run JUnit. We can open a katalon project with Eclipse (not Katalon Studio)! The built-in feature of Eclipse will look after activating the JUnit tests and reporting the results.

However I gave up this approach, because I could not test my custom keyword, within Eclipse, which interacts with web sites via WebDriver. See \[this post\](<https://forum.katalon.com/t/how-to-write-katalon-studio-tests-with-intellij-idea-and-other-ides/15940/27>) for detail.

### My third attempt

In December 2018, I thought that I could test my Custom Keywords using the BDD feature with Cucumber in Katalon Studio. I learned the following manual pages.
- \[Cucumber Features file\](<https://docs.katalon.com/katalon-studio/docs/cucumber-features-file.html>)
- \[Step Definitions\](<https://docs.katalon.com/katalon-studio/docs/step-definitions.html>)
- \[Running Cucumber Features file\](<https://docs.katalon.com/katalon-studio/docs/running-cucumber-features-file.html>)

It seemed OK. I could nearly achieve what I wanted to do. But unfortunately I was blocked by a defect in Katalon Studio:
- \[The Cucumber version bundled in Katalon Studio 5.10.1 is old, therefore sometimes throws exception\](<https://forum.katalon.com/t/poor-error-diagnostics-when-cucumber-feature-is-problematic/17474/8>)

I could not rely on Cucumber in Katalon Studio.

### My fourth attempt

In January 2019 I read the source of \[`com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords`\](<https://github.com/katalon-studio/katalon-studio-testing-framework/blob/master/Include/scripts/groovy/com/kms/katalon/core/cucumber/keyword/CucumberBuiltinKeywords.groovy>) on GitHub. `CucumberBuiltinKeywords` is the core part of Cucumber-Katalon integration. I studied it to find that **I could mimic that to implement JUnit-Katalon integration**. So I made a new custom keyword class \[`com.kazurayam.ksbackyard.junit.JUnitCustomKeywords`\](Keywords/com/kazurayam/ksbackyard/junit/JUnitCustomKeywords.groovy). This looks working fine.

A long and winding road it was. I believe I have found out a satisfactory method to test my custom keywords with JUnit4 within Katalon Studio. I publish it as the 2nd release of \[my demo project\](<https://github.com/kazurayam/RunningJUnitInKatalonStudio/>).

Later, 10 Feb 2019, I changed the package name from `com.kazurayam.ksbackyard.junit` to `com.kazurayam.junit4ks`, having "JUnit4 for Katalon Studio" in mind, for getting better understood what it is. Also note, @devallex88 made a \[contribution\](<https://github.com/kazurayam/junit4ks/compare/1.4…​1.6>) to this.

### 7th update at April 2026.

Previously, I distributed the jar of `junit4ks` at the Maven Central repository:

- <https://central.sonatype.com/artifact/com.kazurayam/junit4ks>

But I realized that providing only jar is not enough. When I want to apply the `junit4ks` in any Katalon Studio project of my own, I always needed to look back the [project repository](https://github.com/kazurayam/junit4ks/tree/develop) for sample codes. I needed to copy & paste the `Test Cases/test/CalcularatorTestRunner` and the `Include/Include/scripts/groovy/junittutorial/CalculatorTest.groovy` because I always forget how to write the codes that utilizes `junit4ks`. Therefore I thought I need to develop new way of distributing the `junit4ks`.

I extended the [build.gradle](https://github.com/kazurayam/junit4ks/tree/develop/build.gradle) so that it implements a new task `createDist`. In the `junit4ks` project I do this:

    $ cd ~/katalon-workspace/junit4ks
    $ ./gradlew createDist

The `createDist` task will create 2 files in the `build/dist-created` directory.

    $ tree build/dist-created
    build/dist-created
    ├── injectJunit4ks-build.gradle
    └── junit4ks-distribution-1.6.3.zip
    ````

    The `injectJunit4ks=build.gradle` file is meant to be downloaded into a Katalon Studio project of users, renamed to `build.gradle` and executed by local Gradle runtime. It's source is as follows:

    [source,groovy]
    ----
    plugins {
        id "de.undercouch.download" version "5.7.0"
    }
    task injectJunit4ks {
        doLast {
            download.run {
                src "https://github.com/kazurayam/junit4ks/releases/download/1.6.3/junit4ks-distribution-1.6.3.zip"
                dest layout.buildDirectory.dir('dist-downloaded')
                overwrite true
            }
            copy {
                from zipTree(layout.buildDirectory.file("dist-downloaded/junit4ks-distribution-1.6.3.zip"))
                into layout.projectDirectory.dir('.')
            }
        }
    }
    ----

    It just download a zip file from the project's Releases page, unzip it and copy the source codes into the current directory = Katalon Studio project.

    This way, I could simply the procedure to use the `junit4ks` in other projects. I am contented with it.
