package app2.controller;


import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;


public class QueryController {
	
	/**
	 * Retorna todas as pessoas e respectivos nomes
	 * @param model modelo RDF
	 *
	 */
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
	
	/**
	 * Retorna areas
	 * @param model modelo RDF
	 *
	 */
	public static void getArea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "SELECT DISTINCT ?area "
				+ "WHERE "
				+ "{"
				+ "?area rdf:type ?acm . "
				+ "FILTER regex(str(?area), 'http://linkserver.icmc.usp.br/ckonto/acm', 'i')"
				+ "}";
		System.out.println("Area: ");
		queryModel(query, model);
		System.out.println();
	}

	/**
	 * Retorna subareas
	 * @param model modelo RDF
	 *
	 */
	public static void getSubarea(Model model) {
		String query;
		query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "SELECT ?subarea "
				+ "WHERE "
				+ "{"
				+ "?uri rdfs:subClassOf ?a . "
				+ "?uri rdfs:label ?subarea . "
				+ "}"
				+ "ORDER BY ?subarea";
		System.out.println("Subareas: ");
		queryModel(query, model);
		System.out.println();
	}

	/**
	 * Retorna pessoas sugeridas por area
	 * @param model modelo RDF
	 *
	 */
	public static void suggestPeopleArea(Model model) {
		String query;
		query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#>"
				+ "SELECT ?person1 ?person2 ?area "
				+ "WHERE "
				+ "{"
				+ "?person1 acm:hasKnowledgeOf ?area . "
				+ "?person2 acm:hasKnowledgeOf ?area . "
				+ "FILTER ("
				+ "?person1 != ?person2"
				+ ")"
				+ "}";
		//order by areas
		System.out.println("Recomendacao de pessoas por areas: ");
		queryModel(query, model);
		System.out.println();
	}

	/**
	 * Retorna pessoas sugeridas por subarea
	 * @param model modelo RDF
	 *
	 */
	public static void suggestPeopleSubarea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>  "
				+ "SELECT DISTINCT ?person ?area ?indicatedPerson ?indicatedArea "
				+ "WHERE "
				+ "{"
				+ "?person rdf:type foaf:Person . "
				+ "?person acm:hasKnowledgeOf ?area . "
				+ "?area rdfs:subClassOf ?indicatedArea ."
				+ "?indicatedPerson acm:hasKnowledgeOf ?indicatedArea . "
				+ "FILTER ("
				+ "?area != ?indicatedArea"
				+ ")"
				+ "}";
		System.out.println("Recomendacao de pessoas por subareas: ");
		queryModel(query, model);
		System.out.println();
	}

	/**
	 * Retorna pessoas sugeridas por area especifica
	 * @param model modelo RDF
	 *
	 */
	public static void suggestPeopleSpecificArea(Model model) {
		String query;
		query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#>"
				+ "SELECT ?person "
				+ "WHERE "
				+ "{"
				+ "acm:E_2_2_Object_Representation rdf:type ?relatedArea . "
				+ "?person acm:hasKnowledgeOf ?relatedArea . "
				+ "}";
		System.out.println("Recomendacao de pessoas por area especifica: E_2_2_Object_Representation ");
		queryModel(query, model);
		System.out.println();
	}
	
	/**
	 * Executa query com modelo recebido e imprime o resultado
	 * @param queryString string com a query em SPARQL
	 * @param model modelo RDF
	 *
	 */
	public static void queryModel(String queryString, Model m) {

		// Criacao de um objeto Query com a string de consulta na linguagem SPARQL
		Query query = QueryFactory.create(queryString);

		// Interface para a execucao de uma unica consulta sobre os GRAFOS retornados do BD
		QueryExecution qe = QueryExecutionFactory.create(query, m);

		// Executa a consulta definida na string SPARQL e armazena em um ResultSet
		ResultSet results = qe.execSelect();

		// Formatador dos resultados de uma consulta
		ResultSetFormatter.out(results);
	}
		
}
