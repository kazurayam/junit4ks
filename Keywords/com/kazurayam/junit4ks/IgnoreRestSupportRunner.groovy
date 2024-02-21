package com.kazurayam.junit4ks

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.*;

public class IgnoreRestSupportRunner extends BlockJUnit4ClassRunner {
	public IgnoreRestSupportRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
		IgnoreRest ignoreRestAnnotation = method.getAnnotation(IgnoreRest.class);
		Description description = describeChild(method);
		if (IgnoreRestHelper.hasIgnoreRestAnnotation(description) && null == ignoreRestAnnotation) {
			notifier.fireTestIgnored(description);
		}
		else {
			super.runChild(method, notifier);
		}
	}
}
