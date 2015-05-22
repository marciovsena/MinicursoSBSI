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

public class Tutorial25 {

	public static void main(String args[]) {

		// Retorna um grafo RDF copiando apenas os recursos que tem idade
		// declarada
		String query = "PREFIX info:    <http://somewhere/peopleInfo#> "
				+ "PREFIX vcard:      <http://www.w3.org/2001/vcard-rdf/3.0#>"
				+ "CONSTRUCT " + "{ ?suj   ?pred1  ?bnode ;"
				+ "         info:age  ?age ." + "  ?bnode ?pred2  ?obj ." + "}"
				+ "WHERE " + "{ ?suj   info:age  ?age ;"
				+ "         ?pred1    ?bnode ." + "  ?bnode ?pred2    ?obj"
				+ "}";

		/*
		 * querySPARQL - A string de consulta na linguagem SPARQL file = Numero
		 * relativo ao arquivo contendo dados RDF
		 */

		// Fonte de dados: 3 = vc-db-3.rdf

		// TESTE AS DEMAIS FONTES DE DADOS:
		// Fonte de dados: 4 = vc-db-4.rdf
		// Fonte de dados: 5 = vc-db-5.rdf
		// Fonte de dados: 6 = vc-db-6.rdf
		queryModel(query, 3);
	}

	public static void queryModel(String querySPARQL, int file) {

		String inputFileName = null;

		if (file == 3) {
			inputFileName = "tutoriais/RDF/vc-db-3.rdf";
		} else if (file == 4) {
			inputFileName = "tutoriais/RDF/vc-db-4.rdf";
		} else if (file == 5) {
			inputFileName = "tutoriais/RDF/vc-db-5.rdf";
		} else if (file == 6) {
			inputFileName = "tutoriais/RDF/vc-db-6.rdf";
		} else {
			throw new IllegalArgumentException("Arquivo: " + inputFileName
					+ " nao encontrado!");
		}

		// Criacao de um MODELO RDF vazio
		Model model = ModelFactory.createDefaultModel();

		// Uso da classe Java FILEMANAGER para localizar o arquivo de entrada
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
			throw new IllegalArgumentException("Arquivo: " + inputFileName
					+ " nao encontrado");
		}

		// Leitura do arquivo RDF de entrada na sintaxe padrao RDF/XML
		model.read(in, "");

		// Criacao de um objeto Query com a string de consulta na linguagem
		// SPARQL
		Query query = QueryFactory.create(querySPARQL);

		// Interface para a execucao de uma unica consulta sobre o GRAFO do
		// arquivo
		QueryExecution qe = QueryExecutionFactory.create(query, model);

		// Metodo execConstruct() executa consultas CONSTRUCT e retorna um grafo
		// RDF
		Model resultModel = qe.execConstruct();
		qe.close();

		resultModel.write(System.out, "TURTLE");
	}
}