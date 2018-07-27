package junit.tutorial_test

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test

import junit.tutorial.Calculator

class MoreCalculatorTest {

	@Test
	void testDivide() {
		Calculator calc = new Calculator()
		float expected = 1.3f
		float actual = calc.divide(3, 2)
		assertThat(actual, is(expected))
	}
}