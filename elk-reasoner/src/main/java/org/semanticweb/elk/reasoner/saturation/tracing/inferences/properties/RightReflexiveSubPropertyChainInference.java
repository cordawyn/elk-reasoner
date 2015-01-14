/**
 * 
 */
package org.semanticweb.elk.reasoner.saturation.tracing.inferences.properties;
/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2014 Department of Computer Science, University of Oxford
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

import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedBinaryPropertyChain;
import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.tracing.inferences.visitors.ObjectPropertyInferenceVisitor;
import org.semanticweb.elk.util.hashing.HashGenerator;

/**
 * TODO
 * 
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 */
public class RightReflexiveSubPropertyChainInference extends ReflexiveSubPropertyChainInference {

	public RightReflexiveSubPropertyChainInference(IndexedPropertyChain subChain, IndexedBinaryPropertyChain chain) {
		super(subChain, chain);
	}

	@Override
	public ReflexivePropertyChain<IndexedPropertyChain> getReflexivePremise() {
		return new ReflexivePropertyChain<IndexedPropertyChain>(getSuperPropertyChain().getRightProperty());
	}

	@Override
	public <I, O> O acceptTraced(ObjectPropertyInferenceVisitor<I, O> visitor,
			I input) {
		return visitor.visit(this, input);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof RightReflexiveSubPropertyChainInference)) {
			return false;
		}
		
		RightReflexiveSubPropertyChainInference inf = (RightReflexiveSubPropertyChainInference) obj;
		
		return getSubPropertyChain().equals(inf.getSubPropertyChain()) && getSuperPropertyChain().equals(inf.getSuperPropertyChain());
	}

	@Override
	public int hashCode() {
		return HashGenerator.combineListHash(getSubPropertyChain().hashCode(), getSuperPropertyChain().hashCode());
	}

}