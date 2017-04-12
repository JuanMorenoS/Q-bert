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

	public UltraPanel(int b,JFrame god, String in, String fi, int x) {
		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		tam = x;
		color = new String[] { in, "lightGray", "darkGray", fi };
		tablero(b);
	}

	public void add(int x, int y) {
		land.get(x)[y].setColors(color,false);
	}

	public void changeColor(int x, int y) {
		land.get(x)[y].visited();
	}

	private void tablero(int b) {
		land = new ArrayList<>();
		for (int i = 0; i < b; i++) {
			if (i % 2 == 0)
				land.add(new Cube[b]);
			else
				land.add(new Cube[(b)]);
		}
		for (int i = 0; i < b; i++) {
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
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == 103){
			qbert.move('L','U');
		}
		if(key == 105){
			qbert.move('R','U');
		}
		if(key == 97){
			qbert.move('L','D');
		}
		if(key == 99){
			qbert.move('R','D');
		}
		changeColor(qbert.cy, qbert.cx);
	}

	public void setPlayer1(int i, int q) {
		int [] temo = land.get(i)[q].getCords();
		qbert = new Player(temo[0], temo[1],tam,q,i);
	}
}

