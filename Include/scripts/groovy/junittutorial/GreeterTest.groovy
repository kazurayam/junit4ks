package junittutorial

import static org.junit.Assert.assertEquals
import static org.junit.Assert.fail

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
class GreeterTest {

	@Test
	void testGreet() {
		String expected = "Hello, world"
		String actual = Greeter.greet("world")
		assertEquals(actual, expected)
	}

	/**
	 * this will fail, intentionally for demonstration purpose
	 */
	@Test
	void testGreet_intensionalFailure() {
		try {
			String expected = "Goodnight, Man"
			String actual = Greeter.greet("Man")
			assertEquals(actual, expected)
			fail("should fail here")
		} catch (java.lang.AssertionError e) {
			;
		}
	}
}
