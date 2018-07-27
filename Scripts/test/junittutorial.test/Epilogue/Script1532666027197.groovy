import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.comment("Run:${result.getRunCount()}, Failure:${result.getFailureCount()}, Ignored:${result.getIgnoreCount()}")

if (result.getFailureCount() > 0) {
	KeywordUtil.markFailed("${result.getFailureCount()} test${result.getFailureCount() > 1 ? 's' : ''} failed")
}