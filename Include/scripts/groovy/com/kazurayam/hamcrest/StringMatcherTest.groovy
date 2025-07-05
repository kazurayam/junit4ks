package com.kazurayam.hamcrest

import org.hamcrest.text.IsEqualIgnoringCase
import static org.hamcrest.MatcherAssert.assertThat

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * See Hamcrest v1.3.0 javadoc at
 *     https://javadoc.io/doc/org.hamcrest/hamcrest-core/1.3/index.html
 */
@RunWith(JUnit4.class)
public class StringMatcherTest {

	/**
	 * https://www.baeldung.com/java-junit-hamcrest-guide#ExampleTest
	 */
	@Test
	public void given2Strings_whenEqual_thenCorrect() {
		String a = "foo";
		String b = "FOO";
		assertThat(a, IsEqualIgnoringCase.equalToIgnoringCase(b));
	}
}
