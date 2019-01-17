package com.kazurayam.ksbackyard.junit

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.kazurayam.ksbackyard.junit.JUnitCustomKeywords

@RunWith(JUnit4.class)
class JUnitCustomKeywordsTest {

	@Test
	void testIndentLines() {
		String expected = "\taaa\n\tbbb\n\tccc\n"
		String actual = JUnitCustomKeywords.indentLines("aaa\nbbb\nccc\n")
		assertThat(actual, is(expected))
	}
}