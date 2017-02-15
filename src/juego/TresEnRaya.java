/*
 * Esta es una implementación del famoso juego tres en raya. Está escrita con el lenguaje de
 * programación Java y está licenciado bajo la licensia MIT, la cual establece que no soy r-
 * esponsable de ningún tipo de error o problema causado por alguna versión derivada de ést-
 * e software. Este software es software libre, osea que usted es libre de modificar cada a-
 * specto de este programa. El programa puede ser distribuido libremente sin pedirme autori-
 * zación alguna. Lo mismo para las versiones derivadas.
 * 
 * El juego consta de un tablero de nueve posiciones y dos tipos de fichas, X y O.
 * Cada una de las posiciones tiene un número. Las posiciones empiezan a contarse desde el
 * cero.
 * 
 *      |     |
 *   0  |  1  |  2
 * ------------------
 *      |     |
 *   3  |  4  |  5
 * ------------------
 *      |     |
 *   6  |  7  |  8
 *      
 * 
 *      |     |
 *   X  |  O  |  O
 * ------------------
 *      |     |
 *      |  X  |  
 * ------------------
 *      |     |
 *   x  |     |  X
 *   
 */

package juego;

public class TresEnRaya {
	
	public static int ganador;
	public static int turno;
	public static boolean computadoraJuegaPrimero;
	
	public static void main(String[] args) {
		
		// Se decide quién juega primero, si la computadora o el jugador.
		int numeroAleatorio = (int) (Math.random() * 10);
		// Se decide el tipo de estrategia que la computadora va a usar. En caso de que juege primero es 1.
		int tipoEstrategia = numeroAleatorio == 0 || numeroAleatorio % 2 == 0 ? 1 : 2;
		
		Tablero tablero = new Tablero();
		Jugador jugador = new Jugador(tablero);
		Computadora computadora = new Computadora(tablero, jugador, tipoEstrategia);

		if (numeroAleatorio == 0 || numeroAleatorio % 2 != 0) {
			computadoraJuegaPrimero = true;
			computadora.setTipo(2);
			jugador.setTipo(1);
			turno = 1;
		}
		
		else {
			computadoraJuegaPrimero = false;
			jugador.setTipo(2);
			computadora.setTipo(1);
			turno = 2;
		}
		
		while (partidaGanada(tablero) == false) {
			
			if (turno % 2 == 0) {
				jugador.jugar();
			}
			
			else {
				computadora.jugar();
			}
			
		}
		
		if (TresEnRaya.ganador == 0)
			System.out.println("Ambos han quedado en empate");
		else if (TresEnRaya.ganador == 1)
			System.out.println("O ha ganado la partida");
		else if (TresEnRaya.ganador == 2)
			System.out.println("X ha ganado la partida");
		else
			System.out.println("Omega el fuerte");
	}
	
	public static boolean partidaGanada (Tablero tablero) {
		
		// Las posibles formas en que O puede ganar.
		boolean ceroGanaColumna1 = tablero.getTipo(0) == 1 && tablero.getTipo(3) == 1 && tablero.getTipo(6) == 1;
		boolean ceroGanaColumna2 = tablero.getTipo(1) == 1 && tablero.getTipo(4) == 1 && tablero.getTipo(7) == 1;
		boolean ceroGanaColumna3 = tablero.getTipo(2) == 1 && tablero.getTipo(5) == 1 && tablero.getTipo(8) == 1;

		boolean ceroGanaFila1 = tablero.getTipo(0) == 1 && tablero.getTipo(1) == 1 && tablero.getTipo(2) == 1;
		boolean ceroGanaFila2 = tablero.getTipo(3) == 1 && tablero.getTipo(4) == 1 && tablero.getTipo(5) == 1;
		boolean ceroGanaFila3 = tablero.getTipo(6) == 1 && tablero.getTipo(7) == 1 && tablero.getTipo(8) == 1;
		
		boolean ceroGanaDiagonal1 = tablero.getTipo(0) == 1 && tablero.getTipo(4) == 1 && tablero.getTipo(8) == 1;
		boolean ceroGanaDiagonal2 = tablero.getTipo(2) == 1 && tablero.getTipo(4) == 1 && tablero.getTipo(6) == 1;
		
		// Las posibles formas en que X puede ganar.
		boolean equisGanaColumna1 = tablero.getTipo(0) == 2 && tablero.getTipo(3) == 2 && tablero.getTipo(6) == 2;
		boolean equisGanaColumna2 = tablero.getTipo(1) == 2 && tablero.getTipo(4) == 2 && tablero.getTipo(7) == 2;
		boolean equisGanaColumna3 = tablero.getTipo(2) == 2 && tablero.getTipo(5) == 2 && tablero.getTipo(8) == 2;

		boolean equisGanaFila1 = tablero.getTipo(0) == 2 && tablero.getTipo(1) == 2 && tablero.getTipo(2) == 2;
		boolean equisGanaFila2 = tablero.getTipo(3) == 2 && tablero.getTipo(4) == 2 && tablero.getTipo(5) == 2;
		boolean equisGanaFila3 = tablero.getTipo(6) == 2 && tablero.getTipo(7) == 2 && tablero.getTipo(8) == 2;
		
		boolean equisGanaDiagonal1 = tablero.getTipo(0) == 2 && tablero.getTipo(4) == 2 && tablero.getTipo(8) == 2;
		boolean equisGanaDiagonal2 = tablero.getTipo(2) == 2 && tablero.getTipo(4) == 2 && tablero.getTipo(6) == 2;
		
		boolean circuloGanador = ceroGanaColumna1 || ceroGanaColumna2 || ceroGanaColumna3 || ceroGanaFila1 || ceroGanaFila2
				|| ceroGanaFila3 || ceroGanaDiagonal1 || ceroGanaDiagonal2;
		
		boolean equisGanador = equisGanaColumna1 || equisGanaColumna2 || equisGanaColumna3 || equisGanaFila1 || equisGanaFila2
				|| equisGanaFila3 || equisGanaDiagonal1 || equisGanaDiagonal2;

		boolean juegoEmpatado = tablero.getTipo(0) != 0 && tablero.getTipo(1) != 0 && tablero.getTipo(2) != 0 && tablero.getTipo(3) != 0
				&& tablero.getTipo(4) != 0 && tablero.getTipo(5) != 0 && tablero.getTipo(6) != 0 && tablero.getTipo(7) != 0
					&& tablero.getTipo(8) != 0;
		if (circuloGanador){
			TresEnRaya.ganador = 1;
			return true;
		}
		
		else if (equisGanador) {
			TresEnRaya.ganador = 2;
			return true;
		}
		
		else if (juegoEmpatado) {
			TresEnRaya.ganador = 0;
			return true;
		}
		return false;
		
	}
	
}
