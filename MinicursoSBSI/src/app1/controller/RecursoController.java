package app1.controller;


import app1.ontologies.Acm;
import app1.ontologies.Foaf;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.vocabulary.RDF;


public class RecursoController {
	
	Model model;
	
	public RecursoController(Model model) {
		this.model = model;
		
		// Criação de um RECURSO com a ontolgia Acm para Área de Conhecimento
		// Adição das PROPRIEDADES ao RECURSO
		Resource area1 = model.createResource(Acm.getURI() + "B_1_4_0_Languages_And_Compilers")
				.addProperty(RDF.type, Acm.B_1_4_0_Languages_And_Compilers );
		
		Resource area2 = model.createResource(Acm.getURI()+"B_8_Performance_And_Reliability")
				.addProperty(RDF.type, Acm.B_8_Performance_And_Reliability);
		
		Resource area3 = model.createResource(Acm.getURI()+"B_Hardware")
				.addProperty(RDF.type, Acm.B_Hardware);
		
		Resource area4 = model.createResource(Acm.getURI()+"C_4_Performance_Of_Systems")
				.addProperty(RDF.type, Acm.C_4_Performance_Of_Systems);
		
		Resource area5 = model.createResource(Acm.getURI()+"C_5_3_0_Microprocessors")
				.addProperty(RDF.type, Acm.C_5_3_0_Microprocessors);
		
		Resource area6 = model.createResource(Acm.getURI()+"D_2_4_3_Formal_Methods")
				.addProperty(RDF.type, Acm.D_2_4_3_Formal_Methods);
		
		Resource area7 = model.createResource(Acm.getURI()+"D_2_4_8_Validation")
				.addProperty(RDF.type, Acm.D_2_4_8_Validation);
		
		Resource area8 = model.createResource(Acm.getURI()+"D_2_4_Software_Program_Verification")
				.addProperty(RDF.type, Acm.D_2_4_Software_Program_Verification);
		
		Resource area9 = model.createResource(Acm.getURI()+"E_2_2_Object_Representation")
				.addProperty(RDF.type, Acm.E_2_2_Object_Representation);
		
		Resource area10 = model.createResource(Acm.getURI()+"E_2_Data_Storage_Representations")
				.addProperty(RDF.type, Acm.E_2_Data_Storage_Representations);
		
		
		// Criação de um RECURSO com a ontolgia Foaf para Autor (Person)
		// Adição das PROPRIEDADES ao RECURSO
		Resource autor1 = model.createResource(Foaf.getURI()+"/WeslleyAraujo" )
				.addProperty(RDF.type, Foaf.Person )
				.addProperty(Foaf.firstName, model.createTypedLiteral("Weslley") )
				.addProperty(Foaf.lastName, model.createTypedLiteral("Araujo") );
		
		Resource autor2 = model.createResource(Foaf.getURI()+"/LizandraOttmann" )
				.addProperty(RDF.type, Foaf.Person )
				.addProperty(Foaf.firstName, model.createTypedLiteral("Lizandra") )
				.addProperty(Foaf.lastName, model.createTypedLiteral("Ottmann") );
		
		Resource autor3 = model.createResource(Foaf.getURI()+"/AndressaVuicik" )
				.addProperty(RDF.type, Foaf.Person )
				.addProperty(Foaf.firstName, model.createTypedLiteral("Andressa") )
				.addProperty(Foaf.lastName, model.createTypedLiteral("Vuicik") );
		
		Resource autor4 = model.createResource(Foaf.getURI()+"/JoseFreitas" )
				.addProperty(RDF.type, Foaf.Person )
				.addProperty(Foaf.firstName, model.createTypedLiteral("Jose") )
				.addProperty(Foaf.lastName, model.createTypedLiteral("Freitas") );
		
		
		// Criação de um RECURSO com a ontolgia Foaf para Revisor (Person)
		// Adição das PROPRIEDADES ao RECURSO
		Resource revisor1 = model.createResource(Foaf.getURI()+"/RenatoBulcao" )
				.addProperty(RDF.type, Foaf.Person )
				.addProperty(Foaf.firstName, model.createTypedLiteral("Renato") )
				.addProperty(Foaf.lastName, model.createTypedLiteral("Bulcao") )
				.addProperty(Acm.hasKnowledgeOf, area1 );
		
		Resource revisor2 = model.createResource(Foaf.getURI()+"/ErnestoVeiga" )
				.addProperty(RDF.type, Foaf.Person )
				.addProperty(Foaf.firstName, model.createTypedLiteral("Ernesto") )
				.addProperty(Foaf.lastName, model.createTypedLiteral("Veiga") )
				.addProperty(Acm.hasKnowledgeOf, area2 );
		
		Resource revisor3 = model.createResource(Foaf.getURI()+"/BrunoSilvestre" )
				.addProperty(RDF.type, Foaf.Person )
				.addProperty(Foaf.firstName, model.createTypedLiteral("Bruno") )
				.addProperty(Foaf.lastName, model.createTypedLiteral("Silves") )
				.addProperty(Acm.hasKnowledgeOf, area3 );
		
		Resource revisor4 = model.createResource(Foaf.getURI()+"/WilliamMacedo" )
				.addProperty(RDF.type, Foaf.Person )
				.addProperty(Foaf.firstName, model.createTypedLiteral("William") )
				.addProperty(Foaf.lastName, model.createTypedLiteral("Macedo") )
				.addProperty(Acm.hasKnowledgeOf, area4 );
		
		Resource revisor5 = model.createResource(Foaf.getURI()+"/AndersonSoares" )
				.addProperty(RDF.type, Foaf.Person )
				.addProperty(Foaf.firstName, model.createTypedLiteral("Anderson") )
				.addProperty(Foaf.lastName, model.createTypedLiteral("Soares") )
				.addProperty(Acm.hasKnowledgeOf, area5 );
		
		Resource revisor6 = model.createResource(Foaf.getURI()+"/EdmundoSpoto" )
				.addProperty(RDF.type, Foaf.Person )
				.addProperty(Foaf.firstName, model.createTypedLiteral("Edmundo") )
				.addProperty(Foaf.lastName, model.createTypedLiteral("Spoto") )
				.addProperty(Acm.hasKnowledgeOf, area7 );
		
		
		// Criação de um RECURSO com a ontolgia Foaf para Artigo (Document)
		// Adição das PROPRIEDADES ao RECURSO
		Resource artigo1 = model.createResource(Foaf.getURI()+"/LinguagemDeSoftware" )
				.addProperty(RDF.type, Foaf.Document ) 
				.addProperty(Foaf.title, model.createTypedLiteral("Linguagem de Software para Compiladores") )
				.addProperty(Foaf.made, autor1 )
				.addProperty(Foaf.primaryTopic, area3 );
		
		Resource artigo2 = model.createResource(Foaf.getURI()+"/RaspberryPy" )
				.addProperty(RDF.type, Foaf.Document ) 
				.addProperty(Foaf.title, model.createTypedLiteral("Os principais componentes do RaspberryPy") )
				.addProperty(Foaf.made, autor2 )
				.addProperty(Foaf.primaryTopic, area7 );
		
		Resource artigo3 = model.createResource(Foaf.getURI()+"/PerformanceSistemasPy" )
				.addProperty(RDF.type, Foaf.Document ) 
				.addProperty(Foaf.title, model.createTypedLiteral("Performance de Sistemas em Python") )
				.addProperty(Foaf.made, autor3 )
				.addProperty(Foaf.primaryTopic, area3 );
		
		Resource artigo4 = model.createResource(Foaf.getURI()+"/RepresentacaoDeObjetos" )
				.addProperty(RDF.type, Foaf.Document ) 
				.addProperty(Foaf.title, model.createTypedLiteral("Representação de Objetos em Python") )
				.addProperty(Foaf.made, autor4 )
				.addProperty(Foaf.primaryTopic, area4 );

	}
	
	public Model getModel(){
		return this.model;
	}
	
}
