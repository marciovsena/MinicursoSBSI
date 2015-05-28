package app1.main;

import java.util.Scanner;

import app1.controller.ModelController;


public class App {
	
	public static void menu() {
		
		/**
		 * Opcoes do Menu
		 */
        System.out.println( "=== PRINCIPAL - OPCOES ===" );
        System.out.println( "1 - Listar Pessoas" );
        System.out.println( "2 - Listar Artigos" );
        System.out.println( "3 - Listar Autores" );
        System.out.println( "4 - Listar Revisores" );
        System.out.println( "5 - Listar Artigo e sua Area" );
        System.out.println( "6 - Listar Subarea da Area do Artigo" );
        System.out.println( "7 - Sugerir Revisor para os Todos Artigos" );
        System.out.println( "8 - Sugerir Revisor para o Artigo: 'Linguagem de Software para Compiladores'" );
        System.out.println( "9 - Persistir no Repositorio TDB" );
        System.out.println( "10 - Imprimir Serializacao TURTLE" );
        System.out.println( "11 - Deletar Registros do TDB" );
        System.out.println( "12 - Sair" );
        System.out.print( "DIGITE UM NUMERO: " );
        System.out.println("");
	}
	
    public static void main( String[] args ) {
     
        /**
         * Inicia modelo em memoria com as informacoes:
         * 		- Area de conhecimento (ACM, areas e subareas)
         * 		- Autores (FOAF, Person)
         * 		- Revisores (FOAF, Person)
         * 		- Artigos (FOAF, Document)
         */
        
        ModelController.initModel();

        /**
         * Le opcao do usuario
         */
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        /**
         * Menu de opcoes
         * 
         * Cada metodo de consulta retorna 3 resultados:
         * 		- Sem inferência
         * 		- Inferência RDFS
         * 		- Inferência OWL (Pellet)
         */
        while(opcao != 12){
        	menu();
        	opcao = sc.nextInt();
        	if (opcao == 1){
        		/**
        		 * Listar todos os recursos da classe FOAF.Person
        		 * Obs.: tanto autores quanto revisores 
        		 */
        		ModelController.listPerson();
        	}
        	
        	else if (opcao == 2){
        		/**
        		 * Listar todos os recursos da classe FOAF.Document
        		 */
        		ModelController.listArticle();
        	}
        	
        	else if (opcao == 3){
        		/**
        		 * Listar todos os recursos da classe FOAF.Person
        		 * que possuam a propriedade FOAF.made
        		 */
        		ModelController.listActors();
        	}
        	else if (opcao == 4){
        		/**
        		 * Listar todos os recursos da classe FOAF.Person
        		 * que possuam a propriedade ACM.hasKnowlwdgeOf
        		 */
        		ModelController.listReviewers();
        	}
        	
        	else if (opcao == 5){
        		/**
        		 * Listar todos os recursos da classe FOAF.Document e 
        		 * sua area FOAF.prymaryTopic
        		 */
				ModelController.listArticleByArea();
        	}
        	
        	else if (opcao == 6){
        		/**
        		 * Listar todos os recursos da classe FOAF.Document e 
        		 * sua area FOAF.prymaryTopic e subarea RDFS.subClassOf
        		 */
				ModelController.listArticleBySubarea();
        	}
        	
        	else if (opcao == 7){
        		/**
        		 * Listar todos os recursos da classe FOAF.Document (artigos) que
        		 * possuam a propriedade FOAF.primaryTopic (area), e os 
        		 * recursos (FOAF.Person, revisores) que possuam a propriedade 
        		 * ACM.hasKnowledgeOf (area de conhecimento) com o mesmo valor da propriedade
        		 * FOAF.primaryTopic (area do artigo)
        		 * 
        		 * Tambem busca subareas dos artigos, utilizando RDFS.subClassOf
        		 */
				ModelController.suggestReviewersAreaByAllArticle();
        	}
        	
        	else if (opcao == 8){
        		/**
        		 * Idem 7, porem recebe o nome do artigo FOAF.title
        		 */
				ModelController.suggestReviewersArticleSpecific();
        	}
        	
        	else if (opcao == 9){
        		/**
        		 * Persistir o modelo em um repositorio TDB
        		 */
				ModelController.TDBPersistsRepositoryData();
        	}
        	
        	else if (opcao == 10){
        		/**
        		 * Imprimir o modelo na linguagem de serializacao TURTLE
        		 */
				ModelController.printModel();
        	}
        	
        	else if (opcao == 11){
        		/**
        		 * Apagar os dados do repositorio TDB
        		 */
				ModelController.TDBRemoveRepositoryData();
        	}
        	
        	else if (opcao == 12){
        		/**
        		 * Sair do programa
        		 */
        		System.out.println("Bye!!!");
        		break;
        	}
        }
        sc.close();
    }
}
