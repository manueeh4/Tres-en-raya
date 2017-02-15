package juego;
import java.util.Scanner;

public class Jugador {
	
	private Tablero tablero;
	private int tipo;
	
	public Jugador (Tablero tablero) {
		this.tablero = tablero;
		this.tipo = tipo;
	}
	
	/* Este método se utiliza para que el jugador pueda insertar fichas en la posición 
	* que desee con su respectivo tipo. Si el jugador es O, su tipo es 1, pero si es X
	* su tipo es 2.
	*/
	public void jugar () {
		
		boolean posicionNoVerificada = false;
		while (posicionNoVerificada == false) {
			
			Scanner obtenerDatos = new Scanner(System.in);
			System.out.println("Inserta la posición que desees");
			int posicionSeleccionada = obtenerDatos.nextInt();
			
			if (tablero.getTipo(posicionSeleccionada) == 0) {
				tablero.jugar(posicionSeleccionada, tipo);
				posicionNoVerificada = true;
			}
			
			else {
				System.out.println("Has tratado de insertar una ficha que ya está ocupada.");
			}
			
		}
		
	}
	
	// Este método establece el tipo de ficha que el jugador va a utilizar para jugar.
	public void setTipo(int tipo) {
		if (tipo == 1 || tipo == 2)
			this.tipo = tipo;
	}
	
	// Este método informa a otras clases de qué tipo de ficha es la que el jugador usa.
	public int getTipo() {
		return tipo;
	}
	
}
