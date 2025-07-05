package com.kazurayam.junit4ks

import static org.junit.Assert.assertEquals

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.kazurayam.junit4ks.JUnitCustomKeywords

@RunWith(JUnit4.class)
class JUnitCustomKeywordsTest {

	@Test
	void testIndentLines() {
		String expected = "\taaa\n\tbbb\n\tccc\n"
		String actual = JUnitCustomKeywords.indentLines("aaa\nbbb\nccc\n", "\t")
		assertEquals(actual, expected)
	}
}
