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

/** Apresenta��o de cada pacote importado do Apache Jena
 *  1. Interfaces Model e Resource
 *  2. Classes ModelFactory e VCARD
 *  3. Apresenta��o r�pida do padr�o VCARD (p�gina VCARD_em_RDF_XML.html) 
 */

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;

/**
 * Tutorial 1 - Cria��o de um MODELO RDF simples
 */

public class Tutorial01 extends Object {

	// String para cria��o de URI de RECURSO
	static String personURI = "http://somewhere/JohnSmith";

	// String para cria��o de um VALOR DE PROPRIEDADE do tipo LITERAL
	static String fullName = "John Smith";

	public static void main(String args[]) {

		// Cria��o de um MODELO RDF vazio
		Model model = ModelFactory.createDefaultModel();

		// Cria��o de um RECURSO e associa��o do mesmo ao MODELO RDF
		// rec�m-criado
		Resource johnSmith = model.createResource(personURI);

		// Cria��o de uma PROPRIEDADE com associa��o ao respectivo VALOR DE
		// PROPRIEDADE (LITERAL)
		// Associa��o dessa PROPRIEDADE ao RECURSO rec�m-criado
		johnSmith.addProperty(VCARD.FN, fullName);

		// Escrita do MODELO RDF na sintaxe padr�o W3C RDF/XML
		model.write(System.out);
	}
}