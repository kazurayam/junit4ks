package myjunit

import org.junit.runner.Description
import org.junit.runner.Result
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunListener

public class MyRunListener extends RunListener {

	private int numberOfTestClass
	private int testExecuted
	private int testFailed
	private long begin

	public MyRunListener(int numberOfTestClass) {
		this.setBegin(System.currentTimeMillis)
		this.numberOfTestClass = numberOfTestClass
	}

	/**
	 * Called when an atomic test flags that it assumes a condition that is false
	 */
	@Override
	void testAssumptionFailure(Failure failure) {}

	/**
	 * Called when an atomic test fails, or when a listener throws an exception.
	 */
	@Override
	void testFailure(Failure failure) {
		this.testFailed += 1
	}

	/**
	 * Called when an atomic test has finished, whether the test succeeds or fails.
	 */
	@Override
	void testFinished(Description description) {}

	/**
	 * Called when a test will not be  run, generally because a test method is annotaged with Ignore
	 */
	@Override
	void testIgnored(Description description) {}

	/**
	 * Called when all tests have finished
	 */
	@Override
	void testRunFinished(Result result) {}

	/**
	 * Called before any tests have been run
	 */
	@Override
	void testRunStarted(Description description) {}

	/**
	 * Called when an atomic test is about to be started.
	 */
	@Override
	public void testStarted(Description description) {
		this.testExecuted += 1
	}

	/* --------------------------------------------------------------------- */

	/**
	 *
	 * @return the numberOfTestClass
	 */
	public int getNumberOfTestClass() {
		return numberOfTestClass
	}

	/**
	 *
	 * @param numberOfTestClass the numberOfTestClass to set
	 */
	public void setNumberOfTestClass(int numberOfTestClass) {
		this.numberOfTestClass = numberOfTestClass
	}

	/**
	 *
	 * @return the testExecuted
	 */
	public int getTestExecuted() {
		return testExecuted
	}

	/**
	 *
	 * @param testExecuted the testExecuted to set
	 */
	public void setTestExecuted(int testExecuted) {
		this.testExecuted = testExecuted
	}

	/**
	 *
	 * @return the testFailed
	 */
	public int getTestFailed() {
		return testFailed
	}

	/**
	 *
	 * @param testFailed the testFailed to set
	 */
	public void setTestFailed(int testFailed) {
		this.testFailed = testFailed
	}

	/**
	 *
	 * @return the begin
	 */
	public long getBegin() {
		return begin
	}

	/**
	 *
	 * @param begin the begin to set
	 */
	public void setBegin(long begin) {
		this.begin = begin
	}
}
