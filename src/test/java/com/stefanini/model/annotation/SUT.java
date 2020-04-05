package com.stefanini.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import mockit.Tested;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Tested(fullyInitialized = true)
public @interface SUT {

}
