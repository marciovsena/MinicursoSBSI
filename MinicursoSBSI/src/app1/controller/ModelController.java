package app1.controller;

import java.net.MalformedURLException;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import app1.database.PersistenceTDB;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.FileManager;

public class ModelController {
	
	static Model model;
	static String FOAF = "src/ontologies/foaf.owl";
	static String ACM = "src/ontologies/acm.owl";

	/**
	 * Carrega do modelo com as informacoes do ResourceController
	 *
	 */
	public static void initModel() {
		model = ModelFactory.createDefaultModel();
		ResourceController r = new ResourceController(model);
		model = r.getModel();
	}

	/**
	 * Escreve o modelo RDF na sintaxe TURTLE
	 *
	 */
	public static void printModel() {
		System.out.println();
		model.write(System.out, "TURTLE");
		System.out.println("\n");
	}

	/**
	 * Executa maquina de inferencia utilizando o RDFSReasoner
	 * @param model modelo RDF
	 *
	 */
	public static InfModel runRDFSReasoner(Model model) {
		Model schema = FileManager.get().loadModel(FOAF);
		Model schema2 = FileManager.get().loadModel(ACM);
		schema.add(schema2);
		
		InfModel infModel = ModelFactory.createRDFSModel(schema, model);

		return infModel;
	}

	/**
	 * Executa maquina de inferencia utilizando o OWLMicroReasoner
	 * @param model modelo RDF
	 *
	 */
	public static InfModel runOWLMicroReasoner(Model model) {		

		Model schema = FileManager.get().loadModel(FOAF);
		Model schema2 = FileManager.get().loadModel(ACM);
		schema.add(schema2);

		Reasoner reasoner = ReasonerRegistry.getOWLMicroReasoner();
		reasoner = reasoner.bindSchema(schema);
		
		InfModel infModel = ModelFactory.createRDFSModel(schema, model);
		infModel = ModelFactory.createInfModel(reasoner, model);
		
		return infModel;
	}

	/**
	 * Executa maquina de inferencia utilizando o Pellet (https://github.com/complexible/pellet)
	 * @param model modelo RDF
	 *
	 */
	public static InfModel runPelletReasoner(Model model) throws MalformedURLException{
    	
		Reasoner reasoner = PelletReasonerFactory.theInstance().create();

		InfModel infModel = ModelFactory.createInfModel(reasoner, model);
		infModel.read(FOAF);
		infModel.read(ACM);

		return infModel;
    }

	/**
	 * Realiza 3 consultas no metodo getPerson com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void listPerson() {
		//Sem inferencia
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getPerson(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getPerson(runRDFSReasoner(model));
		
		//Com inferencia Pellet
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		try {
			QueryController.getPerson(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Realiza 3 consultas no metodo getDocument com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void listArticle() {
		//Sem inferencia
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getDocument(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getDocument(runRDFSReasoner(model));
		
		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		try {
			QueryController.getDocument(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza 3 consultas no metodo getAutor com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void listActors() {
		//Sem inferencia
		System.out.println("");
		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getActor(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getActor(runRDFSReasoner(model));

		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		try {
			QueryController.getActor(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza 3 consultas no metodo getRevisorArea com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void listReviewers() {
		//Sem inferencia
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getReviewersArea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getReviewersArea(runRDFSReasoner(model));
		
		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		try {
			QueryController.getReviewersArea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza 3 consultas no metodo getArtigoByArea com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void listArticleByArea() {
		//Sem inferencia
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getArticleByArea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getArticleByArea(runRDFSReasoner(model));
		
		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		try {
			QueryController.getArticleByArea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza 3 consultas no metodo getSubArea com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void listArticleBySubarea() {
		//Sem inferencia
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getSubarea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getSubarea(runRDFSReasoner(model));
		
		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		try {
			QueryController.getSubarea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza 3 consultas no metodo sugerirRevisorArea e 3 consultas no metodo sugerirRevisorSubArea com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void suggestReviewersAreaByAllArticle() {
		//Sem inferencia
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.suggestReviewersArea(model);
		QueryController.suggestReviewersSubarea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.suggestReviewersArea(runRDFSReasoner(model));
		QueryController.suggestReviewersSubarea(runRDFSReasoner(model));
		
		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		try {
			QueryController.suggestReviewersArea(runPelletReasoner(model));
			QueryController.suggestReviewersSubarea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza 3 consultas no metodo sugerirRevisorArtigo1byArea e 3 consultas no metodo sugerirRevisorArtigo1bySubArea com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void suggestReviewersArticleSpecific() {
		//Sem inferencia
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.suggestReviewersForArticleByArea(model);
		QueryController.suggestReviewersForArticleBySubarea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.suggestReviewersForArticleByArea(runRDFSReasoner(model));
		QueryController.suggestReviewersForArticleBySubarea(runRDFSReasoner(model));
		
		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		try {
			QueryController.suggestReviewersForArticleByArea(runPelletReasoner(model));
			QueryController.suggestReviewersForArticleBySubarea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Persiste atualizando o modelo utilizando TDB
	 *
	 */
	public static void TDBPersistsRepositoryData() {
		PersistenceTDB tdb = new PersistenceTDB();
		tdb.updateModel(model);
		System.out.println();
	}

	/**
	 * Remove o modelo utilizando TDB
	 *
	 */
	public static void TDBRemoveRepositoryData() {
		PersistenceTDB tdb = new PersistenceTDB();
		tdb.removeModel(model);
		System.out.println();
	}
		
}