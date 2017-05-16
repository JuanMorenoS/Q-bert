package logicalT;

import java.io.Serializable;

public abstract class Conduct implements Serializable{
	protected Mobile element;

	/**
	 * returna el proximo movimiento de un jugador
	 * 
	 * @return b un arreglo de String con la posicion
	 */
	public abstract String[] nextMove();

	/**
	 * constructor conduc
	 * @param a el personaje que va a tomar la conducta
	 */
	public Conduct(Mobile a) {
		element = a;
	}
}
