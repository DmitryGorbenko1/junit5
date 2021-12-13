/*
 * Copyright 2015-2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.api;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apiguardian.api.API;

/**
 * {@code @WaitsFor} is used to declare that the annotated test class or test
 * method is to be executed after the condition is resolved.
 *
 * <p>The condition is specified via {@link #clazz} and {@link #method()}.
 * Execution of the annotated element will occur only after the condition returns true.
 * The condition method must be convertible into Supplier with generic Boolean
 *
 * <p>This annotation can be repeated to declare the use of multiple shared resources.
 *
 * <p>Since JUnit Jupiter 5.4, this annotation is {@linkplain Inherited inherited}
 * within class hierarchies.
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@API(status = EXPERIMENTAL, since = "5.9")
@Repeatable(WaitsForList.class)
public @interface WaitsFor {
	/**
	 * Class in which to seek for the condition method
	 */
	Class<?> clazz();

	/**
	 * Static method that would return boolean
	 */
	String method();
}
