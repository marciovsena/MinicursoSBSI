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
import com.hp.hpl.jena.vocabulary.RDFS;

/**
 * Tutorial 11 - Manipulando LITERAIS
 */
public class Tutorial08 extends Object {

	public static void main(String args[]) {

		// Criacao de um MODELO RDF vazio
		Model model = ModelFactory.createDefaultModel();

		// Criacao de um RECURSO ANONIMO (ou BLANK NODE), pois nao tem URI
		// passada como parametro
		Resource r = model.createResource();

		/*
		 * Adicao de PROPRIEDADES ao RECURSO ANONIMO com os respectivos VALORES
		 * DE PROPRIEDADES como LITERAIS
		 * 
		 * O metodo createLiteral() permite atribuir ao LITERAL criado o seu
		 * respectivo idioma
		 * 
		 * A propriedade RDFS.label definida na linguagem RDF Schema, padrao
		 * para construcao de ontologias na Web, e utilizada para fornecer uma
		 * uma versao legivel para humanos a respeito do nome de um RECURSO
		 * qualquer
		 * 
		 * Existem LITERAIS sem tipagem (ou seja, strings implecitas), com
		 * tipagem (associando o tipo de dado do XML SCHEMA) e podem existir
		 * literais que representam elementos XML
		 * 
		 * O atributo rdf:parseType="Literal" e aplicado a OBJETOS que possuem
		 * LITERAIS com elementos XML
		 */
		r.addProperty(RDFS.label, model.createLiteral("chat", "en"))
				.addProperty(RDFS.label, model.createLiteral("chat", "fr"))
				.addProperty(RDFS.label,
						model.createLiteral("<em>chat</em>", true));

		// Serializa o modelo na sintaxe TURTLE
		model.write(System.out, "TURTLE");
		System.out.println();

		// Criacao de novo MODELO RDF
		model = ModelFactory.createDefaultModel();

		// Criacao de um RECURSO ANONIMO
		r = model.createResource();

		// Adicao de duas PROPRIEDADES com o mesmo VALOR
		r.addProperty(RDFS.label, "15").addLiteral(RDFS.label, 12.5);

		// Serializa o novo modelo na sintaxe TURTLE
		model.write(System.out, "TURTLE");
	}
}