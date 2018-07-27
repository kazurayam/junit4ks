import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import org.junit.runner.JUnitCore
import org.junit.runner.Result

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import junit.tutorial_test.AllTests

// force compile Keywords
WebUI.callTestCase(findTestCase('test/junit.tutorial_test/Prologue'), [:])

// run AllTests
Result result = JUnitCore.runClasses(AllTests.class)

// print test result
WebUI.callTestCase(findTestCase('test/junit.tutorial_test/Epilogue'), ['result':result])
