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
public class Player extends Mobile{
	public int[] origen;
	private ImageIcon image;
	private Image representation;
	private String name;
	private int lives,size;
	private char color;
	private String[] vis;
	public Player(int posx, int posy, int tam, int cx, int cy, String name, char col) {
		super(posx, posy, cx, cy);
		color = col;
		origen = new int[] { posx - (size * 3) / 2, posy - size * 3, cx, cy };
		this.name = name;
		lives = 10;
		size = tam;
		x = posx - (size * 3) / 2;
		y = posy - size * 3;
		this.cx = cx;
		this.cy = cy;
		vis=new String[]{"D", "R"};
		refresImage();
	}

	/**
	 * le quita vidas al jugador
	 * @param a el tipo de muerte que tiene el jugador
	 */
	public void lose(char a) {
		if (a == 'F'){
			resetPosition();
		lives--;}
		if (lives == 0) {
			this.representation = null;
			size = 0;
		}
		vis[0]="D";
		vis[1]="R";
		refresImage();
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
		vis[0]=b;
		vis[1]=a;
		if (lives > 0) {
			int velx = size * 2;
			int vely = size * 3;
			if (a.equals("L"))
				velx *= -1;
			if (b.equals("U"))
				vely *= -1;
			x += velx;
			y += vely;
			refresImage();
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
	private void refresImage() {
		image = new ImageIcon("resources/qbert" + color + "-" + vis[0] + vis[1] + ".png");
		this.representation = image.getImage().getScaledInstance(size * 3, size * 3, Image.SCALE_DEFAULT);
	}

	public int getLives() {
		return lives;
	}
	public String getName(){
		return name;
	}


}
