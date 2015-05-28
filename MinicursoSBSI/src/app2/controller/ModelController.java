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
	
	/**
	 * Realiza 3 consultas no metodo getPerson com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void listPerson() {
		//Sem inferencia
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferencias ========\n");
		QueryController.getPerson(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferencias RDFS ========\n");
		QueryController.getPerson(runRDFSReasoner(model));
		
		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferencias Pellet ========\n");
		//QueryController.getPerson(runOWLMicroReasoner(model));
		try {
			QueryController.getPerson(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Realiza 3 consultas no metodo getArea com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void listArea() {
		//Sem inferencia
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferencias ========\n");
		QueryController.getArea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferencias RDFS ========\n");
		QueryController.getArea(runRDFSReasoner(model));
		
		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferencias Pellet ========\n");
		//QueryController.getAreas(runOWLMicroReasoner(model));
		try {
			QueryController.getArea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza 3 consultas no metodo getSubarea com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void listSubarea() {
		//Sem inferencia
		System.out.println("");
		System.out.println("\n\n======== Consultas sobre o modelo sem inferencias ========\n");
		QueryController.getSubarea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferencias RDFS ========\n");
		QueryController.getSubarea(runRDFSReasoner(model));

		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferencias Pellet ========\n");
//		QueryController.getSubAreas(runOWLMicroReasoner(model));
		try {
			QueryController.getSubarea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza 3 consultas no metodo suggestPeopleArea com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void suggestPersonAllArea() {
		//Sem inferencia
		System.out.println("");
		System.out.println("\n\n======== Consultas sobre o modelo sem inferencias ========\n");
		QueryController.suggestPeopleArea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferencias RDFS ========\n");
		QueryController.suggestPeopleArea(runRDFSReasoner(model));

		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferencia Pellet ========\n");
		//QueryController.sugerirPessoasArea(runOWLMicroReasoner(model));
		try {
			QueryController.suggestPeopleArea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza 3 consultas no metodo suggestPeopleSubarea com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void suggestPersonAllSubareas() {
		//Sem inferencia
		System.out.println("");
		System.out.println("\n\n======== Consultas sobre o modelo sem inferencias ========\n");
		QueryController.suggestPeopleSubarea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferencias RDFS ========\n");
		QueryController.suggestPeopleSubarea(runRDFSReasoner(model));

		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferencia Pellet ========\n");
		//QueryController.getDocument(runOWLMicroReasoner(model));
		try {
			QueryController.suggestPeopleSubarea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza 3 consultas no metodo suggestPeopleSpecificArea com modelo:
	 * - Sem inferencia
	 * - com inferencia RDFSResoaner
	 * - com inferencia Pellet
	 */
	public static void suggestPersonAreaSpecif() {
		//Sem inferencia
		System.out.println("");
		System.out.println("\n\n======== Consultas sobre o modelo sem inferencias ========\n");
		QueryController.suggestPeopleSpecificArea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferencias RDFS ========\n");
		QueryController.suggestPeopleSpecificArea(runRDFSReasoner(model));

		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferencia Pellet ========\n");
		//QueryController.getDocument(runOWLMicroReasoner(model));
		try {
			QueryController.suggestPeopleSpecificArea(runPelletReasoner(model));
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
