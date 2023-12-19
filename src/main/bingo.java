package main;

import java.util.Arrays;
import java.util.Scanner;

public class bingo {

	static Scanner sc = new Scanner(System.in);

	// Conteo de rondas
	public static int Rondas(int contador) {
		contador++;
		return contador;
	}

	// Generar número aleatorio entre 1 y 89 y no se repiten
	public static int GenerarNumeroUnico(int[] numerosMostrados) {
		int numeroRandom;

		do {
			numeroRandom = (int) (Math.random() * 89) + 1;
		} while (numerosMostrados[numeroRandom] != 0);

		numerosMostrados[numeroRandom] = numeroRandom;
		return numeroRandom;
	}

	// Comprobación del número aleatorio con el carton
	public static int[][] CartonComprobacion(int matriz[][], int aleatorio) {

		int[][] carton = matriz;
		int[][] cartonComprobado = new int[3][9];

		// Se pasa todos los valores a la nueva matriz
		for (int fila = 0; fila < carton.length; fila++) {
			for (int columna = 0; columna < carton[fila].length; columna++) {
				cartonComprobado[fila][columna] = carton[fila][columna];
			}
		}

		for (int fila = 0; fila < cartonComprobado.length; fila++) {
			for (int columna = 0; columna < cartonComprobado[fila].length; columna++) {
				if (cartonComprobado[fila][columna] == aleatorio) {
					cartonComprobado[fila][columna] = 0;
				}
			}
		}

		return cartonComprobado;
	}

	// Comprueba que todos los números del carton están a 0 y termina el juego
	public static boolean Bingo(int matriz[][]) {

		boolean completo = true;

		for (int fila = 0; fila < matriz.length; fila++) {
			for (int columna = 0; columna < matriz[fila].length && completo; columna++) {
				if (matriz[fila][columna] != 0) {
					completo = false;
				}
			}
		}
		return completo;
	}

	// Linea
	public static boolean cartonLinea(int matriz[][], boolean lineaCantada) {

		final int CONTADOR_LINEA = 9;

		if (!lineaCantada) {
			for (int i = 0; i < matriz.length; i++) {
				int contador = 0;
				for (int j = 0; j < matriz[i].length; j++) {
					if (matriz[i][j] == 0) {
						contador++;
						if (contador == CONTADOR_LINEA) {
							// System.out.println("\u001B[0m\nLINEA!");
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public static int[][] imprimeMatriz(int matriz[][]) {
		for (int i = 0; i < matriz.length; i++) {
			System.out.println();
			for (int j = 0; j < matriz[0].length; j++) {
				if (matriz[i][j] == 0) {
					System.out.print("\u001B[31m X ");
				} else {
					System.out.printf("\u001B[0m%2d ", matriz[i][j]);
				}
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

	// Generar números en el rango sin que se repita
	public static int[][] rellenaCarton(int matriz[][]) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				if (j == 0) {
					int numero;
					do {
						numero = (int) (Math.random() * 9) + 1;
					} while (existeNumeroEnColumna(matriz, numero, j));
					matriz[i][j] = numero;
				} else {

					int numero;
					do {
						numero = (int) (Math.random() * 10) + (j * 10);
					} while (existeNumeroEnColumna(matriz, numero, j));
					matriz[i][j] = numero;

				}

			}
		}

		return matriz;
	}

	// Ordenar los números en cada columna
	public static int[][] ordenaCarton(int matriz[][]) {
		matriz = rellenaCarton(matriz);

		for (int j = 0; j < matriz[0].length; j++) {
			int[] columna = new int[3];
			for (int i = 0; i < matriz.length; i++) {
				columna[i] = matriz[i][j];
			}
			Arrays.sort(columna);
			for (int i = 0; i < matriz.length; i++) {
				matriz[i][j] = columna[i];
			}
		}
		return matriz;
	}

	public static void main(String[] args) {

		int[][] carton = new int[3][9];
		int[][] cartonMaquina = new int[3][9];
		int[] numerosMostrados = new int[90];
		int menuPrincipal, menuApuesta, contador = 0, aleatorio = 0;
		boolean lineaCantada = false;
		boolean primeralinea = false;
		boolean salir = false;
		int votoPartida = 0;
		final int JUGADOR = 1;
		final int MAQUINA = 2;
		final int EMPATE = 3;
		final int PREMIO = 500;
		final int ACIERTO_APUESTA = 500;
		int ganador = 0;

		while (salir != true) {

			System.out.println("===========================");
			System.out.println("   BIENVENIDO AL BINGO   ");
			System.out.println("\u001B[33m       PREMIO: 500€");
			System.out.println("\u001B[0m===========================");
			System.out.println("1) Empezar partida");
			System.out.println("2) Apuestas");
			System.out.println("3) Salir");
			System.out.println("===========================");
			menuPrincipal = sc.nextInt();
			// Limpia buffer ya que al recibir un valor númerico primero y luego un string
			// produce un salto de línea, por lo tanto, para que esto no ocurra se debe
			// limpiar el buffer de esta forma
			sc.nextLine();

			System.out.println("\n");

			switch (menuPrincipal) {
			case 1:

				System.out.println("Estos son los cartones repartidos:");

				System.out.println("===========================");
				System.out.println("\tB I N G O");
				System.out.println("\t Jugador");
				imprimeMatriz(ordenaCarton(carton));
				System.out.println("\n\n===========================");
				System.out.println("\t Máquina");
				imprimeMatriz(ordenaCarton(cartonMaquina));
				System.out.println("\n\n===========================");

				do {
					System.out.println(">> Pulsa enter para avanzar");
					sc.nextLine();

					contador = Rondas(contador);
					System.out.println("\u001B[32mRONDA: " + contador);
					aleatorio = (GenerarNumeroUnico(numerosMostrados));
					System.out.println("\u001B[0mHa salido el " + aleatorio);

					// Jugador
					System.out.println("\n===========================");
					System.out.println("\tB I N G O");
					System.out.println("\t Jugador");
					carton = CartonComprobacion(carton, aleatorio);
					imprimeMatriz(carton);
					if (cartonLinea(carton, lineaCantada) == true && primeralinea == false) {
						System.out.println("\u001B[0m\nLINEA!");
						primeralinea = true;
					}
					if ((Bingo(carton) == true)) {
						System.out.println("\u001B[0m\nBINGO!");
						ganador = JUGADOR;
					}
					System.out.println("\u001B[0m\n===========================");

					// Maquina
					System.out.println("\t Máquina");
					cartonMaquina = CartonComprobacion(cartonMaquina, aleatorio);
					imprimeMatriz(cartonMaquina);
					if (cartonLinea(cartonMaquina, lineaCantada) == true && primeralinea == false) {
						System.out.println("\u001B[0m\nLINEA!");
						primeralinea = true;
					}
					if ((Bingo(cartonMaquina) == true)) {
						if (ganador == JUGADOR) {
							System.out.println("\u001B[0m\nBINGO!");
							ganador = EMPATE;
						} else {
							System.out.println("\u001B[0m\nBINGO!");
							ganador = MAQUINA;
						}
					}
					System.out.println("\u001B[0m\n===========================");

				} while (!(Bingo(carton)) && (!(Bingo(cartonMaquina))));
				System.out.println("===========================");
				contador = 0;
				aleatorio = 0;
				numerosMostrados = new int[90];

				if (ganador == EMPATE) {
					System.out.println("Ganador: Empate");
				} else if (ganador == JUGADOR) {
					System.out.println("Ganador: Jugador");
				} else {
					System.out.println("Ganador: Máquina");
				}
				if (votoPartida == ganador) {
					System.out.println("Acertaste apostando!");
					System.out.println("\u001B[33mPREMIO: " + (PREMIO + ACIERTO_APUESTA) + "€");
				} else if (ganador == EMPATE) {
					System.out.println("\u001B[33mPREMIO: " + (PREMIO / 2) + "€ para cada uno");
				} else if (votoPartida != ganador && votoPartida != 0){
					System.out.println("Perdiste apostando!");
					System.out.println("\u001B[33mPREMIO: " + PREMIO + "€");
				} else {
					System.out.println("\u001B[33mPREMIO: " + PREMIO + "€");
				}
				System.out.println("\u001B[0m\nFIN DE LA PARTIDA");
				System.out.println("\n===========================");
				System.out.println("\n");
				votoPartida = 0;
				break;

			case 2:
				System.out.println("===========================");
				System.out.println("\t APUESTAS");
				System.out.println("\u001B[33mAcierta y duplica el premio!");
				System.out.println("\u001B[0m===========================");
				System.out.println("¿Por quién apuestas?");
				System.out.println("1) Jugador");
				System.out.println("2) Máquina");
				System.out.println("3) Volver al menú");
				System.out.println("===========================");
				menuApuesta = sc.nextInt();

				if (votoPartida == 0) {
					if (menuApuesta == 1) {
						System.out.println(
								"\u001B[32mHas votado por el jugador!\n\u001B[0mTu voto se tendrá en cuenta\nen la próxima partida.\n");
						votoPartida = JUGADOR;
					} else if (menuApuesta == 2) {
						System.out.println(
								"\u001B[32mHas votado por la máquina!\n\u001B[0mTu voto se tendrá en cuenta\nen la próxima partida.\n");
						votoPartida = MAQUINA;
					}
				}

				else {
					System.out.println("\u001B[31mOperación denegada.\u001B[0m\nYa has votado en esta partida!\n");
				}
				break;

			case 3:
				System.out.println("Saliendo...");
				salir = true;
				System.out.println("=== TERMINADO ===");
				break;
			}
		}

		sc.close();
	}

}