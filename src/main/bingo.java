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

	// Muestra números que no han salido
	public static void NumerosSinSalir(int matrizMostrada[]) {

		// Muestra que números no han salido
		for (int fila = 0; fila < matrizMostrada.length; fila++) {
			if (matrizMostrada[fila] == 0) {
				if (fila != 0) {
					System.out.print(fila + " ");
				}

			}
		}
		System.out.println();
	}

	// Extra diana, selecciona 3 números entre el 1 y el 89 y si aciertas los 3
	// primeros,gana la diana de oro, 2 la diana de plata y 1 la diana de bronce
	public static int Diana(int matrizMostrada[], int numElegidos[]) {

		int contadorAciertos = 0;
		int premioDiana = 0;
		final int ORO = 1000, PLATA = 250, BRONCE = 30;

		for (int i = 0; i < numElegidos.length; i++) {
			for (int j = 0; j < matrizMostrada.length; j++) {

				if (numElegidos[i] == matrizMostrada[j]) {
					contadorAciertos++;
				}
			}
		}
		if (contadorAciertos == 3) {
			System.out.println("\u001B[33mGanaste la diana de oro");
			System.out.println("\u001B[37m");
			System.out.println("Se añadirán 1000€ al final");
			premioDiana = ORO;

		} else {
			if (contadorAciertos == 2) {
				System.out.println("\u001B[36mGanaste la diana de plata");
				System.out.println("\u001B[37m");
				System.out.println("Se añadirán 250€ al final");
				premioDiana = PLATA;
			} else {
				if (contadorAciertos == 1) {
					System.out.println("\u001B[31mGanaste la diana de bronce");
					System.out.println("\u001B[37m");
					System.out.println("Se añadirán 30€ al final");
					premioDiana = BRONCE;
				} else {
					System.out.println("No ganaste ninguna diana");

				}
			}
		}
		return premioDiana;

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
		int[] numerosDiana = new int[3];
		int menuPrincipal, menuApuesta, menuSaldo, contador = 0, aleatorio = 0;
		boolean lineaCantada = false;
		boolean primeralinea = false;
		boolean salir = false;
		boolean salidaDiana = false;
		int votoPartida = 0;
		final int JUGADOR = 1;
		final int MAQUINA = 2;
		final int EMPATE = 3;
		final int PREMIO = 500;
		final int ACIERTO_APUESTA = 500;
		int ganador = 0;
		int boteDiana = 0;
		int saldo = 0;
		int dinero = 0;
		String sn;
		final int PRECIO_PARTIDA = 10;
		final int PRECIO_DIANA = 5;
		final int PRECIO_APUESTA = 50;
		int ganadoEnPartida = 0;

		while (salir != true) {

			System.out.println("===========================");
			System.out.println("   BIENVENIDO AL BINGO   ");
			System.out.println("\u001B[32mSALDO: " + saldo + "€\t\u001B[33mPREMIO: 500€");
			System.out.println("\u001B[0m===========================");
			System.out.println("1) Empezar partida (10€)");
			System.out.println("2) Apuestas (50€)");
			System.out.println("3) Introducir saldo");
			System.out.println("4) Salir");
			System.out.println("===========================");
			menuPrincipal = sc.nextInt();
			// Limpia buffer ya que al recibir un valor númerico primero y luego un string
			// produce un salto de línea, por lo tanto, para que esto no ocurra se debe
			// limpiar el buffer de esta forma
			sc.nextLine();

			System.out.println("\n");

			switch (menuPrincipal) {
			case 1:
				if (saldo < PRECIO_PARTIDA) {
					System.out.println("\u001B[31mOperación denegada.\u001B[0m\nNo tienes suficiente saldo!\n");
				} else {
					saldo -= PRECIO_PARTIDA;
					ganadoEnPartida = 0;

					String eleccion;
					System.out.println("===========================");
					System.out.println("\t DIANA");
					System.out.println("\u001B[33mAcierta los números de las\ntres primeras rondas y\nllevate un premio extra");
					System.out.println("\u001B[0m===========================");
					System.out.println("¿Quieres jugar a la diana? (5€)");
					System.out.println("PREMIOS:");
					System.out.println("\u001B[33m3/3 Diana de oro - 1000€");
					System.out.println("\u001B[36m2/3 Diana de plata - 250€");
					System.out.println("\u001B[31m1/3 Diana de bronce - 30€");
					System.out.println("\u001B[37m");

					do {
						System.out.println("\nSelecciona 's' o 'n'");
						eleccion = sc.nextLine();
					} while (!eleccion.equalsIgnoreCase("s") && !eleccion.equalsIgnoreCase("n"));

					if (eleccion.equalsIgnoreCase("s") && saldo > PRECIO_DIANA) {
						salidaDiana = true;
						System.out.println("Introduce 3 números del 1 al 89:");
						for (int i = 0; i < numerosDiana.length; i++) {
							int numeroIngresado;
							boolean repetido;

							do {
								numeroIngresado = sc.nextInt();
								repetido = false;

								if (numeroIngresado < 1 || numeroIngresado > 89) {
									System.out.println("Debe estar entre 1 y 89");
									repetido = true;
								} else {

									for (int j = 0; j < i; j++) {
										if (numerosDiana[j] == numeroIngresado) {
											System.out.println("Ese número ha sido introducido anteriormente");
											repetido = true;
										}
									}
								}

							} while (repetido);

							numerosDiana[i] = numeroIngresado;
						}
						// Limpieza de buffer
						sc.nextLine();
					} else {
						System.out.println("\u001B[31mOperación denegada.\u001B[0m\n");
						System.out.println(">> Pulsa enter para avanzar");
						sc.nextLine();
					}

					System.out.println("\nEstos son los cartones repartidos:");

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

						if (contador == 3 && salidaDiana == true) {
							System.out.println();
							System.out.println();
							boteDiana = Diana(numerosMostrados, numerosDiana);
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
					System.out.println("\nLos números sin salir han sido: ");
					NumerosSinSalir(numerosMostrados);
					contador = 0;
					aleatorio = 0;
					numerosMostrados = new int[90];
					primeralinea=false;

					if (ganador == EMPATE) {
						System.out.println("Ganador: Empate");
						System.out.println("\u001B[33mPREMIO: " + (PREMIO / 2) + "€ para cada uno");
						saldo += (PREMIO / 2);
						ganadoEnPartida += (PREMIO / 2);
						if (boteDiana > 0) {
							System.out.println(("\u001B[33mY de diana : " + boteDiana + "€"));
							saldo += boteDiana;
							ganadoEnPartida += boteDiana;
						}
						System.out.println("Total ganado: " + ganadoEnPartida + "€");

					} else if (ganador == JUGADOR) {
						System.out.println("Ganador: Jugador");
						if (votoPartida == ganador) {
							System.out.println("Acertaste apostando!");
							System.out.println("\u001B[33mPREMIO: " + (PREMIO + ACIERTO_APUESTA) + "€");
							saldo += (PREMIO + ACIERTO_APUESTA);
							ganadoEnPartida += (PREMIO + ACIERTO_APUESTA);
						} else if (votoPartida != ganador && votoPartida != 0) {
							System.out.println("Perdiste apostando!");
							System.out.println("\u001B[33mPREMIO: " + (PREMIO) + "€");
							saldo += PREMIO;
							ganadoEnPartida += (PREMIO);
						} else {
							System.out.println("\u001B[33mPREMIO: " + (PREMIO) + "€");
							saldo += PREMIO;
							ganadoEnPartida += PREMIO;
						}
						if (boteDiana > 0) {
							System.out.println(("\u001B[33mY de diana : " + boteDiana + "€"));
							saldo += boteDiana;
							ganadoEnPartida += boteDiana;
						}
						System.out.println("Total ganado: " + ganadoEnPartida + "€");

					}

					else {
						System.out.println("Ganador: Máquina");
						if (boteDiana > 0) {
							System.out.println(("\u001B[33mY de diana : " + boteDiana + "€"));
							saldo += boteDiana;
							ganadoEnPartida += boteDiana;
						}
						System.out.println("Total ganado: " + ganadoEnPartida + "€");
					}

					System.out.println("\u001B[0m\nFIN DE LA PARTIDA");
					System.out.println("\n===========================");
					System.out.println("\n");
					votoPartida = 0;
				}
				break;

			case 2:
				System.out.println("===========================");
				System.out.println("\t APUESTAS");
				System.out.println("\u001B[32mSALDO: " + saldo + "€\t\u001B[33mPREMIO: 500€");
				System.out.println("Acierta y duplica el premio!");
				System.out.println("\u001B[0m===========================");
				System.out.println("¿Por quién apuestas?");
				System.out.println("1) Jugador");
				System.out.println("2) Máquina");
				System.out.println("3) Volver al menú");
				System.out.println("===========================");
				menuApuesta = sc.nextInt();

				if (votoPartida == 0) {
					if (menuApuesta == 1) {
						if (saldo < PRECIO_APUESTA) {
							System.out.println("\u001B[31mOperación denegada.\u001B[0m\nNo tienes suficiente saldo!\n");
						} else {
							System.out.println(
									"\u001B[32mHas votado por el jugador!\n\u001B[0mTu voto se tendrá en cuenta\nen la próxima partida.\n");
							votoPartida = JUGADOR;
							saldo -= PRECIO_APUESTA;
						}
					} else if (menuApuesta == 2) {
						System.out.println(
								"\u001B[32mHas votado por la máquina!\n\u001B[0mTu voto se tendrá en cuenta\nen la próxima partida.\n");
						votoPartida = MAQUINA;
						saldo -= PRECIO_APUESTA;
					}

				}

				else {
					System.out.println("\u001B[31mOperación denegada.\u001B[0m\nYa has votado en esta partida!\n");
				}
				break;

			case 3:
				System.out.println("===========================");
				System.out.println("     INTRODUCIR SALDO");
				System.out.println("\u001B[32mSALDO: " + saldo + "€\t\u001B[33mPREMIO: 500€");
				System.out.println("\u001B[0m===========================");
				System.out.println("Elige una operación:");
				System.out.println("1) Introducir dinero");
				System.out.println("2) Volver al menú");
				System.out.println("===========================");
				menuSaldo = sc.nextInt();

				if (menuSaldo == 1) {
					System.out.println("¿Cuánto dinero quieres introducir?");
					dinero = sc.nextInt();
					System.out.println("¿Confirmar operación? 's' o 'n'");
					sn = sc.next();
					if (sn.toLowerCase().equals("s")) {
						System.out.println("\u001B[32mOperación completa con éxito.\u001B[0m");
						saldo += dinero;
					} else {
						System.out.println("\u001B[31mOperación denegada.\u001B[0m");
						dinero = 0;

					}
				}

				break;

			case 4:
				System.out.println("Saliendo...");
				salir = true;
				System.out.println("=== TERMINADO ===");
				break;
			}
		}

		sc.close();
	}

}