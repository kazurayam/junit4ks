package com.kazurayam.junit4ks

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.kazurayam.junit4ks.JUnitCustomKeywords

@RunWith(JUnit4.class)
class JUnitCustomKeywordsTest {

	@Test
	void test_formatStackTrace() {
		String expected = "\taaa\n\tbbb\n\tccc\n"
		String actual = JUnitCustomKeywords.formatStackTrace("aaa\nbbb\nccc\n","\t")
		assertThat(actual, is(expected))
	}
}