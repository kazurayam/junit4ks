package junittutorial

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotEquals
import static org.junit.Assert.fail

import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
public class CalculatorFailingTest {

	/**
	 * this test throws an Exception but not catching it
	 */
    @Ignore
	@Test
	void testFailNoCatch() {
		int expected = 1
		int actual = 0
		assertEquals(actual, expected)
	}

}
