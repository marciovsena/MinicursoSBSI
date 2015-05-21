/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tutoriais.RDF;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;

/**
 * Tutorial 4 - Criacao de um MODELO RDF e sua serializacao para a sintaxe
 * RDF/XML
 */
public class Tutorial04 extends Object {

	public static void main(String args[]) {

		// String para criacao de URI de RECURSO
		String personURI = "http://somewhere/JohnSmith";

		// Strings para criacao de VALORES DE PROPRIEDADE do tipo LITERAL
		String givenName = "John";
		String familyName = "Smith";
		String fullName = givenName + " " + familyName;

		// Criacao de um MODELO RDF vazio
		Model model = ModelFactory.createDefaultModel();

		// Criacao e associacao de um RECURSO a um MODELO RDF, e adicao em
		// cascata de PROPRIEDADES a esse RECURSO
		Resource johnSmith = model
				.createResource(personURI)
				.addProperty(VCARD.FN, fullName)
				.addProperty(
						VCARD.N,
						model.createResource()
								.addProperty(VCARD.Given, givenName)
								.addProperty(VCARD.Family, familyName));

		// Serializacao do MODELO RDF para a sintaxe padr�o W3C RDF/XML
		// model.write(System.out);
		model.write(System.out, "RDF/XML");

	}
}
