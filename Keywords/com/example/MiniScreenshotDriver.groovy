package com.example

import java.awt.image.BufferedImage

import javax.imageio.ImageIO

import org.openqa.selenium.WebDriver

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory

import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.Screenshot
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider
import ru.yandex.qatools.ashot.shooting.ShootingStrategies

/**
 * Wraps AShot (WebDriver Screenshot utility).
 * This lets you take a full page screenshot of a web page
 *
 * @author kazurayam
 */
class MiniScreenshotDriver {

	/**
	 * Capture a entire-page screenshot of a web page and return BufferedImage object.
	 * We get access to the page via the WebDriver object passed as argument.
	 * We make use of AShot library.
	 *
	 * @param webDriver
	 * @param timeout millisecond, wait for page to be displayed stable after scrolling downward
	 * @return BufferedImage
	 */
	static BufferedImage takeEntirePageImage(WebDriver webDriver, Integer timeout = 300) {
		Screenshot screenshot = new AShot().
				coordsProvider(new WebDriverCoordsProvider()).
				shootingStrategy(ShootingStrategies.viewportPasting(timeout)).
				takeScreenshot(webDriver)
		return screenshot.getImage()
	}

	/**
	 * Capture a entire-page screenshot of a web page and return BufferedImage object.
	 * We get access to the page via the WebDriver object obtained by DriverFactory.getWebDriver()
	 *
	 * @timeout millisecond, wait for page to displayed stable after scrolling downward
	 * @return BufferedImage
	 */
	@Keyword
	static BufferedImage takeEntirePageImage(Integer timeout = 300) {
		WebDriver webDriver = DriverFactory.getWebDriver()
		return takeEntirePageImage(webDriver, timeout)
	}

	/**
	 * Capture a entire-page screenshot of a web page and save PNG image into file.
	 * We get access to the page via the WebDriver object passed as argument.
	 *
	 * @param webDriver
	 * @param file output file
	 * @param timeout millisecond
	 */
	static void saveEntirePageImage(WebDriver webDriver, File file, Integer timeout = 300) {
		BufferedImage image = takeEntirePageImage(webDriver, timeout)
		ImageIO.write(image, "PNG", file)
	}

	/**
	 * Capture a entire-page screenshot of a web page and save PNG image into file.
	 * We get access to the page via the WebDriver object obtained by DriverFactory.getWebDriver()
	 *
	 * @param file output file
	 * @param timeout millisecond
	 */
	@Keyword
	static void saveEntirePageImage(File file, Integer timeout = 300) {
		WebDriver driver = DriverFactory.getWebDriver()
		saveEntirePageImage(driver, file, timeout)
	}
}