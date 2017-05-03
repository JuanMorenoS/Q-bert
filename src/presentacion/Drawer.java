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
	private Timer time = new Timer(5, this);
	private PoobertGUI father;
	private Poobert logical;
	private ArrayList<Cube[]> land;
	private int size,tamx,tamy;
	private String[] color;
	private Player[] players;
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
		for (Player qbert : logical.getPlayers())
			if (qbert != null)
				g.drawImage(qbert.getDraw(), qbert.getX(), qbert.getY(), null);
	}

	public Drawer(PoobertGUI god) {
		players = new Player[2];
		time.start();
		father = god;
		makeLand();
		father.setSize(logical.getSizeY(), logical.getSizeX());
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						repaint();
						Random x = new Random();
						Random y = new Random();
						String xx = x.nextBoolean() ? "R" : "L";
						String yy = y.nextBoolean() ? "U" : "D";
						logical.move2(xx, yy);
						Thread.sleep(500);
					}
				} catch (Exception e) {
				}

			}
		}).start();

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

	
	
	/*refact*/
	private void tablero(int b) {
		land = new ArrayList<>();
		for (int i = 0; i < b; i++) {
				land.add(new Cube[b]);
		}
		for (int i = 0; i < b; i++) {
			for (int j = 0; j < land.get(i).length; j++) {
				if (i % 2 == 0) {
					land.get(i)[j] = (new Cube(size, null));
					land.get(i)[j].move(j * size * 4, i * size * 3);
				} else {
					land.get(i)[j] = (new Cube(size, null));
					land.get(i)[j].move((j * size * 4) + size * 2, i * size * 3);
				}
			}
		}
	}
	
	private void add(int x, int y) {
		land.get(x)[y].setColors(color, false);
	}
	private void changeColor(Player play) {
		if (!land.get(play.getCy())[play.getCx()].visited())
			play.lose('F');
	}
	public void setPlayer1(int i, int q, String name) {
		int[] temo = land.get(i)[q].getCords();
		players[0] = new Player(temo[0], temo[1], size, q, i, name, 'n');
	}
	public void setPlayer2(int i, int q, String name) {
		int[] temo = land.get(i)[q].getCords();
		players[1] = new Player(temo[0], temo[1], size, q, i, name, 'b');
	}
	private void makeLand(){
		logical = new Poobert(new String[] { father.getPlayer1Name(), father.getPlayer2Name() }, father.getSelection());
		tamx=logical.getXLevel();
		tamy=logical.getYLevel();
		color=logical.getColor();
		tablero(tamx);
		for (int i = 0; i < tamy; i++) {
			for (int k =0 ;k<tamx;k++ ){
				 char j = logical.getChar(i, k);
				if (j != 'x')
					add(i, k);
				if (j == 'Q')
					setPlayer1(i, k, father.getPlayer1Name());
				if (j == 'P' && father.getSelection() != '1')
					setPlayer2(i, k, father.getPlayer2Name());
			}
		}
	}
	
	
}
