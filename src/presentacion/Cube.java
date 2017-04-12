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

	public Color stringToColor(String arg) {
		Color color = null;
		try {
			Field field = Class.forName("java.awt.Color").getField(arg);
			color = (Color) field.get(null);
		} catch (Exception e) {
		}
		return color;
	}

	public void visited() {
		if(!evil)
			colors[0] = colors[3];
		else
			System.out.println("Lose");
	}

	public void move(int x, int y) {
		for (Polygon pol : edges) {
			pol.translate(x, y);
		}
	}
	public int[] getCords(){
		return new int[] {edges[0].xpoints[1],edges[0].ypoints[0]};
	}

	public void setColors(String[] colors,boolean bad) {
		evil=bad;
		this.colors = new Color[5];
		if (colors != null) {
			for (int i = 0; i < 4; i++) {
				this.colors[i] = stringToColor(colors[i]);
			}
		} else
			this.colors = new Color[] { Color.black, Color.BLACK, Color.black, Color.black };
	}

}