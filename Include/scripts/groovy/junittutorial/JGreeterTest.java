package junittutorial;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * You can write JUnit4 Test in Java in Katalon Studio.
 * 
 * However, the file path must be under `Include/scripts/groovy/` --- it's ridiculous, isn't it?
 */
@RunWith(JUnit4.class)
public class JGreeterTest {

	@SuppressWarnings({"deprecation"})
	@Test
	public void testGreet() {
		String expected = "Hello, world";
		String actual = Greeter.greet("world");
		System.out.println(String.format("[testGreet] expected=\"%s\", actual=\"%s\"", expected, actual));
		assertThat(actual, is(expected));
	}

	/**
	 * this will fail, intentionally for demonstration purpose
	 */
	@SuppressWarnings({"deprecation"})
	@Test
	public void testGreet_intentionalFailure() {
		String expected = "Goodnight, Man";
		String actual = Greeter.greet("Man");
		System.out.println(String.format("[testGreet_intentionalFailure] expected=\"%s\", actual=\"%s\"", expected, actual));
		assertThat(actual, is(expected));
	}
}
