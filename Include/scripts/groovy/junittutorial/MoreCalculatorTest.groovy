package junittutorial

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Test

@RunWith(JUnit4.class)
class MoreCalculatorTest {

	@Test
	void testDivide_notDouble() {
		Calculator calc = new Calculator()
		double expected = 1.5f
		double actual = calc.divide(3, 2)
		assertThat(actual, is(not(expected)))
	}
	
	@Test
	void testDivide() {
		Calculator calc = new Calculator()
		int expected = 1
		int actual = calc.divide(3, 2)
		assertThat(actual, is(expected))
	}
}