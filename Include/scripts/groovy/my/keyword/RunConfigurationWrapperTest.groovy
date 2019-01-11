package my.keyword

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import com.kms.katalon.core.configuration.RunConfiguration

class RunConfigurationWrapperTest {

	private static final String PATH_X = "/Users/kazurayam/katalon-workspace/projectX"
	 
	@Before
	void setup() {
		RunConfiguration.metaClass.static.getProjectDir = { ->
			return PATH_X
		}
	}

	@Test
	void testExpandoMetaClass() {
		String s = RunConfiguration.getProjectDir()
		println "[testExpandoMetaClass] " + s
		assertThat(s, is(PATH_X))
	}

    @Test
	void testGetProjectDir() {
        String s = RunConfigurationWrapper.getProjectDir()
        println "[testGetProjectDir] " + s
        assertThat(s, is(PATH_X))
    }
}