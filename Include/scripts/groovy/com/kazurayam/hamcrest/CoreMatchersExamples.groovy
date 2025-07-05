package com.kazurayam.hamcrest

import static org.hamcrest.CoreMatchers.is
import static org.hamcrest.CoreMatchers.not
import static org.hamcrest.CoreMatchers.nullValue
import static org.hamcrest.MatcherAssert.assertThat

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * See Hamcrest v1.3.0 javadoc at
 *     https://javadoc.io/doc/org.hamcrest/hamcrest-core/1.3/index.html 
 */
@RunWith(JUnit4.class)
class CoreMatchersExamples {

	@Test
	void test_id_not_null() {
		String actual = "foo"
		assertThat(actual, is(not(nullValue())))
	}
	
	
}
