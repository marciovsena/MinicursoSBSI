package app2.controller;


import ontologies.ACM;
import ontologies.FOAF;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.vocabulary.RDF;


public class ResourceController {
	
	Model model;
	
	public ResourceController(Model model) {
		this.model = model;
		
		// Criação de um RECURSO com a ontolgia Acm para Área de Conhecimento
		// Adição das PROPRIEDADES ao RECURSO
		Resource area1 = model.createResource(ACM.getURI() + "B_1_4_0_Languages_And_Compilers")
				.addProperty(RDF.type, ACM.B_1_4_0_Languages_And_Compilers );
		
		Resource area2 = model.createResource(ACM.getURI()+"B_8_Performance_And_Reliability")
				.addProperty(RDF.type, ACM.B_8_Performance_And_Reliability);
		
		Resource area3 = model.createResource(ACM.getURI()+"B_Hardware")
				.addProperty(RDF.type, ACM.B_Hardware);
		
		Resource area4 = model.createResource(ACM.getURI()+"C_4_Performance_Of_Systems")
				.addProperty(RDF.type, ACM.C_4_Performance_Of_Systems);
		
		Resource area5 = model.createResource(ACM.getURI()+"C_5_3_0_Microprocessors")
				.addProperty(RDF.type, ACM.C_5_3_0_Microprocessors);
		
		Resource area6 = model.createResource(ACM.getURI()+"D_2_4_3_Formal_Methods")
				.addProperty(RDF.type, ACM.D_2_4_3_Formal_Methods);
		
		Resource area7 = model.createResource(ACM.getURI()+"D_2_4_8_Validation")
				.addProperty(RDF.type, ACM.D_2_4_8_Validation);
		
		Resource area8 = model.createResource(ACM.getURI()+"D_2_4_Software_Program_Verification")
				.addProperty(RDF.type, ACM.D_2_4_Software_Program_Verification);
		
		Resource area9 = model.createResource(ACM.getURI()+"E_2_2_Object_Representation")
				.addProperty(RDF.type, ACM.E_2_2_Object_Representation);
		
		Resource area10 = model.createResource(ACM.getURI()+"E_2_Data_Storage_Representations")
				.addProperty(RDF.type, ACM.E_2_Data_Storage_Representations);
		
		// Criação de um RECURSO com a ontolgia Foaf para Autor (Person)
		// Adição das PROPRIEDADES ao RECURSO
		Resource autor1 = model.createResource(FOAF.getURI()+"/WeslleyAraujo" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Weslley") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Araujo") );
		
		Resource autor2 = model.createResource(FOAF.getURI()+"/LizandraOttmann" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Lizandra") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Ottmann") );
		
		Resource autor3 = model.createResource(FOAF.getURI()+"/AndressaVuicik" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Andressa") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Vuicik") );
		
		Resource autor4 = model.createResource(FOAF.getURI()+"/JoseFreitas" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Jose") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Freitas") );
		
		
		// Criação de um RECURSO com a ontolgia Foaf para Revisor (Person)
		// Adição das PROPRIEDADES ao RECURSO
		Resource revisor1 = model.createResource(FOAF.getURI()+"/RenatoBulcao" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Renato") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Bulcao") )
				.addProperty(ACM.hasKnowledgeOf, area1 );
		
		Resource revisor2 = model.createResource(FOAF.getURI()+"/ErnestoVeiga" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Ernesto") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Veiga") )
				.addProperty(ACM.hasKnowledgeOf, area2 );
		
		Resource revisor3 = model.createResource(FOAF.getURI()+"/BrunoSilvestre" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Bruno") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Silves") )
				.addProperty(ACM.hasKnowledgeOf, area3 );
		
		Resource revisor4 = model.createResource(FOAF.getURI()+"/WilliamMacedo" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("William") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Macedo") )
				.addProperty(ACM.hasKnowledgeOf, area4 );
		
		Resource revisor5 = model.createResource(FOAF.getURI()+"/AndersonSoares" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Anderson") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Soares") )
				.addProperty(ACM.hasKnowledgeOf, area5 );
		
		Resource revisor6 = model.createResource(FOAF.getURI()+"/EdmundoSpoto" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Edmundo") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Spoto") )
				.addProperty(ACM.hasKnowledgeOf, area7 );
		
		
		// Criação de um RECURSO com a ontolgia Foaf para Artigo (Document)
		// Adição das PROPRIEDADES ao RECURSO
		Resource artigo1 = model.createResource(FOAF.getURI()+"/LinguagemDeSoftware" )
				.addProperty(RDF.type, FOAF.Document ) 
				.addProperty(FOAF.title, model.createTypedLiteral("Linguagem de Software para Compiladores") )
				.addProperty(FOAF.made, autor1 )
				.addProperty(FOAF.primaryTopic, area3 );
		
		Resource artigo2 = model.createResource(FOAF.getURI()+"/RaspberryPy" )
				.addProperty(RDF.type, FOAF.Document ) 
				.addProperty(FOAF.title, model.createTypedLiteral("Os principais componentes do RaspberryPy") )
				.addProperty(FOAF.made, autor2 )
				.addProperty(FOAF.primaryTopic, area7 );
		
		Resource artigo3 = model.createResource(FOAF.getURI()+"/PerformanceSistemasPy" )
				.addProperty(RDF.type, FOAF.Document ) 
				.addProperty(FOAF.title, model.createTypedLiteral("Performance de Sistemas em Python") )
				.addProperty(FOAF.made, autor3 )
				.addProperty(FOAF.primaryTopic, area3 );
		
		Resource artigo4 = model.createResource(FOAF.getURI()+"/RepresentacaoDeObjetos" )
				.addProperty(RDF.type, FOAF.Document ) 
				.addProperty(FOAF.title, model.createTypedLiteral("Representação de Objetos em Python") )
				.addProperty(FOAF.made, autor4 )
				.addProperty(FOAF.primaryTopic, area4 );

	}
	
	public Model getModel(){
		return this.model;
	}
	
}
