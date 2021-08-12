package com.kazurayam.junit4ks

import org.junit.runner.Result


/**
 *
 * @author urayamakazuaki
 *
 */
public class JUnitRunnerResultImpl implements JUnitRunnerResult {

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