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

import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;

/**
 * Tutorial 5.5 - Controle de PREFIXOS associados a XML NAMESPACES
 */
public class Tutorial0505 extends Object {

	public static void main(String args[]) {

		// Cria��o de um MODELO RDF vazio
		Model m = ModelFactory.createDefaultModel();

		// Defini��o de strings que representar�o URIs associadas a XML
		// NAMESPACES
		String namespaceA = "http://www.w3.org/else#";
		String namespaceB = "http://www.ieee.org/else#";

		// Cria��o de um RECURSO e sua associa��o ao MODELO RDF rec�m-criado
		// A URI completa do RECURSO � a concatena��o da URI de seu NAMESPACE
		// com o nome do RECURSO
		Resource root = m.createResource(namespaceA + "root");

		// Cria��o e associa��o de PROPRIEDADES ao MODELO RDF
		// As URIs completas das PROPRIEDADES s�o a concatena��o das URIs de
		// seus NAMESPACES com seus respectivos nomes
		Property P = m.createProperty(namespaceA + "P");
		Property Q = m.createProperty(namespaceB + "Q");

		// Idem acima
		Resource x = m.createResource(namespaceA + "x");
		Resource y = m.createResource(namespaceA + "y");
		Resource z = m.createResource(namespaceA + "z");

		// Cria��o das TRIPLAS associando os RECURSOS, PROPRIEDADES e VALORES DE
		// PROPRIEDADES (outros RECURSOS, e n�o LITERAIS)
		m.add(root, P, x).add(root, P, y).add(y, Q, z);

		/*
		 * System.out.println(
		 * "# -- Aqui n�o h� nenhum prefixo definido -- o Jena cria seus pr�prios prefixos"
		 * ); m.write( System.out );
		 */
		System.out
				.println("# -- Cria��o de prefixo para o ESPA�O DE NOMES XML da URI namespaceA");
		m.setNsPrefix("nsA", namespaceA);
		m.write(System.out);

		System.out
				.println("# -- Cria��o de prefixo para o ESPA�O DE NOMES XML da URI namespaceB");
		m.setNsPrefix("nsB", namespaceB);
		m.write(System.out);
	}
}