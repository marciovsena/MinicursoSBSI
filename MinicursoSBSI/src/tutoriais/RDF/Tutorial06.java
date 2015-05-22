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
import java.io.InputStreamReader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.VCARD;

/**
 * Tutorial 6 - Navegando em um MODELO RDF (RECURSOS, PROPRIEDADES e VALORES DE
 * PROPRIEDADES) a partir de uma URI conhecida
 */
public class Tutorial06 extends Object {

	// Caminho do arquivo de entrada no projeto Java corrente
	static final String inputFileName = "tutoriais/RDF/vc-db-1.rdf";

	// Cadeia de caracteres que sera utilizada como URI de um RECURSO
	static final String johnSmithURI = "http://somewhere/JohnSmith/";

	public static void main(String args[]) {

		// Criaaao de um MODELO RDF vazio
		Model model = ModelFactory.createDefaultModel();

		// Uso da classe Java FILEMANAGER para localizar o arquivo de entrada
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
			throw new IllegalArgumentException("File: " + inputFileName
					+ " not found");
		}

		// Leitura do arquivo RDF de entrada na sintaxe padrao RDF/XML
		model.read(new InputStreamReader(in), "");

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Dada a URI de um RECURSO, este pode ser recuperado de um MODELO RDF
		// usando o matodo getResource(String uri)
		Resource vcard = model.getResource(johnSmithURI);

		// Recupera o VALOR de uma PROPRIEDADE como RECURSO via matodos
		// getRequiredProperty(Property p) e getResource()
		Resource name = vcard.getRequiredProperty(VCARD.N).getResource();

		// Recupera o VALOR de uma PROPRIEDADE como LITERAL via matodos
		// getRequiredProperty(Property p) e getLiteral()
		String fullName = vcard.getRequiredProperty(VCARD.FN).getString();

		// O retorno dos matodos get() a o conjunto de TRIPLAS que "casa" com a
		// TRIPLA de entrada da consulta

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Adicao de mais duas TRIPLAS com a mesma PROPRIEDADE e RECURSO: RDF
		// permite isso!
		// ATENCAO: adicionar triplas com o mesmo SUJEITO, PREDICADO e OBJETO
		// nao tem efeito, segundo a teoria dos GRAFOS
		vcard.addProperty(VCARD.NICKNAME, "Smithy").addProperty(VCARD.NICKNAME,
				"Adji Man");

		// Configurando a saada da consulta
		System.out.println("The nicknames of \"" + fullName + "\" are:");

		// Retorna todas as TRIPLAS que "casam" com a TRIPLA de entrada
		// O metodo listProperties() permite recuperar TODOS os VALORES de
		// PROPRIEDADES associados a um PROPRIEDADE de um RECURSO
		StmtIterator iter = vcard.listProperties(VCARD.NICKNAME);
		while (iter.hasNext()) {
			System.out.println("    "
					+ iter.nextStatement().getObject().toString());
		}
		
		model.write(System.out, "TURTLE");
	}
}