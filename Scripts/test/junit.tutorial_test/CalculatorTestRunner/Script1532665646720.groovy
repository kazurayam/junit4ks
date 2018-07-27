import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import org.junit.runner.JUnitCore
import org.junit.runner.Result

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import junit.tutorial_test.CalculatorTest

// force compile Keywords
WebUI.callTestCase(findTestCase('test/junit.tutorial_test/Prologue'), [:])

// run CalculatorTest
Result result = JUnitCore.runClasses(CalculatorTest.class)

// print test result
WebUI.callTestCase(findTestCase('test/junit.tutorial_test/Epilogue'), ['result':result])
