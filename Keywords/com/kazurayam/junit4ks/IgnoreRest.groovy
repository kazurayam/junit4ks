package com.kazurayam.junit4ks

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target([ ElementType.METHOD ])
public @interface IgnoreRest {}