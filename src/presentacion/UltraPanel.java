/**
 * 
 */
package presentacion;

import java.util.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.security.Key;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;
import javax.swing.Timer;

import java.awt.*;

/**
 * @author blackphantom
 *
 */
public class UltraPanel extends JPanel implements ActionListener,KeyListener{
	private Timer time = new Timer(5, this);
	private ArrayList<Cube[]> land;
	private String[] color;
	private int tam;
	private Player qbert;
	
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
		g.drawImage(qbert.getDraw(), qbert.x, qbert.y, null);
	}

	public UltraPanel(JFrame god, String in, String fi, int x) {
		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		tam = x;
		color = new String[] { in, "lightGray", "darkGray", fi };
		tablero();
		qbert = new Player(0,0);
	}

	public void add(int x, int y) {
		land.get(x)[y].setColors(color);
	}

	public void changeColor(int x, int y) {
		land.get(x)[y].visited();
	}

	private void tablero() {
		land = new ArrayList<>();
		for (int i = 0; i < (tam * 4); i++) {
			if (i % 2 == 0)
				land.add(new Cube[(tam * 4)]);
			else
				land.add(new Cube[((tam * 4)) - 1]);
		}
		for (int i = 0; i < (tam * 4); i++) {
			for (int j = 0; j < land.get(i).length; j++) {
				if (i % 2 == 0) {
					land.get(i)[j] = (new Cube(tam, null));
					land.get(i)[j].move(j * tam * 4, i * tam * 3);
				} else {
					land.get(i)[j] = (new Cube(tam, null));
					land.get(i)[j].move((j * tam * 4) + tam * 2, i * tam * 3);
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		repaint();		
	}

	public void keyTyped(KeyEvent e) {
		
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == 103){
			qbert.move(-1,-1);
		}
		if(key == 105){
			qbert.move(1,-1);
		}
		if(key == 97){
			qbert.move(-1,1);
		}
		if(key == 99){
			qbert.move(1,1);
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void setPlayer1(int i, int q) {
		int [] temo = land.get(i)[q].getCords();
		qbert.move(temo[0], temo[1]);
	}
}

