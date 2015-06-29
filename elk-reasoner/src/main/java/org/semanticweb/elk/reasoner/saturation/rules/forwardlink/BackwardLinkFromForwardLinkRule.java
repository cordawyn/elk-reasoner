package org.semanticweb.elk.reasoner.saturation.rules.forwardlink;

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

import java.util.ArrayList;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.interfaces.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.interfaces.ForwardLink;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ConclusionProducer;
import org.semanticweb.elk.reasoner.saturation.tracing.inferences.ReversedForwardLink;
import org.semanticweb.elk.reasoner.saturation.tracing.inferences.SuperReversedForwardLink;

/**
 * A {@link ForwardLinkRule} applied when processing {@link ForwardLink}
 * producing the corresponding {@link BackwardLink}
 * 
 * @author "Yevgeny Kazakov"
 */
public class BackwardLinkFromForwardLinkRule extends AbstractForwardLinkRule {

	public static final String NAME = "BackwardLink from ForwardLink";

	private static final BackwardLinkFromForwardLinkRule INSTANCE_ = new BackwardLinkFromForwardLinkRule();

	public static BackwardLinkFromForwardLinkRule getInstance() {
		return INSTANCE_;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void apply(ForwardLink premise, ContextPremises premises,
			ConclusionProducer producer) {
		IndexedPropertyChain relation = premise.getForwardChain();
		IndexedContextRoot source = premises.getRoot();
		if (relation instanceof IndexedObjectProperty) {
			producer.produce(new ReversedForwardLink(premise,
					(IndexedObjectProperty) relation));
		} else {
			ArrayList<IndexedObjectProperty> superProperties = relation
					.getToldSuperProperties();
			ArrayList<ElkAxiom> superPropertiesReasons = relation
					.getToldSuperPropertiesReasons();
			for (int i = 0; i < superProperties.size(); i++) {
				producer.produce(
						new SuperReversedForwardLink(source, superProperties
								.get(i), premise, superPropertiesReasons.get(i)));
			}
		}

	}

	@Override
	public void accept(ForwardLinkRuleVisitor visitor, ForwardLink premise,
			ContextPremises premises, ConclusionProducer producer) {
		visitor.visit(this, premise, premises, producer);
	}

}
