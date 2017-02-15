package juego;

public class Computadora {
	
	/*
	 * Aquí se encuentra el tablero del juego, la clase computadora necesita una instancia de 
	 * tipo tablero exactamente igual al tablero actual. El tablero estï¿½ compartido entre las
	 * intancias de las clases Jugador, TresEnRaya y computadora. Si en una casilla se coloca
	 * una ficha de tipo 1, se ha colocado una ficha de tipo O pero en caso de que se coloque
	 * una ficha de tipo 2, se está insertando una ficha de tipo X. O = 1, X = 2, Vacío = 0.
	 * 
	 * La clase Computadora tiene una instancia de tipo Jugador, la cual le permite a la com-
	 * putadora tener información del jugador y poder jugar adecuadamente.
	 */
	private Tablero tablero;
	private Jugador jugador;
	
	// Este campo de clase define el tipo de ficha que usa la instancia. O = 1 y X = 2.
	int tipo;
	
	// Define el tipo de estrategias que van a ser utilizadas.
	int tipoEstrategia;
	
	// Estas son las posiciones estratégicas de tipo uno.
	int[] posicionesEstrategicasUno0 = {0, 6, 8};
	int[] posicionesEstrategicasUno1 = {2, 8, 6};
	int[] posicionesEstrategicasUno2 = {6, 0, 2};
	int[] posicionesEstrategicasUno3 = {0, 2, 8};
	
	// Estas son las posiciones ganadoras de tipo uno.
	int[] posicionesGanadoasUno0 = {3, 7, 4};
	int[] posicionesGanadoasUno1 = {5, 7, 4};
	int[] posicionesGanadoasUno2 = {3, 1, 4};
	int[] posicionesGanadoasUno3 = {4, 5, 1};
	
	// Estas son las posiciones estratégicas de tipo dos.
	int[] posicionesEstrategicasDos0 = {0, 4, 2};
	int[] posicionesEstrategicasDos1 = {6, 4, 8};
	int[] posicionesEstrategicasDos2 = {0, 4, 6};
	int[] posicionesEstrategicasDos3 = {2, 4, 8};
		
	// Estas son las posiciones ganadoras de tipo dos.
	int[] posicionesGanadoasDos0 = {1, 6, 8};
	int[] posicionesGanadoasDos1 = {7, 0, 2};
	int[] posicionesGanadoasDos2 = {3, 2, 8};
	int[] posicionesGanadoasDos3 = {5, 0, 6};
	
	int[][] posicionesEstrategicas = new int[4][3];
	int[][] posicionesGanadoras = new int [4][3];
	
	// Aquí se inicializan las instancias de las clases Tablero y Jugador.
	public Computadora (Tablero tablero, Jugador jugador, int tipoEstrategia) {
		/* Se pasan las referencias de las instancias de las siguientes clases para que la computadora pueda
		*  tener acceso a la información del tabler y actuar en base a ello.
		*/
		this.tablero = tablero;
		this.jugador = jugador;
		this.tipoEstrategia = tipoEstrategia;
		
		// Si la estrategia es de tipo uno se asigna la estrategia u posición ganadora a su respectiva casilla.
		if (tipoEstrategia == 1) {
			posicionesEstrategicas[0] = posicionesEstrategicasUno0;
			posicionesEstrategicas[1] = posicionesEstrategicasUno1;
			posicionesEstrategicas[2] = posicionesEstrategicasUno2;
			posicionesEstrategicas[3] = posicionesEstrategicasUno3;
			
			posicionesGanadoras[0] = posicionesGanadoasUno0;
			posicionesGanadoras[1] = posicionesGanadoasUno1;
			posicionesGanadoras[2] = posicionesGanadoasUno2;
			posicionesGanadoras[3] = posicionesGanadoasUno3;
		}
		// Si la estrategia es de tipo uno se asigna la  estrategia u posición ganadora a su respectiva casilla.
		else {
			posicionesEstrategicas[0] = posicionesEstrategicasDos0;
			posicionesEstrategicas[1] = posicionesEstrategicasDos1;
			posicionesEstrategicas[2] = posicionesEstrategicasDos2;
			posicionesEstrategicas[3] = posicionesEstrategicasDos3;
			
			posicionesGanadoras[0] = posicionesGanadoasDos0;
			posicionesGanadoras[0] = posicionesGanadoasDos1;
			posicionesGanadoras[0] = posicionesGanadoasDos2;
			posicionesGanadoras[0] = posicionesGanadoasDos3;
		}
		
	}
	
	public void setTipo (int tipo) {
		if (tipo == 1 || tipo == 2)
			this.tipo = tipo;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public void jugar () {
		
		if (posicionGanadora(tipo) != 500) {
			System.out.println("La comptuadora ha jugado en la posición: " + posicionGanadora(tipo));
			tablero.jugar(posicionGanadora(tipo), tipo);
		}
		
		else if (posicionGanadora(jugador.getTipo()) != 500) {
			System.out.println("La comptuadora ha jugado en la posición: " + posicionGanadora(jugador.getTipo()));
			tablero.jugar(posicionGanadora(jugador.getTipo()), tipo);
		}
		
		else
			jugarAleatoriamente();
	}
	
	// Este método selecciona una posicion aleatoriamente la cual se encuentre vacï¿½a.
	private void jugarAleatoriamente () {
		
		int posicionAleatoria;
		boolean posicionAleatoriaVerificada = false;
		
		while (posicionAleatoriaVerificada == false) {
			
			double numeroAleatorio = Math.random() * 8;
			
			if (tablero.getTipo((int)numeroAleatorio) == 0) {
				posicionAleatoria = (int)numeroAleatorio;
				tablero.jugar(posicionAleatoria, tipo);
				posicionAleatoriaVerificada = true;

				System.out.println("La computadora ha jugado en la posición: " + posicionAleatoria);
				
			}
			
		}
		
	}

	private int posicionGanadora (int tipo) {
		
		// Una linea es un conjunto de posiciones que cuando cada posición tiene un mismo tipo se gana la partida.
		int[][] lineas = {{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}};

		for (int i = 0; i < lineas.length;i++) {
			
			int[] linea = lineas[i];
			
			// Si hay dos casillas llenas de un mismo tipo y una vacía la computadora llena la ficha vacía para ganar, o para bloquear al oponente.
			boolean fichaCeroFinal = tablero.getTipo(linea[0]) == 0 && tablero.getTipo(linea[1]) == tipo && tablero.getTipo(linea[2]) == tipo;
			boolean fichaUnoFinal = tablero.getTipo(linea[0]) == tipo && tablero.getTipo(linea[1]) == 0 && tablero.getTipo(linea[2]) == tipo;
			boolean fichaDosFinal = tablero.getTipo(linea[0]) == tipo && tablero.getTipo(linea[1]) == tipo && tablero.getTipo(linea[2]) == 0;
			
			// En caso de que una ficha está vacía y que las demás fichas están llenas de un mismo tipo, retorna la ficha vacï¿½a.
			if (fichaCeroFinal)
				return linea[0];
			else if (fichaUnoFinal)
				return linea[1];
			else if (fichaDosFinal)
				return linea[2];
		}
		
		// Al retornar el número 10 indica que no se ha encontrado una posicionGanadora.
		return 500;
	}
	
	
	// En caso de que la computadora haya jugado una ficha, este método se encarga de continuar la secuencia.
	private int continuarSecuencia () {
		
		// Una linea es un conjunto de fichas que cuando todas las fichas tienen un mismo tipo se gana la partida.
		int[][] lineas = {{0,3,8}, {1, 4, 7}, {2, 5, 8}, {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}};
		
		for (int i = 0; i < lineas.length; i++) {
			
			// Este es el conjunto de posiciones que forma una linea.
			int[] linea = lineas[i];
			
			// Aquí se muestra si dos posiciones son jugables. Y para que sean jugables tiene que haber una posición del tipo de la computadora.
			boolean CeroUnoJugable = tablero.getTipo(linea[0]) == 0 && tablero.getTipo(linea[1]) == 0 && tablero.getTipo(linea[2]) == tipo;
			boolean UnoDosJugable = tablero.getTipo(linea[0]) == tipo && tablero.getTipo(linea[1]) == 0 && tablero.getTipo(linea[2]) == 0;
			boolean CeroDosJugable = tablero.getTipo(linea[0]) == 0 && tablero.getTipo(linea[1]) == tipo && tablero.getTipo(linea[2]) == 0;
			
			// Se utiliza para decidir cual de las dos posibles casillas al continuar una secuencia va a ser utilizada.
			int decidirCasilla = (int) (Math.random() * 10);
			
			// En caso de que haya dos posiciones jugables, decide cuál de ellas va a jugar aleatoriamente.
			if (CeroUnoJugable) {
				if (decidirCasilla == 0 || decidirCasilla % 2 == 0)
					return linea[0];
				else
					return linea[1];
			}
			
			else if (UnoDosJugable) {
				if (decidirCasilla == 0 || decidirCasilla % 2 == 0)
					return linea[1];
				else
					return linea[2];
			}
			
			else if (CeroDosJugable) {
				if (decidirCasilla == 0 || decidirCasilla % 2 == 0)
					return linea[0];
				else
					return linea[2];
			}
			
		}
		// En caso de que no se pueda continuar una secuencia, entonces la computadora retorna 500 indicando de que no se puede continuar una secuencia.
		return 500;
	}

}
