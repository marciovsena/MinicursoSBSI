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
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.VCARD;

/**
 * Tutorial 7 - Consultas em MODELOS RDF com a API RDF (selecionando RECURSOS
 * VCARD) sem uma URI conhecida
 */
public class Tutorial07 extends Object {

	// Caminho do arquivo de entrada no projeto Java corrente
	static final String inputFileName = "br/ufg/inf/rdf/vc-db-1.rdf";

	public static void main(String args[]) {

		// Cria��o de um MODELO RDF vazio
		Model model = ModelFactory.createDefaultModel();

		// Uso da classe Java FILEMANAGER para localizar o arquivo de entrada
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
			throw new IllegalArgumentException("File: " + inputFileName
					+ " not found");
		}

		// Leitura do arquivo RDF de entrada na sintaxe padr�o RDF/XML
		model.read(new InputStreamReader(in), "");

		/*
		 * O m�todo Model.listStatements() lista todos as senten�as de um MODELO
		 * / GRAFO, o que n�o seria recomendado em modelos muito grandes.
		 */

		// Retorna em um Iterator todos os RECURSOS que possuem a PROPRIEDADE FN
		// da ontologia VCARD
		ResIterator iter = model.listResourcesWithProperty(VCARD.FN);
		if (iter.hasNext()) {
			System.out.println("The database contains vcards for:");
			while (iter.hasNext()) {

				// Obt�m os OBJETOS do tipo LITERAL de uma PROPRIEDADE
				System.out.println("  "
						+ iter.nextResource().getRequiredProperty(VCARD.FN)
								.getString());
			}
		} else {
			System.out.println("No vcards were found in the database");
		}
	}
}