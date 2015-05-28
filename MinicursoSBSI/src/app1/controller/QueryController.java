package app1.controller;


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
				+ "?person rdf:type foaf:Person ."
				+ "?person foaf:firstName ?name ."
				+ "}";
		System.out.println("Pessoas: ");
		queryModel(query, model);
		System.out.println();
	}

	/**
	 * Retorna todos os documentos e respectivos titulos
	 * @param model modelo RDF
	 *
	 */
	public static void getDocument(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "SELECT DISTINCT ?document ?title "
				+ "WHERE "
				+ "{"
				+ "?document rdf:type foaf:Document ."
				+ "?document foaf:title ?title ."
				+ "}";
		System.out.println("Artigos: ");
		queryModel(query, model);
		System.out.println();
	}

	/**
	 * Retorna todos os autores e seus artigos
	 * @param model modelo RDF
	 *
	 */
	public static void getActor(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "SELECT DISTINCT ?actor ?article "
				+ "WHERE "
				+ "{"
				+ "?article rdf:type foaf:Document ."
				+ "?article foaf:made ?actor ."
				+ "}";
		System.out.println("Atores e artigos");
		queryModel(query, model);
		System.out.println();
	}

	/**
	 * Retorna todos os revisores e suas areas
	 * @param model modelo RDF
	 *
	 */
	public static void getReviewersArea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "SELECT DISTINCT ?reviewers ?area "
				+ "WHERE " 
				+ "{"
				+ "?reviewers rdf:type foaf:Person ."
				+ "?reviewers acm:hasKnowledgeOf ?area ."
				+ "}";
		System.out.println("Revisores e area: ");
		queryModel(query, model);
		System.out.println();
	}

	/**
	 * Retorna artigos e suas areas
	 * @param model modelo RDF
	 *
	 */
	public static void getArticleByArea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "SELECT DISTINCT ?article ?area "
				+ "WHERE " 
				+ "{"
				+ "?article rdf:type foaf:Document ."
				+ "?article foaf:primaryTopic ?area ."
				+ "?reviewers acm:hasKnowledgeOf ?area ."
				+ "}";
		System.out.println("Artigos / Area: ");
		queryModel(query, model);
		System.out.println();
	}

	/**
	 * Retorna areas e suas subareas
	 * @param model modelo RDF
	 *
	 */
	public static void getSubarea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "SELECT DISTINCT ?area ?subarea "
				+ "WHERE " 
				+ "{"
				+ "?article rdf:type foaf:Document ."
				+ "?article foaf:primaryTopic ?area ."
				+ "?subarea rdfs:subClassOf ?area ."
				+ "FILTER ("
				+ "?subarea != ?area"
				+ ")"
				+ "}";
		System.out.println("Area e subarea de artigos existentes: ");
		queryModel(query, model);
		System.out.println();
	}

	/**
	 * Retorna artigos e revisores sugeridos
	 * @param model modelo RDF
	 *
	 */
	public static void suggestReviewersArea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "SELECT DISTINCT ?article ?reviewers "
				+ "WHERE " 
				+ "{"
				+ "?article rdf:type foaf:Document . "
				+ "?article foaf:primaryTopic ?area . "
				+ "?reviewers acm:hasKnowledgeOf ?area . "
				+ "}";
		System.out.println("(Prioridade 1) Recomendacao de revisores: artigos por area");
		queryModel(query, model);
		System.out.println();
	}

	/**
	 * Retorna artigos, revisores e subareas
	 * @param model modelo RDF
	 *
	 */
	public static void suggestReviewersSubarea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "SELECT DISTINCT ?article ?reviewers ?subarea "
				+ "WHERE " 
				+ "{"
				+ "?article rdf:type foaf:Document . "
				+ "?article foaf:primaryTopic ?area . "
				+ "?subArea rdfs:subClassOf ?area ."
				+ "?reviewers acm:hasKnowledgeOf ?subarea ."
				+ "FILTER ("
				+ "?subarea != ?area"
				+ ")"
				+ "}";
		System.out.println("(Prioridade 2) Recomendacao de revisores: artigos por subarea");
		queryModel(query, model);
		System.out.println();
	}

	/**
	 * Retorna sugestao de revisores por area
	 * @param model modelo RDF
	 *
	 */
	public static void suggestReviewersForArticleByArea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "SELECT DISTINCT ?article ?reviewers "
				+ "WHERE " 
				+ "{"
				+ "?article rdf:type foaf:Document . "
				+ "?article foaf:title 'Linguagem de Software para Compiladores' . "
				+ "?article foaf:primaryTopic ?area . "
				+ "?reviewers acm:hasKnowledgeOf ?area . "
				+ "}";
		System.out.println("Recomendacao de revisores para artigos de area: Linguagem de Software para Compiladores");
		queryModel(query, model);
		System.out.println();
	}

	/**
	 * Retorna sugestao de revisores por subarea
	 * @param model modelo RDF
	 *
	 */
	public static void suggestReviewersForArticleBySubarea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "SELECT DISTINCT ?article ?reviewers "
				+ "WHERE " 
				+ "{"
				+ "?article rdf:type foaf:Document . "
				+ "?article foaf:title 'Linguagem de Software para Compiladores' . "
				+ "?article foaf:primaryTopic ?area . "
				+ "?subArea rdfs:subClassOf ?area ."
				+ "?reviewers acm:hasKnowledgeOf ?subarea ."
				+ "FILTER ("
				+ "?subarea != ?area"
				+ ")"
				+ "}";
		System.out.println("Recomendacao de revisores para artigos de subarea: Linguagem de Software para Compiladores");
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
