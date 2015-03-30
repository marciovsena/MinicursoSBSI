package app2.controller;


import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;


public class QueryController {
	
	public static void getPerson(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "SELECT DISTINCT ?person ?name " 
				+ "WHERE "
				+ "{"
				+ "?person rdf:type foaf:Person ."
				+ "?person foaf:firstName ?name ."
				+ "}";
		System.out.println("Foaf-Person: ");
		queryModel(query, model);
		System.out.println();
	}
	
	public static void getAreas(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "SELECT DISTINCT ?uri " 
				+ "WHERE "
				+ "{"
				+ "?uri rdf:type ?acm ."
				+ "FILTER regex(str(?uri), 'http://linkserver.icmc.usp.br/ckonto/acm', 'i')"
				+ "}";
		System.out.println("Áreas: ");
		queryModel(query, model);
		System.out.println();
	}
	
	public static void queryModel(String queryString, Model m) {

		// Criação de um objeto Query com a string de consulta na linguagem SPARQL
		Query query = QueryFactory.create(queryString);

		// Interface para a execução de uma única consulta sobre os GRAFOS retornados do BD
		QueryExecution qe = QueryExecutionFactory.create(query, m);

		// Executa a consulta definida na string SPARQL e armazena em um ResultSet
		ResultSet results = qe.execSelect();

		// Formatador dos resultados de uma consulta
		ResultSetFormatter.out(results);
	}
		
}
