import static com.kazurayam.junit4ks.JUnitCustomKeywords.runWithJUnitRunner
import com.kms.katalon.core.model.FailureHandling

import junittutorial.CalculatorTest
import junittutorial.GreeterTest

/**
 * AllTestRunner shows you how to run multiple tests as a batch.
 * The following tests will fail; that's intentional for demonstration purpose.
 * Please note you can specify FailureHandling object as args[1]. 
 */
runWithJUnitRunner(CalculatorTest.class, FailureHandling.CONTINUE_ON_FAILURE)
runWithJUnitRunner(GreeterTest.class, FailureHandling.CONTINUE_ON_FAILURE)