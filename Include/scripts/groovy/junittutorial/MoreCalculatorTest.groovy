package junittutorial

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test

class MoreCalculatorTest {

	@Test
	void testDivide() {
		Calculator calc = new Calculator()
		float expected = 1.5f
		float actual = calc.divide(3, 2)
		assertThat(actual, is(expected))
	}
}