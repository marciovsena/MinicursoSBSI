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
 * Tutorial 4 - Cria��o de um MODELO RDF e sua serializa��o para as sintaxes
 * RDF/XML, N-TRIPLES e TURTLE
 */
public class Tutorial04 extends Object {

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

		// Serializa��o do MODELO RDF para a sintaxe padr�o W3C RDF/XML
		// model.write(System.out);
		// model.write(System.out, "RDF/XML");

		// Serializa��o do MODELO RDF para uma sintaxe padr�o W3C RDF/XML
		// alternativa
		// O atributo rdf:parseType="Resource" permite referenciar BLANK NODES
		// sem o uso do par <rdf:Description></rdf:Description>
		// model.write(System.out, "RDF/XML-ABBREV");

		// Serializa��o do MODELO RDF para a sintaxe N-TRIPLES
		// model.write(System.out, "N-TRIPLE");

		// Serializa��o do MODELO RDF para a sintaxe TURTLE
		// Os caracteres [ e ] servem para identificar BLANK NODES (tamb�m podem
		// ser escritos como _:nodeID)
		model.write(System.out, "TURTLE");
	}
}
