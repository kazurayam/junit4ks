package my.keyword

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import com.kms.katalon.core.configuration.RunConfiguration

/**
 * This test demonstrates how to use so called "ExpandoMetaClass" of Groovy language.
 * See http://groovy-lang.org/metaprogramming.html#metaprogramming_emc for detail.
 * 
 * This JUnit test shows that you can dynamically override the static 
 *     getProjectDir() 
 * method of 
 *     com.kms.katalon.core.configuration.RunConfiguration
 * class. 
 * 
 * Overriding the Katalon Studio runtime engine by ExpandoMetaClass helps 
 * simplifying JUnit testcases for your custom keywords. This technique enables you 
 * to "mock" the runtime engine with just a few lines of Groovy code.
 * 
 * @author kazurayam
 *
 */
class GroovyExpandoMetaClassTest {

    private static final String PATH_X = "/Users/kazurayam/katalon-workspace/projectX"
 
    @Before
    void setup() {
        RunConfiguration.metaClass.static.getProjectDir = { -> return PATH_X }
    }

	@Test
	void testExpandoMetaClass() {
		String s = RunConfiguration.getProjectDir()
		println "[testExpandoMetaClass] " + s
		assertThat(s, is(PATH_X))
	}

}