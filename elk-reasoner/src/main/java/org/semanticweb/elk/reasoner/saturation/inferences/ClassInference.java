/**
 * 
 */
package org.semanticweb.elk.reasoner.saturation.inferences;

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

import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * Represents an inference as an extended conclusion. All premises can be
 * accessed via a suitable visitor.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public interface ClassInference extends ClassConclusion, SaturationInference {

	/**
	 * @return the {@link IndexedContextRoot} of the {@link Context} where this
	 *         {@link ClassInference} was produced; this cannot be {@code null}.
	 */
	public IndexedContextRoot getInferenceRoot();

	public <I, O> O accept(Visitor<I, O> visitor, I input);

	/**
	 * Visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public static interface Visitor<I, O>
			extends
				BackwardLinkInference.Visitor<I, O>,
				ContradictionInference.Visitor<I, O>,
				DisjointSubsumerInference.Visitor<I, O>,
				ForwardLinkInference.Visitor<I, O>,
				LinkComposition.Visitor<I, O>,
				PropagationInference.Visitor<I, O>,
				SubClassInclusionInference.Visitor<I, O> {

		// combined interface

	}

}