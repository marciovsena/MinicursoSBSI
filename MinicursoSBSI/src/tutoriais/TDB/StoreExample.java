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

package tutoriais.TDB;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;

import java.io.*;

/**
 * Armazenando dados RDF de um arquivo em um BD
 */
public class StoreExample {

	// Caminho do arquivo de entrada no projeto Java corrente
	static final String inputFileName = "tutoriais/RDF/vc-db-2.rdf";

	public static void main(String args[]) {

		// Cria��o de um MODELO RDF vazio em mem�ria
		Model model = ModelFactory.createDefaultModel();

		// Uso da classe Java FILEMANAGER para localizar o arquivo de entrada
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
			throw new IllegalArgumentException("File: " + inputFileName
					+ " not found");
		}

		// Leitura do arquivo RDF de entrada na sintaxe padr�o RDF/XML
		model.read(in, "");
		
		TDB_TripleStore tdb = new TDB_TripleStore();
		tdb.update(model);
		
	    //Criação de String de consulta na sintaxe da linguagem SPARQL
		 String queryString = "SELECT ?s ?p ?o " +
				 "WHERE {?s ?p ?o}";

		tdb.consultar(queryString);
	}
}
