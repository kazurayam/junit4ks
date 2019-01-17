package junittutorial

import com.kms.katalon.core.annotation.Keyword

public class Greeter {

	@Keyword
	static String greet(String name) {
		return "Hello, " + name
	}
}
