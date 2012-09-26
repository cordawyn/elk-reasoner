/*
 * #%L
 * ELK Reasoner
 * 
 * $Id$
 * $HeadURL$
 * %%
 * Copyright (C) 2011 - 2012 Department of Computer Science, University of Oxford
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
package org.semanticweb.elk.reasoner.indexing.hierarchy;

import org.semanticweb.elk.reasoner.indexing.visitors.IndexedClassExpressionVisitor;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.rules.RuleEngine;

/**
 * A dummy class representing changes in the index.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class IndexedClassExpressionChange extends IndexedClassExpression {

	@Override
	protected void updateOccurrenceNumbers(IndexUpdater indexUpdater,
			int increment, int positiveIncrement, int negativeIncrement) {
	}

	@Override
	public <O> O accept(IndexedClassExpressionVisitor<O> visitor) {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public void applyDecompositionRule(RuleEngine ruleEngine, Context context) {
	}

}
