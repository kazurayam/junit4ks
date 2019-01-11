package junittutorial

import com.kms.katalon.core.annotation.Keyword

class Calculator {

	@Keyword
	static int add(int a, int b) {
		return a + b;
	}

	@Keyword
	static int subtract(int a, int b) {
		return a - b;
	}

	@Keyword
	static int multiply(int x, int y) {
		return x * y
	}

	@Keyword
	static int divide(int x, int y) {
		return x / y
	}

	@Keyword
	static int power(int a, int b){
		int answer =a;
		for (int x = 2; x <= b; x++){
			answer *= a;
		}
		return answer;
	}

	static void main(String[] args) {
		println "2 + 3 is " + Calculator.add(2, 3)
		println "2 - 3 is " + Calculator.subtract(2, 3)
		println "2 * 3 is " + Calculator.multiply(2, 3)
		println "6 / 3 is " + Calculator.divide(6, 3)
		println "2 ^ 3 is " + Calculator.power(2, 3)
	}
}
