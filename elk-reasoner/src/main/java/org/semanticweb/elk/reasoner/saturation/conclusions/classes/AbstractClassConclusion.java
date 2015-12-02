/**
 * 
 */
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;

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

import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusionEquality;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusionHash;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusionPrinter;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SaturationConclusion;

/**
 * A skeleton for implementation of {@link ClassConclusion}.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public abstract class AbstractClassConclusion implements ClassConclusion {

	private final IndexedContextRoot root_;

	protected AbstractClassConclusion(IndexedContextRoot root) {
		this.root_ = root;
	}

	@Override
	public IndexedContextRoot getConclusionRoot() {
		return this.root_;
	}

	@Override
	public IndexedContextRoot getOriginRoot() {
		return this.root_;
	}

	@Override
	public boolean equals(Object o) {
		return ClassConclusionEquality.equals(this, o);
	}

	@Override
	public int hashCode() {
		return ClassConclusionHash.hashCode(this);
	}

	@Override
	public String toString() {
		return ClassConclusionPrinter.toString(this);
	}
	
	@Override
	public <O> O accept(SaturationConclusion.Visitor<O> visitor) {
		return accept((ClassConclusion.Visitor<O>) visitor);
	}

}
