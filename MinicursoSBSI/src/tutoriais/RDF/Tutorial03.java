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
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.VCARD;

/**
 * Tutorial 3 - Uso de m�todos de acesso a DECLARA��ES (ou STATEMENTS)
 */
public class Tutorial03 extends Object {
	public static void main(String args[]) {

		// String para cria��o de URI de RECURSO
		String personURI = "http://somewhere/JohnSmith";

		// Strings para cria��o de VALORES DE PROPRIEDADE do tipo LITERAL
		String givenName = "John";
		String familyName = "Smith";
		String fullName = givenName + " " + familyName;

		// Cria��o de um MODELO RDF vazio
		Model model = ModelFactory.createDefaultModel();

		// Cria��o e associa��o de um RECURSO a um MODELO RDF, e adi��o em
		// cascata de PROPRIEDADES a esse RECURSO
		Resource johnSmith = model
				.createResource(personURI)
				.addProperty(VCARD.FN, fullName)
				.addProperty(
						VCARD.N,
						model.createResource()
								.addProperty(VCARD.Given, givenName)
								.addProperty(VCARD.Family, familyName));

		// Retorna todas as DECLARA��ES (ou TRIPLAS) do MODELO RDF
		StmtIterator iter = model.listStatements();

		// Cada elemento do StmtIterator � uma TRIPLA, ent�o pode-se obter os
		// respectivos SUJEITO, PREDICADO e OBJETO de cada TRIPLA
		while (iter.hasNext()) {
			Statement stmt = iter.nextStatement(); // obt�m a pr�xima TRIPLA
			Resource subject = stmt.getSubject(); // obt�m o SUJEITO
			Property predicate = stmt.getPredicate(); // obt�m o PREDICADO
			RDFNode object = stmt.getObject(); // obt�m o OBJETO (pode ser uma
												// URI de RECURSO ou um LITERAL
												// ==> RDFNode)

			// M�todo toString() retorna uma representa��o em String das URIs de
			// RECURSOS e PROPRIEDADES
			System.out.print(subject.toString());
			System.out.print(" " + predicate.toString() + " ");

			// Verifica��o do tipo de objeto Java: RECURSO (linha 71) ou LITERAL
			// (linha 73)???
			if (object instanceof Resource) {
				System.out.print(object.toString());
			} else {
				// OBJETO � um LITERAL
				System.out.print(" \"" + object.toString() + "\"");
			}
			System.out.println(" .");
		}
	}
}
