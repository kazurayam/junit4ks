package com.kazurayam.junit4ks

import java.text.MessageFormat

import org.junit.runner.Description
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunListener

import com.kms.katalon.core.constants.StringConstants
import com.kms.katalon.core.logging.ErrorCollector
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.util.internal.ExceptionsUtil

class RunListener4ks extends RunListener {

	private static final KeywordLogger logger = KeywordLogger.getInstance(RunListener4ks.class)

	boolean succeeded

	@Override
	void testStarted(Description description) throws Exception {
		succeeded = true
		logger.startTest(
				description.getDisplayName(),
				new HashMap<String, String>(),
				new Stack<KeywordLogger.KeywordStackElement>())
	}

	@Override
	void testFinished(Description description) throws Exception {
		String name =  description.getDisplayName()
		if (succeeded) {
			logger.logPassed(name)
		}
		logger.endTest(name, new HashMap<String, String>())
	}

	@Override
	void testFailure(Failure failure) throws Exception {
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
}
