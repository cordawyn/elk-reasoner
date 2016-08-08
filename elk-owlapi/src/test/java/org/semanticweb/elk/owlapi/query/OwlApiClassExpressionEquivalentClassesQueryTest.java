/*
 * #%L
 * ELK OWL API Binding
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2016 Department of Computer Science, University of Oxford
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
package org.semanticweb.elk.owlapi.query;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.semanticweb.elk.io.IOUtils;
import org.semanticweb.elk.owlapi.OwlApiReasoningTestDelegate;
import org.semanticweb.elk.reasoner.query.BaseClassExpressionQueryTest;
import org.semanticweb.elk.reasoner.query.ClassExpressionQueryTestManifest;
import org.semanticweb.elk.reasoner.query.ClassQueryTestInput;
import org.semanticweb.elk.reasoner.query.EquivalentEntitiesTestOutput;
import org.semanticweb.elk.testing.ConfigurationUtils;
import org.semanticweb.elk.testing.ConfigurationUtils.TestManifestCreator;
import org.semanticweb.elk.testing.PolySuite;
import org.semanticweb.elk.testing.PolySuite.Config;
import org.semanticweb.elk.testing.PolySuite.Configuration;
import org.semanticweb.elk.testing.TestManifestWithOutput;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.reasoner.Node;

@RunWith(PolySuite.class)
public class OwlApiClassExpressionEquivalentClassesQueryTest extends
		BaseClassExpressionQueryTest<OWLClassExpression, EquivalentEntitiesTestOutput<OWLClass>> {

	public OwlApiClassExpressionEquivalentClassesQueryTest(
			final TestManifestWithOutput<ClassQueryTestInput<OWLClassExpression>, EquivalentEntitiesTestOutput<OWLClass>, EquivalentEntitiesTestOutput<OWLClass>> manifest) {
		super(manifest,
				new OwlApiReasoningTestDelegate<EquivalentEntitiesTestOutput<OWLClass>>(
						manifest) {

					@Override
					public EquivalentEntitiesTestOutput<OWLClass> getActualOutput()
							throws Exception {
						final Node<OWLClass> equivalent = reasoner_
								.getEquivalentClasses(
										manifest.getInput().getClassQuery());
						return new OwlApiEquivalentEntitiesTestOutput(
								equivalent);
					}

				});
	}

	@Config
	public static Configuration getConfig()
			throws IOException, URISyntaxException {

		return ConfigurationUtils.loadFileBasedTestConfiguration(
				INPUT_DATA_LOCATION, BaseClassExpressionQueryTest.class, "owl",
				"expected",
				new TestManifestCreator<ClassQueryTestInput<OWLClassExpression>, EquivalentEntitiesTestOutput<OWLClass>, EquivalentEntitiesTestOutput<OWLClass>>() {

					@Override
					public TestManifestWithOutput<ClassQueryTestInput<OWLClassExpression>, EquivalentEntitiesTestOutput<OWLClass>, EquivalentEntitiesTestOutput<OWLClass>> create(
							final URL input, final URL output)
							throws IOException {

						InputStream outputIS = null;
						try {
							outputIS = output.openStream();
							final ExpectedTestOutputLoader expected = ExpectedTestOutputLoader
									.load(outputIS);

							return new ClassExpressionQueryTestManifest<OWLClassExpression, EquivalentEntitiesTestOutput<OWLClass>>(
									input, expected.getQueryClass(),
									expected.getEquivalentEntitiesTestOutput());

						} finally {
							IOUtils.closeQuietly(outputIS);
						}

					}

				});

	}

	@Test
	@Ignore
	@Override
	public void testWithInterruptions() throws Exception {
		super.testWithInterruptions();
	}

}