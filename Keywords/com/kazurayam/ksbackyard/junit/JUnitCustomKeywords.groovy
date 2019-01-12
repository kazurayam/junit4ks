package com.kazurayam.ksbackyard.junit

import java.text.MessageFormat

import org.junit.runner.Computer
import org.junit.runner.JUnitCore
import org.junit.runner.Result
import org.junit.runner.notification.Failure

import com.kazurayam.ksbackyard.junit.internal.JUnitRunnerResultImpl
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.keyword.internal.KeywordMain
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.model.FailureHandling


/**
 *
 * learned from https://github.com/katalon-studio/katalon-studio-testing-framework/blob/master/Include/scripts/groovy/com/kms/katalon/core/cucumber/keyword/CucumberBuiltinKeywords.groovy
 *
 * @author kazurayam
 *
 */
public class JUnitCustomKeywords {

	private static final KeywordLogger logger = KeywordLogger.getInstance(JUnitCustomKeywords.class)

	@Keyword
	public static JUnitRunnerResult runWithJUnitRunner(Class junitRunnerClass, FailureHandling flowControl) {
		return KeywordMain.runKeyword({
			JUnitCore core = new JUnitCore()
			Computer computer = new Computer()
			Result result = core.run(computer, junitRunnerClass)
			boolean runSuccess = result.wasSuccessful()
			JUnitRunnerResultImpl junitResult = new JUnitRunnerResultImpl(
					runSuccess ? 'passed' : 'failed', '', result)
			if (runSuccess) {
				logger.logPassed(MessageFormat.format("RunWith ''{0}'' was passed", junitRunnerClass.getName()))
			} else {
				List failuresDescriptions = []
				for (Failure failure: result.getFailures()) {
					failuresDescriptions.add(failure.getMessage())
				}
				KeywordMain.stepFailed(
						MessageFormat.format("These following reason:\n {0}", failuresDescriptions),
						flowControl)
			}
			return junitResult
		}, flowControl, "Keyword runWithJUnitRunner failed")
	}

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
		return runWithJUnitRunner(junitRunnerClass, RunConfiguration.getDefaultFailureHandling())
	}
		
}
