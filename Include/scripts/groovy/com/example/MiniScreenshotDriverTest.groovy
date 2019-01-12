package com.example

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class MiniScreenshotDriverTest {

	private static String URL = 'https://www.google.com'
	private static query = "katalon"
	private static Path outputDir

	@BeforeClass
	static void onlyOnce() {
		Path projectDir = Paths.get(RunConfiguration.getProjectDir())
		outputDir = projectDir.resolve("tmp")
		Files.createDirectories(outputDir)
	}

	@Before
	void setUp() {
		WebUI.openBrowser('')
	}

	@After
	void tearDown() {
		WebUI.closeBrowser()
	}

	@Test
	void saveIntoFile() {
		//@Given("I am on the Google Search page")
		WebUI.navigateToUrl(URL)

		//@When("I search for query")
		WebUI.verifyElementPresent(findTestObject("Page_Google_Search/input_q"), 10,
				FailureHandling.STOP_ON_FAILURE)
		WebUI.setText(findTestObject("Page_Google_Search/input_q"), query)
		WebUI.click(findTestObject("Page_Google_Search/input_btnK"))

		//@Then("the page should have input field preset with (.+)")
		WebUI.verifyElementPresent(findTestObject("Page_Google_Result/div_res"), 10,
				FailureHandling.STOP_ON_FAILURE)

		//@When("I take screenshot of the page using AShot")
		WebDriver driver = DriverFactory.getWebDriver()
		File pngFile = outputDir.resolve("google_search_${query}.png").toFile()
		com.example.MiniScreenshotDriver.saveEntirePageImage(driver, pngFile)

		//@Then("the result image file should be there")
		assertThat(pngFile.exists(), is(true))
	}
}