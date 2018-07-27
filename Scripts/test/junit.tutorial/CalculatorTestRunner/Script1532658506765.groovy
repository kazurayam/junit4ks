import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import org.junit.runner.JUnitCore
import org.junit.runner.Result

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import junit.tutorial.CalculatorTest

// force compile Keywords
WebUI.callTestCase(findTestCase('test/junit.tutorial/Prologue'), [:])

// run CalculatorTest
Result result = JUnitCore.runClasses(junit.tutorial.CalculatorTest.class)

// print test result
WebUI.callTestCase(findTestCase('test/junit.tutorial/Epilogue'), ['result':result])
