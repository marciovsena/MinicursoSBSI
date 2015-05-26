package app2.main;

import java.util.Scanner;

import app2.controller.ModelController;


public class App {
	
	public static void menu() {
		
		/**
		 * Opções do Menu
		 */
        System.out.println( "=== PRINCIPAL - OPÇÕES ===" );
        System.out.println( "1 - Listar Pessoas" );
        System.out.println( "2 - Listar Areas de conhecimento" );
		System.out.println( "3 - Listar Subareas" );
        System.out.println( "4 - Sugerir Pessoas em relação a áreas de conhecimento" );
        System.out.println( "5 - Sugerir Pessoas em relação as subareas" );
        System.out.println( "6 - Sugerir Pessoas em relação a área 'B_1_4_0_Languages_And_Compilers'" );
        System.out.println( "7 - Persistir no Repositório TDB" );
        System.out.println( "8 - Imprimir Serialização TURTLE" );
        System.out.println( "9 - Deletar Registros do TDB" );
        System.out.println( "10 - Sair" );
        System.out.print( "DIGITE UM NÚMERO:" );
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
        		 * 
        		 */
        		ModelController.listarAreas();
        	}
        	
        	else if (opcao == 3){
        		/**
        		 * 
        		 */
        		ModelController.listarSubareas();
        	}
        	else if (opcao == 4){
        		/**
        		 */
        		ModelController.sugerirPessoasParaTodasAreas();
        	}
        	
        	else if (opcao == 5){
        		/**
        		 */
				ModelController.sugerirPessoasParaTodasSubareas();
        	}
        	
        	else if (opcao == 6){
        		/**
        		 */
				ModelController.sugerirPessoasParaAreaEspecifica();
        	}
        	
        	else if (opcao == 8){
        		/**
        		 */
        		ModelController.imprimirModeloTURTLE();
        	}
        	
        	else if (opcao == 9){
        		/**
        		 */
        		ModelController.deletarDadosRepositorioTDB();
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
