/**
 * 
 */
package presentacion;

import java.awt.Color;
import java.awt.Polygon;
import java.lang.reflect.Field;

/**
 * @author blackphantom
 *
 */
public class Cube {
	public Polygon[] edges;
	public Color[] colors;
	private boolean evil;

	/**
	 * @param tam
	 * @param colors
	 */
	public Cube(int tam, String[] colors) {
		int[] numbers = new int[5];
		numbers[0] = 0;
		setColors(colors, true);
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
	 * convierte una cadena a color
	 * 
	 * @param arg
	 *            la cadena
	 * @return el obejto color
	 */
	public static Color stringToColor(String arg) {
		Color color = null;
		try {
			Field field = Class.forName("java.awt.Color").getField(arg);
			color = (Color) field.get(null);
		} catch (Exception e) {
		}
		return color;
	}

	/**
	 * cambia el color del cubo cuando es visitado
	 * 
	 * @return si es un cubo de caida
	 */
	public boolean visited() {
		if (!evil)
			colors[0] = colors[3];
		return !evil ? true : false;
	}

	/**
	 * ubica el cubo en la posicion x y y del canvas
	 * 
	 * @param x
	 *            la pos en x
	 * @param y
	 *            la pos en y
	 */
	public void move(int x, int y) {
		for (Polygon pol : edges) {
			pol.translate(x, y);
		}
	}

	/**
	 * da la posicion del cubo
	 * 
	 * @return las cordenadas del cubo en el canvas
	 */
	public int[] getCords() {
		return new int[] { edges[0].xpoints[1], edges[0].ypoints[0] };
	}

	/**
	 * pone los colores a los cubos
	 * 
	 * @param colors
	 *            un arerglo de colores cara de arriba 2 lados, y el color final
	 * @param bad
	 *            si es un cuadro de caida o no
	 */
	public void setColors(String[] colors, boolean bad) {
		evil = bad;
		this.colors = new Color[5];
		if (colors != null && !bad) {
			for (int i = 0; i < 4; i++) {
				this.colors[i] = stringToColor(colors[i]);
			}
		} else
			this.colors = new Color[] { Color.black, Color.BLACK, Color.black, Color.black };
	}

}