/**
 * 
 */
package presentacion;

import java.awt.Color;
import java.awt.Polygon;
import java.io.Serializable;
import java.lang.reflect.Field;

/** 
 * @author blackphantom
 *
 */
public class Cube {
	public Polygon[] edges;
	public Color[] colors;
	private boolean evil;
	
	/** Clase constructora por medio del tam del cubo y de un arreglo de colores
	 * @param tam dimension del cubo
	 * @param colors arreglo con los colores representados como String 
	 */
	public Cube(int tam, String[] colors) {
		int[] numbers = new int[5];
		numbers[0] = 0;
		setColors(colors,true);
		for (int i = 1; i < 5; i++)
			numbers[i] = tam * i;
		edges = new Polygon[3];
		edges[0] = new Polygon(new int[] { numbers[0], numbers[2], numbers[4], numbers[2] },
				               new int[] { numbers[1], numbers[2], numbers[1], numbers[0] }, 4);

		edges[1] = new Polygon(new int[] { numbers[0], numbers[2], numbers[2], numbers[0] },
				new int[] { numbers[1], numbers[2], numbers[4], numbers[3] }, 4);

		edges[2] = new Polygon(new int[] { numbers[4], numbers[2], numbers[2], numbers[4] },
				new int[] { numbers[1], numbers[2], numbers[4], numbers[3] }, 4);
	}
	
	/**
	 * Convierte una cadena a un color
	 * @param arg el color representado como cadena
	 * @return el color convertido al tipo Color
	 */
	public Color stringToColor(String arg) {
		Color color = null;
		try {
			Field field = Class.forName("java.awt.Color").getField(arg);
			color = (Color) field.get(null);
		} catch (Exception e) {
		}
		return color;
	}

	/**
	 * Decide si un cubo ha sido visitado o no
	 * @return verdadero si ha sido visitado, falso si no
	 */
	public boolean visited() {
		if(!evil)
			colors[0] = colors[3];
		return !evil?true:false;
	}

	/**
	 * Mueve el cubi dadas sus nuevas coordenadas
	 * @param x coordenada en x
	 * @param y coordenada en y
	 */
	public void move(int x, int y) {
		for (Polygon pol : edges) {
			pol.translate(x, y);
		}
	}
	
	/**
	 * Obtiene las coordenadas de un cubo
	 * @return un arreglo de enteros con las coordenadas
	 */
	public int[] getCords(){
		return new int[] {edges[0].xpoints[1],edges[0].ypoints[0]};
	}
	
	/**
	 * Asigna los colores a un cubo
	 * @param colors el arreglo de colores representado como cadenas
	 * @param bad verdadero si no es un cubo del escenario, falso en otro caso
	 */
	public void setColors(String[] colors,boolean bad) {
		evil=bad;
		this.colors = new Color[5];
		if (colors != null && !bad) {
			for (int i = 0; i < 4; i++) {
				this.colors[i] = stringToColor(colors[i]);
			}
		} else
			this.colors = new Color[] { Color.white
					, Color.BLACK, Color.black, Color.black };
	}

}