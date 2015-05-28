package app2.controller;

import java.net.MalformedURLException;

import com.hp.hpl.jena.ontology.OntModel;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.mindswap.pellet.jena.PelletReasoner;

import app2.database.PersistenceTDB;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.FileManager;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import uk.ac.manchester.cs.owl.owlapi.OWLOntologyManagerImpl;


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
	
	public static void listPerson() {
		//Sem inferência
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getPerson(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getPerson(runRDFSReasoner(model));
		
		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		//QueryController.getPerson(runOWLMicroReasoner(model));
		try {
			QueryController.getPerson(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void listArea() {
		//Sem inferência
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getAreas(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getAreas(runRDFSReasoner(model));
		
		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		//QueryController.getAreas(runOWLMicroReasoner(model));
		try {
			QueryController.getAreas(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void listSubarea() {
		//Sem inferência
		System.out.println("");
		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getSubAreas(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getSubAreas(runRDFSReasoner(model));

		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
//		QueryController.getSubAreas(runOWLMicroReasoner(model));
		try {
			QueryController.getSubAreas(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void suggestPersonAllArea() {
		//Sem inferência
		System.out.println("");
		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.sugerirPessoasArea(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.sugerirPessoasArea(runRDFSReasoner(model));

		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferência Pellet ========\n");
		//QueryController.sugerirPessoasArea(runOWLMicroReasoner(model));
		try {
			QueryController.sugerirPessoasArea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void suggestPersonAllSubareas() {
		//Sem inferência
		System.out.println("");
		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.sugerirPessoasSubarea(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.sugerirPessoasSubarea(runRDFSReasoner(model));

		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferência Pellet ========\n");
		//QueryController.getDocument(runOWLMicroReasoner(model));
		try {
			QueryController.sugerirPessoasSubarea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void suggestPersonAreaSpecif() {
		//Sem inferência
		System.out.println("");
		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.sugerirPessoasAreaEspecifica(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.sugerirPessoasAreaEspecifica(runRDFSReasoner(model));

		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferência Pellet ========\n");
		//QueryController.getDocument(runOWLMicroReasoner(model));
		try {
			QueryController.sugerirPessoasAreaEspecifica(runPelletReasoner(model));
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
