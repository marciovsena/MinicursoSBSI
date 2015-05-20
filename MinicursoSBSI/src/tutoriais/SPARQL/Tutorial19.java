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

package tutoriais.SPARQL;

import java.io.InputStream;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class Tutorial19 {

	public static void main(String args[]) {

		// Retorna a soma das idades dos recursos
		String query = "PREFIX info:    <http://somewhere/peopleInfo#> "
				+ "PREFIX vcard:   <http://www.w3.org/2001/vcard-rdf/3.0#> "
				+ "SELECT (SUM(?age) AS ?total) " + "WHERE "
				+ "{ ?person info:age  ?age . }";

		/*
		 * querySPARQL - A string de consulta na linguagem SPARQL usarResultSet
		 * - Imprimir resultados da consulta na forma tabular (true) ou em
		 * RDF/XML padr�o (false) file = N�mero relativo ao arquivo contendo
		 * dados RDF
		 */

		// Fonte de dados: 2 = vc-db-2.rdf
		// Testar com arquivo de dados 2, 5 ou 6 ...
		queryModel(query, true, 5);
	}

	public static void queryModel(String querySPARQL, boolean usarResultSet,
			int file) {

		String inputFileName = null;

		if (file == 2) {
			inputFileName = "br/ufg/inf/rdf/vc-db-2.rdf";
		} else if (file == 5) {
			inputFileName = "br/ufg/inf/rdf/vc-db-5.rdf";
		} else if (file == 6) {
			inputFileName = "br/ufg/inf/rdf/vc-db-6.rdf";
		} else {
			throw new IllegalArgumentException("Arquivo: " + inputFileName
					+ " n�o encontrado!");
		}

		// Cria��o de um MODELO RDF vazio
		Model model = ModelFactory.createDefaultModel();

		// Uso da classe Java FILEMANAGER para localizar o arquivo de entrada
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
			throw new IllegalArgumentException("Arquivo: " + inputFileName
					+ " nao encontrado");
		}

		// Leitura do arquivo RDF de entrada na sintaxe padr�o RDF/XML
		model.read(in, "");

		// Cria��o de um objeto Query com a string de consulta na linguagem
		// SPARQL
		Query query = QueryFactory.create(querySPARQL);

		// Interface para a execu��o de uma �nica consulta sobre o GRAFO do
		// arquivo
		QueryExecution qe = QueryExecutionFactory.create(query, model);

		if (usarResultSet) {
			// Executa a consulta definida na string SPARQL e armazena em um
			// ResultSet
			ResultSet results = qe.execSelect();

			// Formatador dos resultados de uma consulta
			ResultSetFormatter.out(System.out, results, query);
		} else {

			// Escrita do MODELO RDF na sintaxe padr�o W3C RDF/XML
			model.write(System.out);
		}
	}
}