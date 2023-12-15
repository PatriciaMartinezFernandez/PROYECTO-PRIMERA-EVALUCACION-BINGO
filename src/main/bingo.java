package main;
import java.util.Arrays;
import java.util.Scanner;

public class bingo {

	static Scanner sc = new Scanner(System.in);
	
	//Conteo de rondas
	public static int Rondas(int contador) {
		contador++;
		return contador;
	}
	
	//Generar número aleatorio entre 1 y 89 y no se repiten
	public static int GenerarNumeroUnico(int[] numerosMostrados) {
	    int numeroRandom;

	    do {
	        numeroRandom = (int) (Math.random() * 89) + 1;
	    } while (numerosMostrados[numeroRandom] != 0);

	    numerosMostrados[numeroRandom] = numeroRandom;
	    return numeroRandom;
	}
	

	//Comprobación del número aleatorio con el carton
	public static int [][]CartonComprobacion(int matriz[][],int aleatorio){
		
		int [][] carton=matriz;
		int [][] cartonComprobado=new int [3][9];
		
		//Se pasa todos los valores a la nueva matriz
		for(int fila=0;fila<carton.length;fila++) {
			for(int columna=0;columna<carton[fila].length;columna++) {
				cartonComprobado[fila][columna]=carton[fila][columna];
			}
		}
				
		for(int fila=0;fila<cartonComprobado.length;fila++) {
			for(int columna=0;columna<cartonComprobado[fila].length;columna++) {
				if(cartonComprobado[fila][columna]==aleatorio) {
					cartonComprobado[fila][columna]=0;
				}
			}
		}
		
		return cartonComprobado;
	}
	
	//Comprueba que todos los números del carton están a 0 y termina el juego
	public static boolean CartonCompleto(int matriz[][]) {
		
		boolean completo=true;
		
		for(int fila=0;fila<matriz.length;fila++) {
			for(int columna=0;columna<matriz[fila].length && completo;columna++) {
				if(matriz[fila][columna]!=0) {
					completo=false;
				}
			}
		}
		return completo;
	}
	
	
	public static int[][] imprimeMatriz(int matriz[][]) {
		for (int i = 0; i < matriz.length; i++) {
			System.out.println();
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
		}
		return matriz;
	}

	// Verifica si el número ya está en una columna del cartón
	public static boolean existeNumeroEnColumna(int[][] carton, int numero, int columna) {
		for (int i = 0; i < carton.length; i++) {
			if (carton[i][columna] == numero) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {

		int[][] carton = new int[3][9];
		int[]numerosMostrados=new int[90];
		int menu,contador=0,aleatorio=0;
		
		
		System.out.println("=========================");
		System.out.println("   BIENVENIDO AL BINGO   ");
		System.out.println("=========================");
		System.out.println("1) Empezar partida");
		System.out.println("2) Salir");
		System.out.println("=========================");
		menu = sc.nextInt();
		
		System.out.println("\n");

		if (menu == 1) {
			
			for (int i = 0; i < carton.length; i++) {
				for (int j = 0; j < carton[0].length; j++) {
					if (j == 0) {
						int numero;
						do {
							numero = (int) (Math.random() * 9) + 1;
						} while (existeNumeroEnColumna(carton, numero, j));
						carton[i][j] = numero;
					}
					else {
						// Generar números en el rango sin que se repita
						int numero;
						do {
							numero = (int) (Math.random() * 10) + (j * 10);
						} while (existeNumeroEnColumna(carton, numero, j));
						carton[i][j] = numero;
			
					}

				}
			}
			
			// Ordenar los números en cada columna
			for (int j = 0; j < carton[0].length; j++) {
				int[] columna = new int[3];
				for (int i = 0; i < carton.length; i++) {
					columna[i] = carton[i][j];
				}
				Arrays.sort(columna);
				for (int i = 0; i < carton.length; i++) {
					carton[i][j] = columna[i];
				}
			}
			
			System.out.println("=========================");
			System.out.println("\tB I N G O");
			imprimeMatriz(carton);
			
			
			do {
				System.out.println("\n\n=========================");
				contador=Rondas(contador);
				System.out.println("Ronda: "+contador);
				aleatorio=(GenerarNumeroUnico(numerosMostrados));
				System.out.println("Ha salido el "+aleatorio);
				System.out.println("\n\n=========================");
				carton=CartonComprobacion(carton,aleatorio);
				imprimeMatriz(carton);
				System.out.println("\n\n=========================");
				
				
			}
			while(!CartonCompleto(carton));
			System.out.println();
			
			
			
			
			
			
			
		}
		

		sc.close();
	}

}