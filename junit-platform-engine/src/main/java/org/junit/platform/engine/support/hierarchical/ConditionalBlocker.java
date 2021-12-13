/*
 * Copyright 2015-2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.platform.engine.support.hierarchical;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;

import org.apiguardian.api.API;

@API(status = EXPERIMENTAL, since = "5.9")
public class ConditionalBlocker {
	private final Set<Supplier<Boolean>> conditions;

	public ConditionalBlocker() {
		conditions = new HashSet<>();
	}

	public ConditionalBlocker(Set<Supplier<Boolean>> conditions) {
		this.conditions = conditions;
	}

	public boolean isEmpty() {
		return conditions.isEmpty();
	}

	public void add(ConditionalBlocker blocker) {
		this.conditions.addAll(blocker.conditions);
	}

	public void waitForConditions() throws InterruptedException {
		if (!isEmpty()) {
			ForkJoinPool.managedBlock(new ConditionalManagedBlocker());
		}
	}

	private class ConditionalManagedBlocker implements ForkJoinPool.ManagedBlocker {
		private boolean checkConditions() {
			for (Supplier<Boolean> condition : conditions) {
				if (!condition.get()) {
					return false;
				}
			}

			return true;
		}

		@Override
		public boolean block() throws InterruptedException {
			Thread.sleep(5000);
			return isReleasable();
		}

		@Override
		public boolean isReleasable() {
			return checkConditions();
		}

	}
}
