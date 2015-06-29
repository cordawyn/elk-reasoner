/**
 * 
 */
package org.semanticweb.elk.reasoner.saturation.conclusions.implementation;

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

import org.semanticweb.elk.reasoner.saturation.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.interfaces.Conclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.visitors.ConclusionEqualityChecker;
import org.semanticweb.elk.reasoner.saturation.conclusions.visitors.ConclusionHashGenerator;
import org.semanticweb.elk.reasoner.saturation.conclusions.visitors.ConclusionVisitor;

/**
 * A wrapper around the underlying {@link Conclusion} which implements
 * {@code equals} and {@code hashCode}. Useful for storing and looking up
 * conclusions in collections.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class ConclusionEntry implements Conclusion {

	private static final ConclusionEqualityChecker equalityChecker = new ConclusionEqualityChecker();
	private static final ConclusionHashGenerator hashGen = new ConclusionHashGenerator();

	private final Conclusion conclusion_;

	public ConclusionEntry(Conclusion c) {
		conclusion_ = c;
	}

	@Override
	public IndexedContextRoot getConclusionRoot() {
		return conclusion_.getConclusionRoot();
	}
	
	@Override
	public IndexedContextRoot getOriginRoot() {
		return conclusion_.getOriginRoot();
	}
	
	@Override
	public <I, O> O accept(ConclusionVisitor<I, O> visitor, I parameter) {
		return conclusion_.accept(visitor, parameter);
	}

	@Override
	public int hashCode() {
		return conclusion_.accept(hashGen, null);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Conclusion) {
			return conclusion_.accept(equalityChecker, (Conclusion) obj);
		}

		return false;
	}

	@Override
	public String toString() {
		return conclusion_.toString();
	}	

}
