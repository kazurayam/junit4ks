package my.keyword

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration

class RunConfigurationWrapper {

	@Keyword
	static String getProjectDir() {
		return RunConfiguration.getProjectDir()
	}
}