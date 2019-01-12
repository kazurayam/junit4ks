package junittutorial

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
class CalculatorTest {

	@Test
	void testMultiply() {
		println ">>> Hello from CalculatorTest#testMultiply()"
		Calculator calc = new Calculator()
		int expected = 21
		int actual = calc.multiply(7, 3)
		assertThat(actual, is(expected))
	}
}