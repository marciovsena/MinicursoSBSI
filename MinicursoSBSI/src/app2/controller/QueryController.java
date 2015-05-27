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
				+ "?person rdf:type foaf:Person . "
				+ "?person foaf:firstName ?name . "
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
				+ "?uri rdf:type ?acm . "
				+ "FILTER regex(str(?uri), 'http://linkserver.icmc.usp.br/ckonto/acm', 'i')"
				+ "}";
		System.out.println("Áreas: ");
		queryModel(query, model);
		System.out.println();
	}

	public static void getSubAreas(Model model) {
		String query;
		query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "SELECT DISTINCT ?subareaLabel ?areaLabel "
				+ "WHERE "
				+ "{"
				+ "?uri rdfs:subClassOf ?area . "
				+ "?uri rdfs:label ?subareaLabel . "
				+ "?area rdfs:label ?areaLabel "
				+ "}";
		//order by areas
		System.out.println("Subareas: ");
		queryModel(query, model);
		System.out.println();
	}

	public static void sugerirPessoasArea(Model model) {
		String query;
		query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#>"
				+ "SELECT ?p1 ?p2 ?area "
				+ "WHERE "
				+ "{"
				+ "?p1 acm:hasKnowledgeOf ?area . "
				+ "?p2 acm:hasKnowledgeOf ?area . "
				+ "FILTER ("
				+ "?p1 != ?p2"
				+ ")"
				+ "}";
		//order by areas
		System.out.println("Recomendação de pessoas por áreas: ");
		queryModel(query, model);
		System.out.println();
	}

	public static void sugerirPessoasSubarea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>  "
				+ "SELECT DISTINCT ?pessoa ?area ?pessoaIndicada ?areaPessoaIndicada "
				+ "WHERE "
				+ "{"
				+ "?pessoa rdf:type foaf:Person . "
				+ "?pessoa acm:hasKnowledgeOf ?area . "
				+ "?area rdfs:subClassOf ?areaPessoaIndicada ."
				+ "?a1 rdf:type owl:Class . "
				+ "?pessoaIndicada acm:hasKnowledgeOf ?areaPessoaIndicada . "
				+ "FILTER ("
				+ "?area != ?areaPessoaIndicada"
				+ ")"
				+ "}";
		System.out.println("Recomendação de pessoas por subareas: ");
		queryModel(query, model);
		System.out.println();
	}

	public static void sugerirPessoasAreaEspecifica(Model model) {
		String query;
		query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#>"
				+ "SELECT DISTINCT ?pessoa "
				+ "WHERE "
				+ "{"
				+ "?pessoa acm:hasKnowledgeOf acm:B_1_4_0_Languages_And_Compilers . "
				+ "}";
		//order by areas
		System.out.println("Recomendação de pessoas por área específica: B_1_4_0_Languages_And_Compilers ");
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
