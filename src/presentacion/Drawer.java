/**
 * 
 */
package presentacion;

import java.util.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import aplicacion.Player;
import aplicacion.Poobert;

/**
 * @author blackphantom
 *
 */
public class Drawer extends JPanel implements ActionListener, KeyListener {
	private int size;
	private Timer time = new Timer(5, this);
	private PoobertGUI father;
	private Poobert logical;
	private ArrayList<Cube[]> cubeLand;
	private Player[] players;
	public void paintComponent(Graphics g) {
		setBackground(Color.black);
		super.paintComponent(g);
		for (Cube[] cs : cubeLand) {
			for (Cube u : cs) {
				for (int i = 0; i < 3; i++) {
					g.setColor(u.colors[i]);
					g.fillPolygon(u.edges[i]);
				}
			}
		}
		for (Player qbert : logical.getPlayers())
			if (qbert != null)
				g.drawImage(qbert.getDraw(), qbert.getX(), qbert.getY(), null);
	}

	public Drawer(PoobertGUI god) {

		time.start();
		father = god;
		logical = new Poobert(new String[] { father.getPlayer1Name(), father.getPlayer2Name() }, father.getSelection());
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		prepareTablero();
		father.setSize(size * (logical.getXLevel() * 4 + 2), size * (logical.getYLevel() * 2 + 10));
		new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						repaint();
						Random x = new Random();
						Random y = new Random();
						String xx = x.nextBoolean() ? "R" : "L";
						String yy = y.nextBoolean() ? "U" : "D";
						logical.move2(xx, yy);
						Thread.sleep(1000);
					}
				} catch (Exception e) {
				}

			}
		}).start();

	}

	private void prepareTablero() {
		int zoom = 20 * 3;
		size = zoom / 3;
		tablero(logical.getXLevel());
		for (int i = 0; i < logical.getSizeY(); i++) {
			for (int j=0;i<logical.getXLevel();j++) {
				char re=logical.getChar(j, i);
				if (re != 'x')
					add(i, j);
				if (re == 'Q')
					setPlayer1(i, j, father.getPlayer1Name());
				if (re == 'P' && father.getSelection()!= '1')
					setPlayer2(i, j, father.getPlayer2Name());
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == 103) {
			logical.move1("L", "U");
		}
		if (key == 105) {
			logical.move1("R", "U");
		}
		if (key == 97) {
			logical.move1("L", "D");
		}
		if (key == 99) {
			logical.move1("R", "D");
		}
		if (father.getSelection() != 'C') {
			if (key == 81) {
				logical.move2("L", "U");
			}
			if (key == 87) {
				logical.move2("R", "U");
			}
			if (key == 65) {
				logical.move2("L", "D");
			}
			if (key == 83) {
				logical.move2("R", "D");
			}
		}
		logical.printMat();
	}

	/**
	 * genera el tablero dada el tamaño en x
	 * 
	 * @param b
	 *            el tamaño en x del tablero
	 */
	private void tablero(int b) {
		cubeLand = new ArrayList<>();
		for (int i = 0; i < b; i++) 
			if (i % 2 == 0)
				cubeLand.add(new Cube[b]);
		for (int i = 0; i < b; i++) {
			for (int j = 0; j < cubeLand.get(i).length; j++) {
				if (i % 2 == 0) {
					cubeLand.get(i)[j] = (new Cube(size, null));
					cubeLand.get(i)[j].move(j * size * 4, i * size * 3);
				} else {
					cubeLand.get(i)[j] = (new Cube(size, null));
					cubeLand.get(i)[j].move((j * size * 4) + size * 2, i * size * 3);
				}
			}
		}
	}

	/**
	 * al cubo en la posicion x,y lo cambia a habitable
	 * 
	 * @param x
	 *            pos x
	 * @param y
	 *            pos y
	 */
	public void add(int x, int y) {
		cubeLand.get(x)[y].setColors(logical.getColors(), false);
	}
	
	/**
	 * setea el jugador dado su posicion inicial, y nombre
	 * 
	 * @param i
	 *            pos en x
	 * @param q
	 *            pos rn y
	 * @param name
	 *            nombre
	 */
	public void setPlayer1(int i, int q, String name) {
		int[] temo = cubeLand.get(i)[q].getCords();
		players[0] = new Player(temo[0], temo[1], size, q, i, name, 'n');
	}

	/**
	 * setea el jugador dado su posicion inicial, y nombre
	 * 
	 * @param i
	 *            pos en x
	 * @param q
	 *            pos rn y
	 * @param name
	 *            nombre
	 */
	public void setPlayer2(int i, int q, String name) {
		int[] temo = cubeLand.get(i)[q].getCords();
		players[1] = new Player(temo[0], temo[1], size, q, i, name, 'b');
	}
	
}
