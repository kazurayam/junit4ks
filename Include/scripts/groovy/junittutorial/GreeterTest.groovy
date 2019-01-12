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
}
