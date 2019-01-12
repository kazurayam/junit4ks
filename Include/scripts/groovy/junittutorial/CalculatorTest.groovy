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
		int expected = 21
		int actual = Calculator.multiply(7, 3)
		assertThat(actual, is(expected))
	}

	@Test
	void testDivide_wrongType() {
		double expected = 1.5f
		double actual = Calculator.divide(3, 2)
		assertThat(actual, is(not(expected)))
	}

	@Test
	void testDivide() {
		int expected = 1
		int actual = Calculator.divide(3, 2)
		assertThat(actual, is(expected))
	}
}