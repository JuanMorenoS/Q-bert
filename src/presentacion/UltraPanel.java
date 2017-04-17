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
import java.lang.reflect.Field;
import java.security.Key;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;
import javax.swing.Timer;


/**
 * @author blackphantom
 *
 */
public class UltraPanel extends JPanel implements ActionListener,KeyListener{
	private Timer time = new Timer(5, this);
	private ArrayList<Cube[]> land;
	private String[] color;
	private int tam;
	private Player[] players;
	private PoobertGUI father;
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
		for (Player qbert:players) if(qbert!=null) g.drawImage(qbert.getDraw(), qbert.x, qbert.y, null);		
	}

	public UltraPanel(int b,PoobertGUI god, String in, String fi, int x) {
		time.start();
		father=god;
		players=new Player[2];
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
		if (!land.get(x)[y].visited())
			players[0].lose('F');
		
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
			players[0].move("L","U");
			changeColor(players[0].cy, players[0].cx);
		}
		if(key == 105){
			players[0].move("R","U");
			changeColor(players[0].cy, players[0].cx);
		}
		if(key == 97){
			players[0].move("L","D");
			changeColor(players[0].cy, players[0].cx);
		}
		if(key == 99){
			players[0].move("R","D");
			changeColor(players[0].cy, players[0].cx);
		}
		if(key == 81 && father.getSelection()!='C'){
			players[1].move("L","U");
			changeColor(players[1].cy, players[1].cx);
		}
		if(key == 87 && father.getSelection()!='C'){
			players[1].move("R","U");
			changeColor(players[1].cy, players[1].cx);
		}
		if(key == 65 && father.getSelection()!='C'){
			players[1].move("L","D");
			changeColor(players[1].cy, players[1].cx);
		}
		if(key == 83 && father.getSelection()!='C'){
			players[1].move("R","D");
			changeColor(players[1].cy, players[1].cx);
		}
	}

	public void setPlayer1(int i, int q,String name) {
		int [] temo = land.get(i)[q].getCords();
		players[0] = new Player(temo[0], temo[1],tam,q,i,name,'n');
	}
	public void setPlayer2(int i, int q,String name) {
		int [] temo = land.get(i)[q].getCords();
		players[1] = new Player(temo[0], temo[1],tam,q,i,name,'b');
	}
}

