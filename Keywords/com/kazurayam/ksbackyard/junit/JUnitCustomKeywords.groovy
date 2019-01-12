package com.kazurayam.ksbackyard.junit

import java.text.MessageFormat

import org.junit.runner.Computer
import org.junit.runner.JUnitCore
import org.junit.runner.Result
import org.junit.runner.notification.Failure

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
	
	
	
	
	/**
	 * 
	 * @author urayamakazuaki
	 *
	 */
	public static interface JUnitRunnerResult {
		
		/**
		 * @return passed or failed
		 */
		String getStatus()
		
		/**
		 * Optional:
		 * @return absolute path of generated junit report
		 */
		String getReportLocation()
		
		/**
		 * Optional: Used when the keyword is {@link JUnitCustomKeywords#runWithJUnitRunner(Class)}
		 * @return an instance of JUnit Result, null if the keyword is NOT
		 * {@link JUnitBuiltinKeywords#runWithJUnitRunner(Class)}
		 */
		Result getJUnitRunnerResult()
	}
	
	
	/**
	 * 
	 * @author urayamakazuaki
	 *
	 */
	public static class JUnitRunnerResultImpl implements JUnitRunnerResult {
		private String status
		private String reportLocation
		private Result result
	
		public JUnitRunnerResultImpl(String status, String reportLocation) {
			this(status, reportLocation, null)
		}
	
		public JUnitRunnerResultImpl(String status, String reportLocation, Result result) {
			this.status = status
			this.reportLocation = reportLocation
			this.result = result
		}
	
		@Override
		public String getStatus() {
			return status
		}
	
		@Override
		public String getReportLocation() {
			return reportLocation
		}
	
		@Override
		public Result getJUnitRunnerResult() {
			return result
		}
	
		@Override
		public String toString() {
			return "JUnitRunnerResultImpl{"
			+ "status: " + status
			+ ", reportLocation: " + reportLocation
			+ ", junitRunnerResult: " + (result != null ? result.toString() : "null")
			+ "}"
		}
	}
	
}
