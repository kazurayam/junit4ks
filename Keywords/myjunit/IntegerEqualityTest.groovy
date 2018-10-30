package myjunit

import static org.junit.Assert.assertTrue

import org.junit.Test

/**
 * How to generate html report if my junit is not run by and but by junitcore.run
 * https://stackoverflow.com/questions/26411163/how-to-generate-html-report-if-my-junit-is-not-run-by-ant-but-by-junitcore-run
 *
 * @author kazurayam
 *
 */
public class IntegerEqualityTest {

	@Test
	public void test() {
		int i = 5
		int j = 5
		assertTrue(i == j)
	}

	@Test
	public void test2() {
		int i = 5
		int j = 15
		assertTrue(i == j)
	}
}