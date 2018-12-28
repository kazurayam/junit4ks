package my.keyword

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory

class DriverFactoryWrapper {

	@Keyword
	static String getChromeDriverPath() {
		return DriverFactory.getChromeDriverPath()
	}
}