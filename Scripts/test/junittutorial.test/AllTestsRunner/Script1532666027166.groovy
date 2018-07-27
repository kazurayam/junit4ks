import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import org.junit.runner.JUnitCore
import org.junit.runner.Result

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling

import junittutorial.test.AllTests

// force compile Keywords
WebUI.callTestCase(findTestCase('test/Prologue'), [:],
	FailureHandling.CONTINUE_ON_FAILURE)

// run AllTests
Result result = JUnitCore.runClasses(AllTests.class)

// print test result
WebUI.callTestCase(findTestCase('test/Epilogue'), ['result':result],
	FailureHandling.CONTINUE_ON_FAILURE)
