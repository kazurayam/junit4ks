package com.kazurayam.junit4ks

import java.text.MessageFormat

import org.junit.runner.Computer
import org.junit.runner.Description
import org.junit.runner.JUnitCore
import org.junit.runner.Result
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunListener

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.constants.StringConstants
import com.kms.katalon.core.keyword.internal.KeywordMain
import com.kms.katalon.core.logging.ErrorCollector
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.internal.ExceptionsUtil

/**
 * A custom keyword in Katalon Studio. This enables you to run JUnit4 to 
 * perform test-driven development for your own custom keywords.
 * 
 * I read and learned form
 * https://github.com/katalon-studio/katalon-studio-testing-framework/blob/master/Include/scripts/groovy/com/kms/katalon/core/cucumber/keyword/CucumberBuiltinKeywords.groovy
 *
 * @author kazurayam
 *
 */
public class JUnitCustomKeywords {

	private static final KeywordLogger logger = KeywordLogger.getInstance(JUnitCustomKeywords.class)

	/**
	 * Run the given <code>junitRunnerClass</code> that is annotated with
	 * {@link JUnit} runner by invoke JUnit runner.
	 *
	 * @param junitRunnerClass a class that is annotated with {@link JUnit} runner.
	 *
	 * <p>
	 * Example of <code>junitRunnerClass</code>:
	 * <ul>
	 * <li>
	 * <pre>
	 * import org.junit.runner.RunWith
	 * import org.junit.runners.JUnit4
	 * &#64;RunWith(JUnit4.class)
	 * public class MyJunitRunner {}
	 *
	 * </pre>
	 * </li>
	 * </ul>
	 * </p>
	 * @return
	 */
	@Keyword
	public static JUnitRunnerResult runWithJUnitRunner(Class junitRunnerClass) {
		assert RunConfiguration.getDefaultFailureHandling() != null
		return runWithJUnitRunner(junitRunnerClass, RunConfiguration.getDefaultFailureHandling())
	}

	/**
	 * Example:
	 *
	 * You can run the following test case `Test Cases/test/CalculatorTestRunner` in Katalon Studio
	 * just as you run a usual test case by clicking the Run button.
	 *
	 * Test Case:
	 * <PRE>
	 * import junittutorial.CalculatorTest
	 * CustomKeywords.'com.kazurayam.ksbackyard.junit.JUnitCustomKeywordsTest.runWithJUnitRunner'(CalculatorTest.class)
	 * </PRE>
	 *
	 * The following is a JUnit test (localated at Include/scripts/groovy/junittutorial/CalculatorTest.groovy)
	 * executed by the above test case:
	 * <PRE>
	 * package junittutorial
	 *
	 * import static org.hamcrest.CoreMatchers.*
	 * import static org.junit.Assert.*
	 *
	 * import org.junit.Test
	 * import org.junit.runner.RunWith
	 * import org.junit.runners.JUnit4
	 *
	 * @RunWith(JUnit4.class)
	 * class CalculatorTest {
	 * 	@Test
	 * 	void testMultiply() {
	 * 		int expected = 21
	 * 		int actual = Calculator.multiply(7, 3)
	 * 		assertThat(actual, is(expected))
	 * 	}
	 *
	 * 	@Test
	 * 	void testDivide_wrongType() {
	 * 		double expected = 1.5f
	 * 		double actual = Calculator.divide(3, 2)
	 * 		assertThat(actual, is(not(expected)))
	 * 	}
	 *
	 * 	@Test
	 * 	void testDivide() {
	 * 		int expected = 1
	 * 		int actual = Calculator.divide(3, 2)
	 * 		assertThat(actual, is(expected))
	 * 	}
	 * }
	 * </PRE>
	 *
	 * Finally the class to be tested is located at `Keywords/junittutorial/Calculator.groovy`:
	 * <PRE>
	 * package junittutorial
	 *
	 * import com.kms.katalon.core.annotation.Keyword
	 *
	 * class Calculator {
	 *
	 * 	   @Keyword
	 * 	   static int add(int a, int b) {
	 * 		   return a + b;
	 * 	   }
	 *
	 * 	   @Keyword
	 * 	   static int subtract(int a, int b) {
	 * 		   return a - b;
	 * 	   }
	 *
	 * 	   @Keyword
	 * 	   static int multiply(int x, int y) {
	 * 		   return x * y
	 * 	   }
	 *
	 * 	   @Keyword
	 * 	   static int divide(int x, int y) {
	 * 		   return x / y
	 * 	   }
	 *
	 * 	   @Keyword
	 * 	   static int power(int a, int b){
	 * 		   int answer =a;
	 * 		   for (int x = 2; x <= b; x++){
	 * 		       answer *= a;
	 * 		   }
	 * 		   return answer;
	 *     }
	 * }
	 * </PRE>
	 *
	 * Please note that in the targeted Custom Keyword (e.g, Keywords/com/example/MiniScreenshotDriver.groovy) and
	 * JUnit test (e.g, Include/scripts/groovy/com/example/MiniScreenshotDriverTest.groovy), you can call
	 * any Katalon Studio API including org.openqa.selenium.WebDriver. Your JUnit invoked within Katalon Studio now
	 * can interact with your Application Under Test (web site) through WebDriver. This is what I wanted to achieve.
	 *
	 * @param junitRunnerClass
	 * @param flowControl
	 * @return
	 */
	@Keyword
	public static JUnitRunnerResult runWithJUnitRunner(Class junitRunnerClass, FailureHandling flowControl) {
		return KeywordMain.runKeyword({

			// JUnitCore is a facade for running tests.
			JUnitCore core = new JUnitCore()

			// set a listener to the core
			core.addListener(new RunListener4KS())

			// A Computer represents a strategy for computing runners and suites.
			Computer computer = new Computer()

			// Run all the tests in class.
			Result result = core.run(computer, junitRunnerClass)

			// wrap up the result as an instance of JUnitRunnerResult
			boolean runSuccess = result.wasSuccessful()
			JUnitRunnerResultImpl junitResult = new JUnitRunnerResultImpl(
					runSuccess ? 'passed' : 'failed', '', result)

			// report the result to console.
			if (runSuccess) {
				// if the test ran success, just say it has passed.
				logger.logPassed(
						MessageFormat.format("Run for ''{0}'' passed", junitRunnerClass.getName()))

			} else {
				// if the test failed, show the stacktrace of the failed step
				List failuresDescriptions = []
				for (Failure failure: result.getFailures()) {
					failuresDescriptions.add("\n" +
							formatStackTrace(failure.getTrace(), "\t") + "\t")
				}
				KeywordMain.stepFailed(
						MessageFormat.format("These following reason:\n {0}", failuresDescriptions),
						flowControl)
			}

			return junitResult

		}, flowControl, "Keyword runWithJUnitRunner failed")
	}


	/**
	 * 
	 * "aaa\nbbb\nccc" -> "    aaa\n    bbb\n    ccc\n"
	 *
	 * @param source
	 * @return
	 */
	public static String formatStackTrace(String lines, String indent = '>>  ') {
		StringWriter sw = new StringWriter()
		BufferedWriter bw = new BufferedWriter(sw)
		BufferedReader br = new BufferedReader(new StringReader(lines))
		String s;
		while ((s = br.readLine()) != null) {
			bw.println(indent + s)
		}
		bw.flush()
		return sw.toString()
	}




	/**
	 * logs various events triggered by JUnitCore class
	 * into Katalon Studio's Log.
	 * 
	 * Consequently you can see "started", "failed" and "finishede" messages 
	 * in the Katalon Studio's LogViewer.
	 * 
	 * @author kazurayam
	 *
	 */
	private static class RunListener4KS extends RunListener {

		boolean succeeded

		public void testStarted(Description description) {
			logger.startTest(
					description.getDisplayName(),
					new HashMap<String, String>(),
					new Stack<KeywordLogger.KeywordStackElement>())
			succeeded = true
		}

		public void testFailure(Failure failure) {
			succeeded = false
			String name =  failure.getDescription().getDisplayName()
			Throwable t = failure.getException()
			String stackTraceForThrowable = ExceptionsUtil.getStackTraceForThrowable(t)
			String message = MessageFormat.format(
					StringConstants.MAIN_LOG_MSG_FAILED_BECAUSE_OF,
					name,
					stackTraceForThrowable)
			logger.logMessage(ErrorCollector.fromError(t), message, t);
		}

		public void testFinished(Description description) {
			String name =  description.getDisplayName()
			if (succeeded) {
				logger.logPassed(name)
			}
			logger.endTest(name, new HashMap<String, String>())
		}
	}
}