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
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apiguardian.api.API;

/**
 * {@code @WaitsForList} is a container for one or more
 * {@link WaitsFor @WaitsFor} declarations.
 *
 * <p>Note, however, that use of the {@code @WaitsForList} container is
 * completely optional since {@code @WaitsFor} is a
 * {@linkplain java.lang.annotation.Repeatable repeatable} annotation.
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@API(status = EXPERIMENTAL, since = "5.9")
public @interface WaitsForList {
	/**
	 * An array of one or more {@linkplain WaitsFor @WaitsFor} declarations.
	 */
	WaitsFor[] value();
}
