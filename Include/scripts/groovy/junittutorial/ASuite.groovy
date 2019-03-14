package junittutorial

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite.class)

@Suite.SuiteClasses([
	CalculatorTest.class,
	GreeterTest.class
])

public class ASuite {
}
