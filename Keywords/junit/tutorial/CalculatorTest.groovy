package junit.tutorial

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test

class CalculatorTest {

	@Test
	void testMultiply() {
		Calculator calc = new Calculator()
		int expected = 12
		int actual = calc.multiply(3, 4)
		assertThat(actual, is(expected))
	}

	void compileMe() {}
}