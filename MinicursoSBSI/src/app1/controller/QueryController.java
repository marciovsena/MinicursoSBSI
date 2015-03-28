package app1.controller;


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
	
	public static void getDocument(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "SELECT DISTINCT ?uri ?title " 
				+ "WHERE "
				+ "{"
				+ "?uri rdf:type foaf:Document ."
				+ "?uri foaf:title ?title ."
				+ "}";
		System.out.println("Artigos: ");
		queryModel(query, model);
		System.out.println();
	}
	
	public static void getAutor(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "SELECT DISTINCT ?autor ?artigo " 
				+ "WHERE "
				+ "{"
				+ "?artigo rdf:type foaf:Document ."
				+ "?artigo foaf:made ?autor ."
				+ "}";
		System.out.println("Autor e Artigo: ");
		queryModel(query, model);
		System.out.println();
	}
	
	public static void getRevisorArea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "SELECT DISTINCT ?revisor ?area " 
				+ "WHERE " 
				+ "{"
				+ "?revisor rdf:type foaf:Person ."
				+ "?revisor acm:hasKnowledgeOf ?area ."
				+ "}";
		System.out.println("Revisor e sua Área: ");
		queryModel(query, model);
		System.out.println();
	}
	
	public static void getArtigoByArea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "SELECT DISTINCT ?artigo ?area " 
				+ "WHERE " 
				+ "{"
				+ "?artigo rdf:type foaf:Document ."
				+ "?artigo foaf:primaryTopic ?area ."
				+ "?revisor acm:hasKnowledgeOf ?area ."
				+ "}";
		System.out.println("Artigo / Área: ");
		queryModel(query, model);
		System.out.println();
	}
	
	public static void getSubArea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "SELECT DISTINCT ?area ?subArea "
				+ "WHERE " 
				+ "{"
				+ "?artigo rdf:type foaf:Document ."
				+ "?artigo foaf:primaryTopic ?area ."
				+ "?subArea rdfs:subClassOf ?area ."
				+ "FILTER ("
				+ "?subArea != ?area"
				+ ")"
				+ "}";
		System.out.println("Áreas e Sub-Áreas de Artigos Existes: ");
		queryModel(query, model);
		System.out.println();
	}
	
	public static void sugerirRevisorArea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "SELECT DISTINCT ?artigo ?revisor "
				+ "WHERE " 
				+ "{"
				+ "?artigo rdf:type foaf:Document . "
				+ "?artigo foaf:primaryTopic ?area . "
				+ "?revisor acm:hasKnowledgeOf ?area . "
				+ "}";
		System.out.println("(PRIORIDADE 1) Recomendação de Revisor Prioritário: Mesma Área do Artigo");
		queryModel(query, model);
		System.out.println();
	}
	
	public static void sugerirRevisorSubArea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "SELECT DISTINCT ?artigo ?revisor ?subArea "
				+ "WHERE " 
				+ "{"
				+ "?artigo rdf:type foaf:Document . "
				+ "?artigo foaf:primaryTopic ?area . "
				+ "?subArea rdfs:subClassOf ?area ."
				+ "?revisor acm:hasKnowledgeOf ?subArea ."
				+ "FILTER ("
				+ "?subArea != ?area"
				+ ")"
				+ "}";
		System.out.println("(PRIORIDADE 2) Recomendação de Revisor: Mesma Sub-Área do Artigo");
		queryModel(query, model);
		System.out.println();
	}
	
	public static void sugerirRevisorArtigo1byArea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "SELECT DISTINCT ?artigo ?revisor "
				+ "WHERE " 
				+ "{"
				+ "?artigo rdf:type foaf:Document . "
				+ "?artigo foaf:title 'Linguagem de Software para Compiladores' . "				
				+ "?artigo foaf:primaryTopic ?area . "
				+ "?revisor acm:hasKnowledgeOf ?area . "
				+ "}";
		System.out.println("Recomendação de Revisor para Artigo na Area: Linguagem de Software para Compiladores");
		queryModel(query, model);
		System.out.println();
	}
	
	public static void sugerirRevisorArtigo1bySubArea(Model model) {
		String query;
		query = "PREFIX foaf: <http://www.semanticweb.org/ontologies/2013/10/foaf.owl#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX acm: <http://linkserver.icmc.usp.br/ckonto/acm#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "SELECT DISTINCT ?artigo ?revisor "
				+ "WHERE " 
				+ "{"
				+ "?artigo rdf:type foaf:Document . "
				+ "?artigo foaf:title 'Linguagem de Software para Compiladores' . "
				+ "?artigo foaf:primaryTopic ?area . "
				+ "?subArea rdfs:subClassOf ?area ."
				+ "?revisor acm:hasKnowledgeOf ?subArea ."
				+ "FILTER ("
				+ "?subArea != ?area"
				+ ")"
				+ "}";
		System.out.println("Recomendação de Revisor para Artigo na Sub-área: Linguagem de Software para Compiladores");
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
