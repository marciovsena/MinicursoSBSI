package app2.main;

import java.util.Scanner;

import app2.controller.ModelController;


public class App {
	
	public static void menu() {
		
		/**
		 * Opcoes do Menu
		 */
        System.out.println( "=== PRINCIPAL - OPCOES ===" );
        System.out.println( "1 - Listar Pessoas" );
        System.out.println( "2 - Listar Areas de conhecimento" );
		System.out.println( "3 - Listar Subareas" );
        System.out.println( "4 - Sugerir Pessoas em relacao a areas de conhecimento" );
        System.out.println( "5 - Sugerir Pessoas em relacao as subareas" );
        System.out.println( "6 - Sugerir Pessoas em relacao a area 'E_2_2_Object_Representation'" );
        System.out.println( "7 - Persistir no Repositorio TDB" );
        System.out.println( "8 - Imprimir Serializacao TURTLE" );
        System.out.println( "9 - Deletar Registros do TDB" );
        System.out.println( "10 - Sair" );
        System.out.print( "DIGITE UM NUMERO:" );
        System.out.println("");
	}
	
    public static void main( String[] args ) {
     
        /**
         * Inicia modelo em memoria com as informacoes:
         * 		- Area de conhecimento (ACM, áreas e subareas)
         * 		- Autores (FOAF, Person)
         * 		- Revisores (FOAF, Person)
         * 		- Artigos (FOAF, Document)
         */
        
        ModelController.initModel();
        
        
        /**
         * Lê opcao do usuario
         */
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        
        
        /**
         * Menu de opcoes
         * 
         * Cada método de consulta retorna 3 resultados:
         * 		- Sem inferencia
         * 		- inferencia RDFS
         * 		- inferencia OWL (Pellet)
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
				ModelController.listArea();
        	}
        	
        	else if (opcao == 3){
				ModelController.listSubarea();
        	}
        	else if (opcao == 4){
				ModelController.suggestPersonAllArea();
        	}
        	
        	else if (opcao == 5){
				ModelController.suggestPersonAllSubareas();
        	}
        	
        	else if (opcao == 6){
				ModelController.suggestPersonAreaSpecif();
        	}
        	
        	else if (opcao == 8){
        		ModelController.printModel();
        	}
        	
        	else if (opcao == 9){
        		ModelController.TDBRemoveRepositoryData();
        	}
        	
        	else if (opcao == 10){
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
