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
		System.out.println("\n");
		
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
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		//QueryController.getPerson(runOWLMicroReasoner(model));
		try {
			QueryController.getPerson(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void listarArtigos() {
		//Sem inferência
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getDocument(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getDocument(runRDFSReasoner(model));
		
		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		//QueryController.getDocument(runOWLMicroReasoner(model));
		try {
			QueryController.getDocument(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void listarAutores() {
		//Sem inferência
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getAutor(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getAutor(runRDFSReasoner(model));
		
		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		//QueryController.getAutor(runOWLMicroReasoner(model));
		try {
			QueryController.getAutor(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void listarRevisores() {
		//Sem inferência
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getRevisorArea(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getRevisorArea(runRDFSReasoner(model));
		
		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		//QueryController.getRevisorArea(runOWLMicroReasoner(model));
		try {
			QueryController.getRevisorArea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void listarArtigosPorArea() {
		//Sem inferência
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getArtigoByArea(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getArtigoByArea(runRDFSReasoner(model));
		
		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		//QueryController.getArtigoByArea(runOWLMicroReasoner(model));
		try {
			QueryController.getArtigoByArea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void listarArtigosPorSubarea() {
		//Sem inferência
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getSubArea(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getSubArea(runRDFSReasoner(model));
		
		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		//QueryController.getSubArea(runOWLMicroReasoner(model));
		try {
			QueryController.getSubArea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void sugerirRevisoresParaTodosArtigos() {
		//Sem inferência
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.sugerirRevisorArea(model);
		QueryController.sugerirRevisorSubArea(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.sugerirRevisorArea(runRDFSReasoner(model));
		QueryController.sugerirRevisorSubArea(runRDFSReasoner(model));
		
		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		//QueryController.sugerirRevisorArea(runOWLMicroReasoner(model));
		//QueryController.sugerirRevisorSubArea(runOWLMicroReasoner(model));
		try {
			QueryController.sugerirRevisorArea(runPelletReasoner(model));
			QueryController.sugerirRevisorSubArea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void sugerirRevisoresParaArtigoEspecifico() {
		//Sem inferência
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.sugerirRevisorArtigo1byArea(model);
		QueryController.sugerirRevisorArtigo1bySubArea(model);

		//Com inferência RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.sugerirRevisorArtigo1byArea(runRDFSReasoner(model));
		QueryController.sugerirRevisorArtigo1bySubArea(runRDFSReasoner(model));
		
		//Com inferência OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		//QueryController.sugerirRevisorArtigo1byArea(runOWLMicroReasoner(model));
		//QueryController.sugerirRevisorArtigo1bySubArea(runOWLMicroReasoner(model));
		try {
			QueryController.sugerirRevisorArtigo1byArea(runPelletReasoner(model));
			QueryController.sugerirRevisorArtigo1bySubArea(runPelletReasoner(model));
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
