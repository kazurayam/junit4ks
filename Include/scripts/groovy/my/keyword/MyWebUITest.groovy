package my.keyword

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test

public class MyWebUITest {

	@Test
	void testOpenBrowser() {
		println ">>> Hello from MyWebUITest#testOpenBrowser()"
		MyWebUI.openBrowser()
	}
}