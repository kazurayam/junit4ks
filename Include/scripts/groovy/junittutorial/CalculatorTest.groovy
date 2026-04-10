package junittutorial

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotEquals
import static org.junit.Assert.fail

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
class CalculatorTest {

	@Test
	void testMultiply() {
		int expected = 21
		int actual = Calculator.multiply(7, 3)
		assertEquals(actual, expected)
	}

	@Test
	void testDivide_wrongType() {
		double expected = 1.5f
		double actual = Calculator.divide(3, 2)
		assertNotEquals(actual, expected)
	}

	@Test
	void testDivide() {
		int expected = 1
		int actual = Calculator.divide(3, 2)
		assertEquals(actual, expected)
	}

	/**
	 * this test fails but catches it
	 */
	@Test
	void testFailAndCatch() {
		try {
			int expected = 1
			int actual = 0
			assertEquals(actual, expected)
			fail("should fail here")
		} catch (java.lang.AssertionError e) {
			; // as expected
		}
	}
}