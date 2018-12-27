package junittutorial

import com.kms.katalon.core.annotation.Keyword

class Calculator {

	@Keyword
	static int multiply(int x, int y) {
		return x * y
	}

	@Keyword
	static int divide(int x, int y) {
		return x / y
	}

	static void main(String[] args) {
		println "2 * 3 is " + Calculator.multiply(2, 3)
		println "6 / 3 is " + Calculator.divide(6, 3)
	}
}
