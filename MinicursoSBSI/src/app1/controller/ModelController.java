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
	 * Carrega do modelo com as informaçoes do ResourceController
	 *
	 */
	public static void inicializaModelo() {
		model = ModelFactory.createDefaultModel();
		ResourceController r = new ResourceController(model);
		model = r.getModel();
	}

	/**
	 * Escreve o modelo RDF na sintaxe TURTLE
	 *
	 */
	public static void imprimirModeloTURTLE() {
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
	public static void listarPessoas() {
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
	public static void listarArtigos() {
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
	public static void listarAutores() {
		//Sem inferencia
		System.out.println("");
		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getAutor(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getAutor(runRDFSReasoner(model));

		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		try {
			QueryController.getAutor(runPelletReasoner(model));
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
	public static void listarRevisores() {
		//Sem inferencia
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getRevisorArea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getRevisorArea(runRDFSReasoner(model));
		
		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		try {
			QueryController.getRevisorArea(runPelletReasoner(model));
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
	public static void listarArtigosPorArea() {
		//Sem inferencia
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getArtigoByArea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getArtigoByArea(runRDFSReasoner(model));
		
		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		try {
			QueryController.getArtigoByArea(runPelletReasoner(model));
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
	public static void listarArtigosPorSubarea() {
		//Sem inferencia
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.getSubArea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.getSubArea(runRDFSReasoner(model));
		
		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		try {
			QueryController.getSubArea(runPelletReasoner(model));
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
	public static void sugerirRevisoresParaTodosArtigos() {
		//Sem inferencia
		System.out.println("");
  		System.out.println("\n\n======== Consultas sobre o modelo sem inferências ========\n");
		QueryController.sugerirRevisorArea(model);
		QueryController.sugerirRevisorSubArea(model);

		//Com inferencia RDFSResoaner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências RDFS ========\n");
		QueryController.sugerirRevisorArea(runRDFSReasoner(model));
		QueryController.sugerirRevisorSubArea(runRDFSReasoner(model));
		
		//Com inferencia OWLMicroReasoner
		System.out.println("\n\n======== Consultas sobre o modelo com inferências Pellet ========\n");
		try {
			QueryController.sugerirRevisorArea(runPelletReasoner(model));
			QueryController.sugerirRevisorSubArea(runPelletReasoner(model));
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
		try {
			QueryController.sugerirRevisorArtigo1byArea(runPelletReasoner(model));
			QueryController.sugerirRevisorArtigo1bySubArea(runPelletReasoner(model));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Persiste atualizando o modelo utilizando TDB
	 *
	 */
	public static void persistirDadosRepositorioTDB() {
		// Persistência do MODELO RDF em memória para o BD
		PersistenceTDB tdb = new PersistenceTDB();
		tdb.updateModel(model);
		System.out.println();
	}

	/**
	 * Remove o modelo utilizando TDB
	 *
	 */
	public static void deletarDadosRepositorioTDB() {
		// Persistência do MODELO RDF em memória para o BD
		PersistenceTDB tdb = new PersistenceTDB();
		tdb.removeModel(model);
		System.out.println();
	}
		
}