/**
 * 
 */
package aplicacion;

import java.awt.Image;
import java.util.*;
import javax.swing.ImageIcon;

/**
 * @author blackphantom
 *
 */
public class Player {
	public int x, y;
	public int cx, cy;
	public int[] origen;
	private ImageIcon image;
	private Image representation;
	private int size;
	private String name;
	private int lives;
	private char color;

	public Player(int posx, int posy, int tam, int cx, int cy, String name, char col) {
		color = col;
		origen = new int[] { posx - (size * 3) / 2, posy - size * 3, cx, cy };
		this.name = name;
		lives = 3;
		size = tam;
		x = posx - (size * 3) / 2;
		y = posy - size * 3;
		this.cx = cx;
		this.cy = cy;
		refresImage("D", "R");
	}

	/**
	 * le quita vidas al jugador
	 * @param a el tipo de muerte que tiene el jugador
	 */
	public void lose(char a) {
		if (a == 'F')
			resetPosition();
		lives--;
		if (lives == 0) {
			this.representation = null;
			size = 0;
		}
		refresImage("D", "R");
	}

	/**
	 * pone el jugador en la pos inicial
	 */
	private void resetPosition() {
		x = origen[0] - (size * 3) / 2;
		y = origen[1] - size * 3;
		cx = origen[2];
		cy = origen[3];
	}

	/**
	 * mueve el jugador 
	 * @param a movimiento horizontal
	 * @param b movimiento vertical 
	 * @return si fue posible moverlo ahi
	 */
	public boolean move(String a, String b) {
		if (lives > 0) {
			int velx = size * 2;
			int vely = size * 3;
			if (a.equals("L"))
				velx *= -1;
			if (b.equals("U"))
				vely *= -1;
			x += velx;
			y += vely;
			if (size != 0) {
				if (cy % 2 != 0) {
					cx = (a.equals("L")) ? cx : cx + 1;
				} else {
					cx = (a.equals("R")) ? cx : cx - 1;
				}
				cy = (b.equals("U")) ? cy - 1 : cy + 1;
			}
			refresImage(b, a);
			return true;
		}
		return false;
	}

	/**
	 * me da la imagen del personaje
	 * @return la imagen del personaje en ese instante
	 */
	public Image getDraw() {
		return representation;
	}

	/**
	 * actualiza la imagen
	 * @param b vista en x
	 * @param a vista en y
	 */
	private void refresImage(String b, String a) {
		image = new ImageIcon("resources/qbert" + color + "-" + b + a + ".png");
		this.representation = image.getImage().getScaledInstance(size * 3, size * 3, Image.SCALE_DEFAULT);
	}

}
