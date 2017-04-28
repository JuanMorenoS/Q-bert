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

import aplicacion.Cube;
import aplicacion.Player;
import aplicacion.Poobert;


/**
 * @author blackphantom
 *
 */
public class Drawer extends JPanel implements ActionListener,KeyListener{
	private Timer time = new Timer(5, this);
	private PoobertGUI father;
	private Poobert logical;

	public void paintComponent(Graphics g) {
		setBackground(Color.black);
		super.paintComponent(g);
		for (Cube[] cs : logical.getLand()) {
			for (Cube u : cs) {
				for (int i = 0; i < 3; i++) {
					g.setColor(u.colors[i]);
					g.fillPolygon(u.edges[i]);
				}
			}
		}
		for (Player qbert:logical.getPlayers()) if(qbert!=null) g.drawImage(qbert.getDraw(), qbert.x, qbert.y, null);		
	}

	public Drawer(PoobertGUI god) {
		
		time.start();
		father=god;
		logical=new Poobert(new String[]{father.getPlayer1Name(),father.getPlayer2Name()},father.getSelection());
		father.setSize(logical.getSizeY(), logical.getSizeX());
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					while(true){
						repaint();
						Random x = new Random();
						Random y = new Random();
						String xx = x.nextBoolean()?"R":"L";
						String yy = y.nextBoolean()?"U":"D";
						logical.move2(xx,yy);
						Thread.sleep(1000);
					}
				}
				catch(Exception e){
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
		if(key == 103){
			logical.move1("L","U");
		}
		if(key == 105){
			logical.move1("R","U");
		}
		if(key == 97){
			logical.move1("L","D");
		}
		if(key == 99){
			logical.move1("R","D");
		}
		if(key == 81 && father.getSelection()!='C'){
			logical.move2("L","U");
		}
		if(key == 87 && father.getSelection()!='C'){
			logical.move2("R","U");
		}
		if(key == 65 && father.getSelection()!='C'){
			logical.move2("L","D");
		}
		if(key == 83 && father.getSelection()!='C'){
			logical.move2("R","D");
		}
	}

}

