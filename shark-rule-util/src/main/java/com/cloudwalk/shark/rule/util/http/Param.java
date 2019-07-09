package com.cloudwalk.shark.rule.util.http;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ ElementType.PARAMETER})
@Retention(RUNTIME)
public @interface Param {

	String value() default "";
}