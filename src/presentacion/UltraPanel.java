/**
 * 
 */
package presentacion;

import java.util.*;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Field;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

import java.awt.*;

/**
 * @author blackphantom
 *
 */
public class UltraPanel extends JPanel {
	private ArrayList<Cube[]> land;
	private Color[] color;

	public void paintComponent(Graphics g) {
		setBackground(Color.black);
		super.paintComponent(g);
		for (Cube[] cs : land) {
			for (Cube u : cs) {
				for (int i = 0; i < 3; i++) {
					g.setColor(u.colors[i]);
					g.fillPolygon(u.edges[i]);
				}
			}
		}
	}

	public UltraPanel(JFrame god, String in, String fi) {
		color = new Color[] { stringToColor(in), Color.darkGray, Color.lightGray, stringToColor(fi) };
		land = new ArrayList<>();
		for (int i = 0; i < 600 / 100; i++) {
			if (i%2==0)
				land.add(new Cube[6]);
			else
				land.add(new Cube[5]);
		}
		int h=0;
		for (int i = 0; i < 600 / 100; i++) {
			int w=0;
			for (int j = 0; j < land.get(i).length; j++) {
				
				if (i % 2 == 0){
					land.get(i)[j] = (new Cube(25, color));
					land.get(i)[j].move(j*100,i*75);
					
				}
				else{
					land.get(i)[j] = (new Cube(25, color));
					land.get(i)[j].move((j*100)+50,i*75);
				}
			}
		}

	}

	public Color stringToColor(String arg) {
		Color color = null;
		try {
			Field field = Class.forName("java.awt.Color").getField(arg.toLowerCase());
			color = (Color) field.get(null);
		} catch (Exception e) {
		}
		return color;
	}

	public void changeColor(Color c) {

	}
}

class Cube {
	public Polygon[] edges;
	public Color[] colors;

	public Cube(int tam, Color[] colors) {
		int[] numbers=new int[5];
		numbers[0]=0;
		for (int i=1;i<5;i++)
			numbers[i]=tam*i;
		if (colors != null)
			this.colors = colors;
		else
			this.colors = new Color[] { Color.BLACK, Color.BLACK, Color.black, Color.BLACK };
		edges = new Polygon[3];
		edges[0] = new Polygon(new int[] { numbers[0], numbers[2], numbers[4], numbers[2] },
				new int[] { numbers[1], numbers[2], numbers[1], numbers[0] }, 4);

		edges[1] = new Polygon(new int[] { numbers[0], numbers[2], numbers[2], numbers[0] },
				new int[] { numbers[1], numbers[2], numbers[4], numbers[3] }, 4);

		edges[2] = new Polygon(new int[] { numbers[4], numbers[2], numbers[2], numbers[4] },
				new int[] { numbers[1], numbers[2], numbers[4], numbers[3] }, 4);
	}

	public void move(int x, int y) {
		for (Polygon pol : edges) {
			pol.translate(x , y );
		}
	}
	public void zoom(int z){
		for (Polygon pol : edges) {
			for(int i :pol.xpoints)
				i+=z;
			for(int j :pol.xpoints)
				j+=z;
		}
	}

}