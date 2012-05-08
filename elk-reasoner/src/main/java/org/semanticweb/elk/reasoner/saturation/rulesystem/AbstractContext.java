/*
 * #%L
 * elk-reasoner
 * 
 * $Id$
 * $HeadURL$
 * %%
 * Copyright (C) 2011 Oxford University Computing Laboratory
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.semanticweb.elk.reasoner.saturation.rulesystem;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedClassExpression;

/**
 * Abstract base class for representing contexts. Contexts are used to store
 * information during saturation, and to support the processing of new
 * derivations. To this end, this base class provides facilities for managing
 * the processing of new derivations, ensuring that only new derivations are
 * used when searching for applicable derivation rules. The implementation
 * allows concurrent processing of derivations by multiple parallel workers. The
 * data structures for storing processed information are implemented by
 * subclasses according to the needs of the inference system.
 * 
 * @author Frantisek Simancik
 * @author Markus Kroetzsch
 */
public class AbstractContext implements Context {

	/**
	 * The root expression that this context relates to.
	 */
	private final IndexedClassExpression root;

	/**
	 * The queue of derivations for that context that still need to be
	 * processed.
	 */
	private final Queue<Queueable<?>> queue;

	/**
	 * A context is active if its queue is not empty or it is being processed.
	 */
	private final AtomicBoolean isActive;

	/**
	 * Construct a new context for the given root expression. Initially, the
	 * context is not active.
	 * 
	 * @param root
	 */
	public AbstractContext(IndexedClassExpression root) {
		this.root = root;
		this.queue = new ConcurrentLinkedQueue<Queueable<?>>();
		this.isActive = new AtomicBoolean(false);
	}

	/**
	 * Get the root expression of that context.
	 * 
	 * @return root expression
	 */
	@Override
	public IndexedClassExpression getRoot() {
		return root;
	}

	/**
	 * Get the current queue of items that still need to be processed for this
	 * context.
	 * 
	 * @return queue
	 */
	@Override
	public final Queue<Queueable<?>> getQueue() {
		return queue;
	}

	/**
	 * Ensure that the context is active, and return true if the activation
	 * state has been changed from false to true. This method is thread safe:
	 * for two concurrent executions only one succeeds.
	 * 
	 * @return true if the context was not active; returns false otherwise
	 */
	@Override
	public boolean tryActivate() {
		if (isActive.get()) {
			return false;
		}
		return isActive.compareAndSet(false, true);
	}

	/**
	 * Ensure that the context is not active, and return true if the activation
	 * state has been changed from true to false. This method is thread safe:
	 * for two concurrent executions only one succeeds.
	 * 
	 * @return true if the context was active; returns false otherwise
	 */
	@Override
	public boolean tryDeactivate() {
		if (!isActive.get()) {
			return false;
		}
		return isActive.compareAndSet(true, false);
	}

}
