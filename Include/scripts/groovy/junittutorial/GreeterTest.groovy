package junittutorial

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
class GreeterTest {

	@Test
	void testGreet() {
		String expected = "Hello, world"
		String actual = Greeter.greet("world")
		assertThat(actual, is(expected))
	}

	/**
	 * this will fail, intentionally for demonstration purpose
	 */
	@Test
	void testGreet_intentionalFailure() {
		String expected = "Goodnight, Man"
		String actual = Greeter.greet("Man")
		assertThat(actual, is(expected))
	}
}
