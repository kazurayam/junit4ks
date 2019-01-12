package com.kazurayam.ksbackyard.junit

import org.junit.runner.Result

public interface JUnitRunnerResult {

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
