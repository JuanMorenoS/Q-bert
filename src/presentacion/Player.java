/**
 * 
 */
package presentacion;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author blackphantom
 *
 */
public class Player {
	public int[] origen;
	private ImageIcon image;
	private Image representation;
	private String name;
	private int lives,size;
	private char color;
	private String[] vis;
	public Player(int posx, int posy, int tam, int cx, int cy, String name, char col) {
		color = col;
		origen = new int[] { posx - (size * 3) / 2, posy - size * 3, cx, cy };
		this.name = name;
		lives = 10;
		size = tam;
		vis=new String[]{"D", "R"};
		refresImage();
	}

	/**
	 * le quita vidas al jugador
	 * @param a el tipo de muerte que tiene el jugador
	 */
	public void lose() {
		vis[0]="D";
		vis[1]="R";
		refresImage();
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
	private void refresImage() {
		image = new ImageIcon("resources/qbert" + color + "-" + vis[0] + vis[1] + ".png");
		this.representation = image.getImage().getScaledInstance(size * 3, size * 3, Image.SCALE_DEFAULT);
	}



}
