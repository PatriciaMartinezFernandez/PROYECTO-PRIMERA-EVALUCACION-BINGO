package main;

import java.util.Arrays;
import java.util.Scanner;

public class bingo {

	static Scanner sc = new Scanner(System.in);

	public static void imprimeMatriz(int matriz[][]) {
		for (int i = 0; i < matriz.length; i++) {
			System.out.println();
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
		}
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
		int menu;
		
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
			System.out.println("\n\n=========================");
			
		}
		

		sc.close();
	}

}