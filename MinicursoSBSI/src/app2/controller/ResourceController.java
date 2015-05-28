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
		
		Resource area8 = model.createResource(ACM.getURI()+"D_2_3_0_Object_Oriented_Programming")
				.addProperty(RDF.type, ACM.D_2_3_0_Object_Oriented_Programming);
		
		Resource area9 = model.createResource(ACM.getURI()+"E_2_2_Object_Representation")
				.addProperty(RDF.type, ACM.E_2_2_Object_Representation);
		
		Resource area10 = model.createResource(ACM.getURI()+"E_2_Data_Storage_Representations")
				.addProperty(RDF.type, ACM.E_2_Data_Storage_Representations);
		
		// Criação de um RECURSO com a ontolgia Foaf para Autor (Person)
		// Adição das PROPRIEDADES ao RECURSO
		Resource actor1 = model.createResource(FOAF.getURI()+"/WeslleyAraujo" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Weslley") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Araujo") );
		
		Resource actor2 = model.createResource(FOAF.getURI()+"/MarceloQuinta" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Marcelo") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Quinta") );
		
		Resource actor3 = model.createResource(FOAF.getURI()+"/DanielCoelho" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Daniel") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Coelho") );
		
		Resource actor4 = model.createResource(FOAF.getURI()+"/JoseFreitas" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Jose") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Freitas") );
		
		
		// Criação de um RECURSO com a ontolgia Foaf para Revisor (Person)
		// Adição das PROPRIEDADES ao RECURSO
		Resource reviewers1 = model.createResource(FOAF.getURI()+"/RenatoBulcao" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Renato") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Bulcao") )
				.addProperty(ACM.hasKnowledgeOf, area1 );

		Resource reviewers2 = model.createResource(FOAF.getURI()+"/ErnestoVeiga" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Ernesto") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Veiga") )
				.addProperty(ACM.hasKnowledgeOf, area2 );
		
		Resource reviewers3 = model.createResource(FOAF.getURI()+"/BrunoSilvestre" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Bruno") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Silvestre") )
				.addProperty(ACM.hasKnowledgeOf, area10 );
		
		Resource reviewers4 = model.createResource(FOAF.getURI()+"/WilliamMacedo" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("William") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Macedo") )
				.addProperty(ACM.hasKnowledgeOf, area9 );
		
		Resource reviewers5 = model.createResource(FOAF.getURI()+"/AndersonSoares" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Anderson") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Soares") )
				.addProperty(ACM.hasKnowledgeOf, area6 );
		
		Resource reviewers6 = model.createResource(FOAF.getURI()+"/EdmundoSpoto" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Edmundo") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Spoto") )
				.addProperty(ACM.hasKnowledgeOf, area7 );

		Resource reviewers7 = model.createResource(FOAF.getURI()+"/MarcioSena" )
				.addProperty(RDF.type, FOAF.Person )
				.addProperty(FOAF.firstName, model.createTypedLiteral("Marcio") )
				.addProperty(FOAF.lastName, model.createTypedLiteral("Sena") )
				.addProperty(ACM.hasKnowledgeOf, area1 );
	}
	
	public Model getModel(){
		return this.model;
	}
	
}
