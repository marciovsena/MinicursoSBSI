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

package br.ufg.inf.SPARQL;

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

public class TutorialX {

	public static void main(String args[]) {

		// Busca todas as triplas do BD, caso n�o seja especificado um grafo em
		// espec�fico
		String query = "Select ?s ?p ?o where {?s ?p ?o}";

		// Busca todos os Recursos que tenham a propriedade FN com valor John
		// Smith
		// Fonte de dados: vc-db-1.rdf
		String q1 = "SELECT ?x "
				+ "WHERE { ?x  <http://www.w3.org/2001/vcard-rdf/3.0#FN>  'John Smith'}";

		// Busca todos os Recursos que tenham a propriedade FN
		// Fonte de dados: vc-db-1.rdf
		String qbp1 = "SELECT ?x ?name " + "WHERE "
				+ "{ ?x <http://www.w3.org/2001/vcard-rdf/3.0#FN> ?name }";

		// Utilizando a mesma variavel para o 'casar' das triplas
		// Fonte de dados: vc-db-1.rdf
		String qbp2 = "SELECT ?givenName "
				+ "WHERE "
				+ "{ ?y  <http://www.w3.org/2001/vcard-rdf/3.0#Family>  'Smith' . "
				+ " ?y  <http://www.w3.org/2001/vcard-rdf/3.0#Given>  ?givenName ."
				+ "}";

		// Utilizando a cl�usula PREFIX
		// Fonte de dados: vc-db-1.rdf
		String qbp3 = "PREFIX vcard:      <http://www.w3.org/2001/vcard-rdf/3.0#>"
				+ "SELECT ?givenName "
				+ "WHERE "
				+ "{ ?y  vcard:Family  'Smith' . "
				+ " ?y  vcard:Given  ?givenName ." + "}";

		// Buscando os n�s em branco
		// Fonte de dados: vc-db-1.rdf
		String qbp4 = "PREFIX vcard:      <http://www.w3.org/2001/vcard-rdf/3.0#>"
				+ "SELECT ?y ?givenName "
				+ "WHERE "
				+ "{ ?y  vcard:Family  'Smith' . "
				+ " ?y  vcard:Given  ?givenName ." + "}";

		// Casamento de Strings, semelhante ao LIKE do SQL
		// Sintaxe: FILTER regex(?x, "pattern" [, "flags"])
		// Traducao: filtre os valores para ?g que tenham r ou R. A flag "i"
		// significa casamento de padrão case-insensitivo.
		// Fonte de dados: vc-db-2.rdf
		String qbp5 = "PREFIX vcard:      <http://www.w3.org/2001/vcard-rdf/3.0#>"
				+ "SELECT ?g "
				+ "WHERE "
				+ "{ ?y vcard:Given ?g ."
				+ " FILTER regex(?g, 'r', 'i') }";

		// Casamento de Strings, semelhante ao LIKE do SQL
		// Sintaxe: FILTER (clausula)
		// Traducao: filtre as idades maiores ou igual que 24
		// Fonte de dados: vc-db-2.rdf
		String qbp6 = "PREFIX info: <http://somewhere/peopleInfo#>"
				+ "SELECT ?resource " + "WHERE "
				+ "{ ?resource info:age ?age ." + "  FILTER (?age >= 24) }";

		// Busca os nomes completos e as idades caso tenha
		// Fonte de dados: vc-db-2.rdf
		String qopt1 = "PREFIX info:    <http://somewhere/peopleInfo#> "
				+ "PREFIX vcard:   <http://www.w3.org/2001/vcard-rdf/3.0#> "
				+ "SELECT ?name ?age " + "WHERE "
				+ "{ ?person vcard:FN  ?name ."
				+ "  OPTIONAL { ?person info:age ?age }" + "}";

		// Busca os nomes completos que tenha idade
		// Fonte de dados: vc-db-2.rdf
		String qopt2 = "PREFIX info:    <http://somewhere/peopleInfo#> "
				+ "PREFIX vcard:   <http://www.w3.org/2001/vcard-rdf/3.0#> "
				+ "SELECT ?name ?age " + "WHERE "
				+ "{ ?person vcard:FN  ?name ." + "  ?person info:age ?age ."
				+ "}";

		// Busca os nomes completos e mostra a idade se for maior que 24
		// Fonte de dados: vc-db-2.rdf
		String qopt3 = "PREFIX info:    <http://somewhere/peopleInfo#> "
				+ "PREFIX vcard:   <http://www.w3.org/2001/vcard-rdf/3.0#> "
				+ "SELECT ?name ?age " + "WHERE "
				+ "{ ?person vcard:FN  ?name ."
				+ "  OPTIONAL { ?person info:age ?age . FILTER ( ?age > 24 ) }"
				+ "}";

		// Busca os nomes completos e as idades, caso tenha
		// Fonte de dados: vc-db-2.rdf
		String qlimit1 = "PREFIX info:    <http://somewhere/peopleInfo#> "
				+ "PREFIX vcard:   <http://www.w3.org/2001/vcard-rdf/3.0#> "
				+ "SELECT ?name " + "WHERE " + "{ ?person vcard:FN  ?name ."
				+ "} LIMIT 1";

		// Busca os nomes completos e as idades caso tenha
		// Fonte de dados: vc-db-3.rdf
		String qorderby = "PREFIX info:    <http://somewhere/peopleInfo#> "
				+ "PREFIX vcard:   <http://www.w3.org/2001/vcard-rdf/3.0#> "
				+ "SELECT ?name ?age " + "WHERE "
				+ "{ ?person vcard:FN  ?name ." + "  ?person info:age ?age ."
				+ "} ORDER BY desc(?age)";

		// Imprime apenas um resultado se tiver semelhante
		// Fonte de dados: vc-db-4.rdf
		String qdistinct = "PREFIX info:    <http://somewhere/peopleInfo#> "
				+ "PREFIX vcard:  <http://www.w3.org/2001/vcard-rdf/3.0#>"
				+ "PREFIX  foaf: 	<http://xmlns.com/foaf/0.1/>"
				+ "SELECT distinct ?name " + "WHERE " + "{ ?x vcard:FN ?name }";

		/*
		 * querySPARQL - a consulta em SPARQL usarResultSet - imprimir
		 * tabela(true) ou rdf/xml(false) file = numero do arquivo
		 */
		queryModel(query, false, 4);
	}

	public static void queryModel(String querySPARQL, boolean usarResultSet,
			int file) {
		String inputFileName = null;

		if (file == 1) {
			inputFileName = "br/ufg/impl/vc-db-1.rdf";
		} else if (file == 2) {
			inputFileName = "br/ufg/impl/vc-db-2.rdf";
		} else if (file == 3) {
			inputFileName = "br/ufg/impl/vc-db-3.rdf";
		} else if (file == 4) {
			inputFileName = "br/ufg/impl/vc-db-4.rdf";
		} else {
			throw new IllegalArgumentException("Arquivo: " + inputFileName
					+ " nao encontrado");
		}

		// criando um model padrao
		Model model = ModelFactory.createDefaultModel();

		// lendo arquivo
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
			throw new IllegalArgumentException("Arquivo: " + inputFileName
					+ " nao encontrado");
		}
		model.read(in, "");

		Query query = QueryFactory.create(querySPARQL);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		if (usarResultSet) {
			ResultSet results = qe.execSelect();
			ResultSetFormatter.out(System.out, results, query);
		} else {
			model.write(System.out);
		}
	}
}