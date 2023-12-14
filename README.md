## PROYECTO-PRIMERA-EVALUCACION-BINGO
# Componentes: Ruben Camacho Gómez, Patricia Martínez Fernández y Pablo Rojas Roldán

# Objetivo final del proyecto
Realizar un bingo funcional en java.

#Especificación de requisitos
1. Menú del programa. (Patricia) Estimación: 14/12/2023
2. Generación aleatoria del cartón. (Patricia) Estimación: 14/12/2023
3. Generación aleatoria de número(1-89) por ronda. (Ruben) Estimación: 15/12/2023
4. Tachar automaticamente números que aparezcan en el cartón. (Ruben) Estimación: 15/12/2023
5. Detectar línea en el cartón. (Pablo) Estimación: 16/12/2023 - 17/12/2023
6. Detectar bingo en el cartón. (Pablo) Estimación: 16/12/2023 - 17/12/2023
8. Correcciones finales. (Grupo)

# Análisis
1. Menú del programa.

Cuenta con dos opciones, siendo la primera para iniciar el juego y la segunda para salir del programa.
Cuando una partida finalize tiene que volver a aparecer el menú del programa.

2. Generación aleatoria del cartón.

La generación por columnas de números aleatorios cuenta con distintos rangos:
	- 1º Columna: Números del 1 al 9.
	- 2º Columna: Números del 10 al 19.
	- 3º Columna: Números del 20 al 29.
	- 4º Columna: Números del 30 al 39.
	- 5º Columna: Números del 40 al 49.
	- 6º Columna: Números del 50 al 59.
	- 7º Columna: Números del 60 al 69.
	- 8º Columna: Números del 70 al 79.
	- 9º Columna: Números del 80 al 89.

Los números generados no pueden repetirse.
Los números generados tienen que estar ordenados de menor a mayor por columna.

3. Generación aleatoria de número(1-89) por ronda.

Las rondas tienen que estar enumeradas.
Generación del número(1-89) de la ronda específica.
El número por ronda debe de ser único, es decir, no se tiene que repetir.

4. Tachar automaticamente números que aparezcan en el cartón.

Comprobar que el número de la ronda coincide con algún número del cartón.
Si este coincide, tachar el número del cartón.
 
5. Detectar línea en el cartón.

Comprobar si una linea completa está tachada.
Solo imprimirá el mensaje de Línea! la primera vez que se tache una.
 
6. Detectar bingo en el cartón.

Comprobar si el cartón completo está tachado.
Si está completo imprimir el mensaje de Bingo!
Si no está completo se paasa a la siguiente ronda cuando el jugador lo especifique.
 
7. Correcciones finales.

Comprobaciones para que todos los requisitos funcionen y buscar la optimización del código.
 
