package org.semanticweb.elk.reasoner.saturation.rules.contextinit;

import org.semanticweb.elk.reasoner.saturation.conclusions.ContextInitialization;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.rules.ConclusionProducer;
import org.semanticweb.elk.reasoner.saturation.rules.RuleVisitor;

/**
 * A skeleton implementation of {@link ContextInitRule}
 * 
 * @author "Yevgeny Kazakov"
 */
abstract class AbstractContextInitRule implements ContextInitRule {

	@Override
	public void accept(RuleVisitor visitor, ContextInitialization premise,
			Context context, ConclusionProducer producer) {
		accept((ContextInitRuleVisitor) visitor, premise, context, producer);
	}

}
