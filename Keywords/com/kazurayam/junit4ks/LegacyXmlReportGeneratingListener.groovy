package com.kazurayam.junit4ks

import org.junit.runner.Description
import org.junit.runner.Result
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunListener

/**
 * A mimic of https://memorynotfound.com/add-junit-listener-example/ which is able to
 * compile a test result report in XML.
 * 
 * Still TODO. 
 * 
 * Reporting requires significant development efforts.
 * On the other hand, I personally do not require the report file.
 * So, I am not going to finish this. 
 * 
 * @author kazurayam
 */
class LegacyXmlReportGeneratingListener extends RunListener4ks {

	void testRunStarted(Description description) throws Exception {
		println("Number of tests to execute: " + description.testCount())
	}

	void testRunFinished(Result result) throws Exception {
		println("Number of tests executed: " + result.getRunCount())
	}

	@Override
	void testStarted(Description description) throws Exception {
		super.testStarted(description)
		println("Starting: " + description.getMethodName())
	}

	@Override
	void testFinished(Description description) throws Exception {
		super.testFinished(description)
		println("Finished: " + description.getMethodName())
	}

	@Override
	void testFailure(Failure failure) throws Exception {
		super.testFailure(failure)
		println("Failed: " + failure.getDescription().getMethodName())
	}

	void testAssumptionFailure(Failure failure) {
		println("Failed: " + failure.getDescription().getMethodName())
	}

	void testIgnored(Description description) throws Exception {
		println("Ignored: " + description.getMethodName())
	}
}
