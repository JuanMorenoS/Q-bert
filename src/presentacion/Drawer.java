/**
 * 
 */
package presentacion;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.Timer;

import logicalT.Poobert;

/**
 * @author blackphantom
 *
 */
public class Drawer extends JPanel implements ActionListener, KeyListener {
	private Timer time = new Timer(5, this);
	private PoobertGUI father;
	private Poobert logic;

	public void paintComponent(Graphics g) {
		setBackground(Color.black);
		
	}

	public Drawer(PoobertGUI god) {
		time.start();
		father = god;
		logic = new Poobert(new String[] { father.getPlayer1Name(), father.getPlayer2Name() }, father.getSelection());
		//father.setSize(logic.getSizeY(), logic.getSizeX());
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
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
			logic.movePlayer1("L", "U");
		}
		if (key == 105) {
			logic.movePlayer1("R", "U");
		}
		if (key == 97) {
			logic.movePlayer1("L", "D");
		}
		if (key == 99) {
			logic.movePlayer1("R", "D");
		}
		if (father.getSelection() != 'C') {
			if (key == 81) {
				logic.movePlayer2("L", "U");
			}
			if (key == 87) {
				logic.movePlayer2("R", "U");
			}
			if (key == 65) {
				logic.movePlayer2("L", "D");
			}
			if (key == 83) {
				logic.movePlayer2("R", "D");
			}
		}
	}

}
