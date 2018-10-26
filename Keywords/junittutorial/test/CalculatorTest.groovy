package junittutorial.test

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test

import junittutorial.Calculator

class CalculatorTest {

	@Test
	void testMultiply() {
		println ">>> Hello from CalculatorTest#testMultiply()"
		Calculator calc = new Calculator()
		int expected = 12
		int actual = calc.multiply(3, 4)
		assertThat(actual, is(expected))
	}
}