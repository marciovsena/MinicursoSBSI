package app2.controller;

import java.net.MalformedURLException;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import app2.database.PersistenceTDB;

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

	public static void inicializaModelo() {
		model = ModelFactory.createDefaultModel();
		
		// Inicializa os Recursos para o Modelo
		ResourceController r = new ResourceController(model);
		model = r.getModel();
	}

	public static void imprimirModeloTURTLE() {
		// Escrita do MODELO RDF na sintaxe TURTLE
		System.out.println();
		model.write(System.out, "TURTLE");
	}

	public static InfModel runRDFSReasoner(Model model) {
		// Máquina Inferência utilizando o RDFSReasoner
		
		Model schema = FileManager.get().loadModel(FOAF);
		Model schema2 = FileManager.get().loadModel(ACM);
		schema.add(schema2);
		
		InfModel infModel = ModelFactory.createRDFSModel(schema, model);

		return infModel;
	}
	
	public static InfModel runOWLMicroReasoner(Model model) {		
		// Máquina Inferência utilizando o OWLMicroReasoner
		
		Model schema = FileManager.get().loadModel(FOAF);
		Model schema2 = FileManager.get().loadModel(ACM);
		schema.add(schema2);

		Reasoner reasoner = ReasonerRegistry.getOWLMicroReasoner();
		reasoner = reasoner.bindSchema(schema);
		
		InfModel infModel = ModelFactory.createRDFSModel(schema, model);
		infModel = ModelFactory.createInfModel(reasoner, model);
		
		return infModel;
	}
	
	public static InfModel runPelletReasoner(Model model) throws MalformedURLException{
    	
		Model schema = FileManager.get().loadModel(FOAF);
		Model schema2 = FileManager.get().loadModel(ACM);
		schema.add(schema2);

		// create Pellet reasoner
		Reasoner r = PelletReasonerFactory.theInstance().create();

		// create an inferencing model using the raw model
		InfModel infModel = ModelFactory.createInfModel(r, schema, model);
        
        return infModel;
    }
	
	public static void listarPessoas() {
		//Sem inferência
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getPerson(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getPerson(runRDFSReasoner(model));
		
		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências OWL ========\n");
		//QueryController.getPerson(runOWLMicroReasoner(model));
		try {
			QueryController.getPerson(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void listarAreas() {
		//Sem inferência
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getAreas(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getAreas(runRDFSReasoner(model));
		
		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências OWL ========\n");
		//QueryController.getDocument(runOWLMicroReasoner(model));
		try {
			QueryController.getAreas(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void listarSubareas() {
		//Sem inferência
		System.out.println("");
		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getSubAreas(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getSubAreas(runRDFSReasoner(model));

		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências OWL ========\n");
		//QueryController.getDocument(runOWLMicroReasoner(model));
		try {
			QueryController.getSubAreas(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void sugerirPessoasParaTodasAreas() {
		//Sem inferência
		System.out.println("");
		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.sugerirPessoasArea(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.sugerirPessoasArea(runRDFSReasoner(model));

		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferência Pellet ========\n");
		//QueryController.getDocument(runOWLMicroReasoner(model));
		try {
			QueryController.sugerirPessoasArea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void sugerirPessoasParaTodasSubareas() {
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

	public static void sugerirPessoasParaAreaEspecifica() {
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
	
	public static void persistirDadosRepositorioTDB() {
		// Persistência do MODELO RDF em memória para o BD
		PersistenceTDB tdb = new PersistenceTDB();
		tdb.updateModel(model);
		System.out.println();
	}
	
	public static void deletarDadosRepositorioTDB() {
		// Persistência do MODELO RDF em memória para o BD
		PersistenceTDB tdb = new PersistenceTDB();
		tdb.removeModel(model);
		System.out.println();
	}
}
