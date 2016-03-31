/**
 * 
 */
package org.semanticweb.elk.owlapi.proofs;
/*
 * #%L
 * ELK OWL API Binding
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

import org.semanticweb.elk.owl.interfaces.ElkAnonymousIndividual;
import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.owl.visitors.ElkIndividualVisitor;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;

/**
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 */
class IndividualConverter implements ElkIndividualVisitor<OWLIndividual> {

	private final OWLDataFactory factory_;
	
	IndividualConverter(OWLDataFactory f) {
		factory_ = f;
	}
	
	@Override
	public OWLIndividual visit(ElkAnonymousIndividual ind) {
		return factory_.getOWLAnonymousIndividual(ind.getNodeId());
	}

	@Override
	public OWLIndividual visit(ElkNamedIndividual ind) {
		return factory_.getOWLNamedIndividual(IRI.create(ind.getIri().getFullIriAsString()));
	}

}