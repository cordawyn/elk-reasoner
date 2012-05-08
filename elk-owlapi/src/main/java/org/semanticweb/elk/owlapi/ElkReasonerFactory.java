/*
 * #%L
 * elk-reasoner
 * 
 * $Id$
 * $HeadURL$
 * %%
 * Copyright (C) 2011 Department of Computer Science, University of Oxford
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
/**
 * @author Yevgeny Kazakov, Jun 29, 2011
 */
package org.semanticweb.elk.owlapi;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.IllegalConfigurationException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;

/**
 * @author Yevgeny Kazakov
 * 
 */
public class ElkReasonerFactory implements OWLReasonerFactory {

	@Override
	public String getReasonerName() {
		return ElkReasonerFactory.class.getPackage().getImplementationTitle();
	}

	@Override
	public OWLReasoner createNonBufferingReasoner(OWLOntology ontology) {
		return new ElkReasoner(ontology, false, null);
	}

	@Override
	public OWLReasoner createReasoner(OWLOntology ontology) {
		return new ElkReasoner(ontology, true, null);
	}

	@Override
	public OWLReasoner createNonBufferingReasoner(OWLOntology ontology,
			OWLReasonerConfiguration config)
			throws IllegalConfigurationException {
		return createElkReasoner(ontology, true, config);
	}

	@Override
	public OWLReasoner createReasoner(OWLOntology ontology,
			OWLReasonerConfiguration config)
			throws IllegalConfigurationException {
		return createElkReasoner(ontology, false, config);
	}

	ElkReasoner createElkReasoner(OWLOntology ontology, boolean isBufferingMode, OWLReasonerConfiguration config) throws IllegalConfigurationException {
		//here we check if the passed configuration also has ELK's parameters
		if (config != null && config instanceof ElkReasonerConfiguration) {
			return new ElkReasoner(ontology, isBufferingMode, config.getProgressMonitor(), ((ElkReasonerConfiguration)config).getElkConfiguration());
		}
		else {
			return new ElkReasoner(ontology, isBufferingMode, config != null ? config.getProgressMonitor() : null);	
		}
	}
}
