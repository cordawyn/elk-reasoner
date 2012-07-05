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
/**
 * @author Yevgeny Kazakov, May 13, 2011
 */
package org.semanticweb.elk.reasoner.indexing;

import java.util.Collection;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkAxiomProcessor;
import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedClass;
import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedIndividual;
import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedPropertyChain;

/**
 * Interface for public methods of the index of the ontology.
 * 
 * @author Yevgeny Kazakov
 * @author Frantisek Simancik
 * 
 */
public interface OntologyIndex {

	IndexedClassExpression getIndexed(ElkClassExpression elkClassExpression);

	IndexedPropertyChain getIndexed(
			ElkSubObjectPropertyExpression elkSubObjectPropertyExpression);

	IndexedClass getIndexedOwlThing();

	IndexedClass getIndexedOwlNothing();

	/**
	 * Should always contain owl:Thing and owl:Nothing
	 */
	Collection<IndexedClassExpression> getIndexedClassExpressions();

	Collection<IndexedClass> getIndexedClasses();

	Collection<IndexedIndividual> getIndexedIndividuals();

	Collection<IndexedPropertyChain> getIndexedPropertyChains();

	Collection<IndexedObjectProperty> getIndexedObjectProperties();

	Collection<IndexedObjectProperty> getReflexiveObjectProperties();

	ElkAxiomProcessor getAxiomInserter();

	ElkAxiomProcessor getAxiomDeleter();

	void clear();
}
