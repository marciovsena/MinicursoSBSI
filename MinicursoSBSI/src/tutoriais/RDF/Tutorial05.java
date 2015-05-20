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
import com.hp.hpl.jena.util.FileManager;

/**
 * Tutorial 5 - Leitura de arquivo de entrada na sintaxe RDF/XML e escrita do
 * mesmo na sa�da padr�o
 */
public class Tutorial05 extends Object {

	// Caminho do arquivo de entrada no projeto Java corrente
	static final String inputFileName = "br/ufg/inf/rdf/bus.ttl";

	public static void main(String args[]) {

		// Cria��o de um MODELO RDF vazio
		Model model = ModelFactory.createDefaultModel();

		// Uso da classe Java FileManager para localizar o arquivo de entrada
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
			throw new IllegalArgumentException("File: " + inputFileName
					+ " not found");
		}

		// Leitura do arquivo RDF de entrada
		// O segundo par�metro inclui uma URI utilizada para resolver URIs
		// relativas
		model.read(in, "");

		// Escrita do MODELO RDF para a sa�da padr�o (usa a sintaxe W3C RDF/XML)
		model.write(System.out);

		// VAMOS ESCREVER ESSE GRAFO???
	}
}