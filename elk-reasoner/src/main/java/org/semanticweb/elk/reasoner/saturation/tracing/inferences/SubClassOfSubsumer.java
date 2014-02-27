/**
 * 
 */
package org.semanticweb.elk.reasoner.saturation.tracing.inferences;
/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2013 Department of Computer Science, University of Oxford
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

import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.conclusions.Conclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.DecomposedSubsumer;
import org.semanticweb.elk.reasoner.saturation.tracing.inferences.visitors.InferenceVisitor;

/**
 * Represents a subsumption inference. 
 * 
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 */
public class SubClassOfSubsumer extends DecomposedSubsumer implements Inference {
	
	private final IndexedClassExpression premise_;

	public SubClassOfSubsumer(IndexedClassExpression premise, IndexedClassExpression expression) {
		super(expression);
		premise_ = premise;
	}
	
	public Conclusion getPremise() {
		return new DecomposedSubsumer(premise_);
	}

	@Override
	public <I, O> O acceptTraced(InferenceVisitor<I, O> visitor, I parameter) {
		return visitor.visit(this, parameter);
	}

	@Override
	public IndexedClassExpression getInferenceContextRoot(IndexedClassExpression rootWhereStored) {
		return rootWhereStored;
	}

	@Override
	public String toString() {
		return super.toString() + " (subclassof)";
	}
}