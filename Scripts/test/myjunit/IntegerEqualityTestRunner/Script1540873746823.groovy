import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import java.nio.file.Path
import java.nio.file.Paths

import org.junit.runner.Result

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import myjunit.IntegerEqualityTest
import myjunit.MyJUnitTestRunner

Path outdir = Paths.get(RunConfiguration.getProjectDir()).resolve("tmp")

MyJUnitTestRunner runner = new MyJUnitTestRunner(outdir)
runner.addTestToRun(IntegerEqualityTest.class)
Result result = runner.runTest()

// print test result
WebUI.callTestCase(findTestCase('test/Epilogue'), ['result':result],
	FailureHandling.CONTINUE_ON_FAILURE)

WebUI.comment(">>> JUnit Report is avaliable at ${outdir.toString()}")