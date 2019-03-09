package com.kazurayam.junit4ks

import java.lang.reflect.Method;

import org.junit.runner.Description;

public class IgnoreRestHelper {
	public static boolean isIgnoreable(Description description) {
		if (hasIgnoreRestAnnotation(description)) {
			return null == description.getAnnotation(IgnoreRest.class);
		}
		return false;
	}

	public static boolean hasIgnoreRestAnnotation(Description description) {
		Class<?> c = description.getTestClass();
		for (Method m : c.getDeclaredMethods()) {
			if (null != m.getAnnotation(IgnoreRest.class)) {
				return true;
			}
		}
		return false;
	}
}