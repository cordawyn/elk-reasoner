/**
 * 
 */
package org.semanticweb.elk.reasoner.saturation.properties.inferences;

/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2015 Department of Computer Science, University of Oxford
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

import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubPropertyChain;

/**
 * {@link SubPropertyChain} representing a tautology {@code r1 ○ ... ○ rn  ⊑ r1 ○ ... ○ rn}
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @author "Yevgeny Kazakov"
 */
public class SubPropertyChainTautology extends AbstractSubPropertyChainInference {

	public SubPropertyChainTautology(IndexedPropertyChain chain) {
		super(chain, chain);
	}

	public IndexedPropertyChain getChain() {
		return super.getSubChain();
	}

	@Override
	public String toString() {
		return "Init sub-chain: " + getSubChain() + " => " + getSuperChain();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof SubPropertyChainTautology)) {
			return false;
		}

		SubPropertyChainTautology inf = (SubPropertyChainTautology) obj;

		return getChain().equals(inf.getChain());
	}

	@Override
	public int hashCode() {
		return getChain().hashCode();
	}

	@Override
	public <O> O accept(SubPropertyChainInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}
	
	/**
	 * Visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public static interface Visitor<O> {
		
		public O visit(SubPropertyChainTautology inference);
		
	}

}
