import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import org.junit.runner.JUnitCore
import org.junit.runner.Result

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling

// force compile Keywords
WebUI.callTestCase(findTestCase('test/junit.tutorial/Prologue'), [:])

// run AllTests
Result result = JUnitCore.runClasses(junit.tutorial.AllTests.class)

// print test result
WebUI.callTestCase(findTestCase('test/junit.tutorial/Epilogue'), ['result':result])
