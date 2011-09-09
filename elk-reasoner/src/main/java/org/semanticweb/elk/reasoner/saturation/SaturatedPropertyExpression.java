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

package org.semanticweb.elk.reasoner.saturation;

import java.util.Set;

import org.semanticweb.elk.reasoner.indexing.IndexedPropertyExpression;
import org.semanticweb.elk.util.collections.ArrayHashSet;
import org.semanticweb.elk.util.collections.HashListMultimap;

/**
 * 
 * This object is used for fast retrieval of property inclusions and compositions 
 * which are needed during ClassExpressionSaturation. 
 * 
 * @author Frantisek Simancik
 *
 */
public class SaturatedPropertyExpression {
	protected final IndexedPropertyExpression root;
	
	protected final Set<IndexedPropertyExpression>
		derivedSubObjectProperties;
	
	protected final Set<IndexedPropertyExpression>
		derivedSuperObjectProperties;
	
	protected HashListMultimap<IndexedPropertyExpression,
			IndexedPropertyExpression>
		propertyCompositionsByLeftSubProperty;
	
	protected HashListMultimap<IndexedPropertyExpression,
			IndexedPropertyExpression>
		propertyCompositionsByRightSubProperty;

	
	public SaturatedPropertyExpression(IndexedPropertyExpression iop) {
		this.root = iop;
		this.derivedSuperObjectProperties =
			new ArrayHashSet<IndexedPropertyExpression>();
		this.derivedSubObjectProperties =
			new ArrayHashSet<IndexedPropertyExpression>();
	}
	
	public IndexedPropertyExpression getRoot() {
		return root;
	}

	public Set<IndexedPropertyExpression> getSubObjectProperties() {
		return derivedSubObjectProperties;
	}

	public Set<IndexedPropertyExpression> getSuperObjectProperties() {
		return derivedSuperObjectProperties;
	}

}
