import com.kazurayam.ksbackyard.junit.JUnitCustomKeywordsTest
import com.kms.katalon.core.model.FailureHandling

CustomKeywords.'com.kazurayam.junit4ks.JUnitCustomKeywords.runWithJUnitRunner'(
	JUnitCustomKeywordsTest.class,
	FailureHandling.CONTINUE_ON_FAILURE)

	//FailureHandling.OPTIONAL)

	/*
	 * Here you may specify FailureHandling.OPTIONAL.
	 * Then the log output will become less verbose. 
	 * But the test case will PASS even if the JUnitCustomKeywordsTest failed internally.
	 */
