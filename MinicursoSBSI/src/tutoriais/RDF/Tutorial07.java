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
 * Tutorial 9 - Opera��es sobre Grafos
 */
public class Tutorial07 extends Object {

	// Caminho dos arquivos de entrada no projeto Java corrente
	static final String inputFileName1 = "br/ufg/inf/rdf/vc-db-3.rdf";
	static final String inputFileName2 = "br/ufg/inf/rdf/vc-db-4.rdf";

	public static void main(String args[]) {

		// Cria��o de dois MODELOS / GRAFOS RDF vazios
		Model model1 = ModelFactory.createDefaultModel();
		Model model2 = ModelFactory.createDefaultModel();

		// Uso da classe Java FILEMANAGER para localizar os dois arquivos de
		// entrada
		InputStream in1 = FileManager.get().open(inputFileName1);
		if (in1 == null) {
			throw new IllegalArgumentException("File: " + inputFileName1
					+ " not found");
		}
		InputStream in2 = FileManager.get().open(inputFileName2);
		if (in2 == null) {
			throw new IllegalArgumentException("File: " + inputFileName2
					+ " not found");
		}

		// Leitura dos dois arquivos RDF de entrada na sintaxe padr�o RDF/XML
		model1.read(in1, "");
		model2.read(in2, "");

		// Uni�o dos dois GRAFOS
		Model union = model1.union(model2);

		// Intersec��o dos dois GRAFOS
		// Model inter = model1.intersection(model2);

		// Diferen�a do GRAFO 1 para o GRAFO 2 (o que o GRAFO 1 tem que o GRAFO
		// 2 n�o tem)
		//Model diff = model2.difference(model1);

		// Serializa��o do MODELO RDF resultante para a sintaxe N-TRIPLE
		union.write(System.out, "TURTLE");
		System.out.println();
	}
}