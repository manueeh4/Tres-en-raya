package juego;

public class Tablero {
	
	/* Aqu� se encuentra el tablero del juego. Son 9 posiciones, cada una puede tener 0, 1, 2.
	* Vac�o = 0, O = 1, X = 2.
	*/
	private int[] tablero = new int[9];
        
	// Este m�todo pone una ficha de un tipo determinado en una posici�n del tablero.
	public void jugar (int posicion, int tipo) {
		if (posicion < 9 && (tipo == 1 || tipo == 2))
			tablero[posicion] = tipo;
		TresEnRaya.turno++;
	}
	
	// Este m�todo retorna el tipo de ficha que hay en una determinada posici�n.
	public int getTipo (int posicion) throws IndexOutOfBoundsException {
		return tablero[posicion];
	}
	
}
