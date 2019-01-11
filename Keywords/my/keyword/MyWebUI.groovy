package my.keyword

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


class MyWebUI {

	/**
	 * This static method opens a browser.
	 * If you chose Google Chrome AND, a new ChromeDriver object is instanciated with custom ChromeOptions specified.
	 * Otherwise <code>WebUI.openBrowser()</code> will be called as usual.
	 */
	@Keyword
	static void openBrowser() {
		WebUIDriverType executedBrowser = DriverFactory.getExecutedBrowser()
		if (executedBrowser == WebUIDriverType.CHROME_DRIVER) {
			// open custom Chrome browser
			System.setProperty('webdriver.chrome.driver', DriverFactory.getChromeDriverPath())

			// specify custom ChromeOptions
			ChromeOptions options = new ChromeOptions()
			//options.addArguments("user-data-dir=/Users/qcq0264/Library/Application Support/Google/Chrome/Default")
			WebDriver driver = new ChromeDriver(options)

			DriverFactory.changeWebDriver(driver)
		} else {
			// open browser as usual
			WebUI.openBrowser('')
		}
	}
}