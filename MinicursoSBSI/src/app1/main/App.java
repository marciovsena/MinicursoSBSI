package app1.main;

import java.util.Scanner;

import app1.controller.ModelController;


public class App {
	
	public static void menu() {
		
		/**
		 * Opções do Menu
		 */
        System.out.println( "=== PRINCIPAL - OPÇÕES ===" );
        System.out.println( "1 - Listar Pessoas" );
        System.out.println( "2 - Listar Artigos" );
        System.out.println( "3 - Listar Autores" );
        System.out.println( "4 - Listar Revisores" );
        System.out.println( "5 - Listar Artigo e sua Área" );
        System.out.println( "6 - Listar Subárea da Área do Artigo" );
        System.out.println( "7 - Sugerir Revisor para os Todos Artigos" );
        System.out.println( "8 - Sugerir Revisor para o Artigo: 'Linguagem de Software para Compiladores'" );
        System.out.println( "9 - Persistir no Repositório TDB" );
        System.out.println( "10 - Imprimir Serialização TURTLE" );
        System.out.println( "11 - Deletar Registros do TDB" );
        System.out.println( "12 - Sair" );
        System.out.print( "DIGITE UM NÚMERO: " );
        System.out.println("");
	}
	
    public static void main( String[] args ) {
     
        /**
         * Inicia modelo em memória com as informações:
         * 		- Área de conhecimento (ACM, áreas e subáreas)
         * 		- Autores (FOAF, Person)
         * 		- Revisores (FOAF, Person)
         * 		- Artigos (FOAF, Document)
         */
        
        ModelController.inicializaModelo();
        
        
        /**
         * Lê opção do usuário
         */
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        
        
        /**
         * Menu de opções
         * 
         * Cada método de consulta retorna 3 resultados:
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
        		ModelController.listarPessoas();
        	}
        	
        	else if (opcao == 2){
        		/**
        		 * Listar todos os recursos da classe FOAF.Document
        		 */
        		ModelController.listarArtigos();
        	}
        	
        	else if (opcao == 3){
        		/**
        		 * Listar todos os recursos da classe FOAF.Person
        		 * que possuam a propriedade FOAF.made
        		 */
        		ModelController.listarAutores();
        	}
        	else if (opcao == 4){
        		/**
        		 * Listar todos os recursos da classe FOAF.Person
        		 * que possuam a propriedade ACM.hasKnowlwdgeOf
        		 */
        		ModelController.listarRevisores();
        	}
        	
        	else if (opcao == 5){
        		/**
        		 * Listar todos os recursos da classe FOAF.Document e 
        		 * sua area FOAF.prymaryTopic
        		 */
        		ModelController.listarArtigosPorArea();
        	}
        	
        	else if (opcao == 6){
        		/**
        		 * Listar todos os recursos da classe FOAF.Document e 
        		 * sua area FOAF.prymaryTopic e subárea RDFS.subClassOf
        		 */
        		ModelController.listarArtigosPorSubarea();
        	}
        	
        	else if (opcao == 7){
        		/**
        		 * Listar todos os recursos da classe FOAF.Document (artigos) que
        		 * possuam a propriedade FOAF.primaryTopic (área), e os 
        		 * recursos (FOAF.Person, revisores) que possuam a propriedade 
        		 * ACM.hasKnowledgeOf (área de conhecimento) com o mesmo valor da propriedade
        		 * FOAF.primaryTopic (área do artigo)
        		 * 
        		 * Também busca subáreas dos artigos, utilizando RDFS.subClassOf
        		 */
        		ModelController.sugerirRevisoresParaTodosArtigos();
        	}
        	
        	else if (opcao == 8){
        		/**
        		 * Idem 7, porém recebe o nome do artigo FOAF.title
        		 */
        		ModelController.sugerirRevisoresParaArtigoEspecifico();
        	}
        	
        	else if (opcao == 9){
        		/**
        		 * Persistir o modelo em um repositório TDB
        		 */
        		ModelController.persistirDadosRepositorioTDB();
        	}
        	
        	else if (opcao == 10){
        		/**
        		 * Imprimir o modelo na linguagem de serialização TURTLE
        		 */
        		ModelController.imprimirModeloTURTLE();
        	}
        	
        	else if (opcao == 11){
        		/**
        		 * Apagar os dados do repositório TDB
        		 */
        		ModelController.deletarDadosRepositorioTDB();
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
