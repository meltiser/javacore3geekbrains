package ru.grigorev.javacore3.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * @author Dmitriy Grigorev
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = METHOD)
public @interface AfterSuite {
}
