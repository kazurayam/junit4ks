import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import org.junit.runner.notification.Failure
import org.junit.runner.Description

WebUI.comment("Run:${result.getRunCount()}, Failure:${result.getFailureCount()}, Ignored:${result.getIgnoreCount()}")

List<Failure> failures = result.getFailures()
for (Failure failure : failures) {
	StringBuilder sb = new StringBuilder()
	sb.append(failure.getDescription().getDisplayName() + "\n")
	sb.append(failure.getMessage() + "\n")
	//sb.append(failure.getTrace() + "\n")
	WebUI.comment(sb.toString())
}

if (result.getFailureCount() > 0) {
	KeywordUtil.markFailed("${result.getFailureCount()} test${result.getFailureCount() > 1 ? 's' : ''} failed")
}