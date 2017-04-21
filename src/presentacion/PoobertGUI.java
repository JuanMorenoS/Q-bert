/**
 * 
 */
package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import javax.swing.*;

import aplicacion.Poobert;

/**
 * @author blackphantom
 *
 */
public class PoobertGUI extends JFrame {
	private Drawer tablero;
	private Menu father;
	public PoobertGUI(Menu god) {
		super();
		father=god;
		setLayout(new BorderLayout());
		prepareElementos();
		prepareAcciones();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	private void prepareAcciones() {
	}

	private void prepareElementos() {
			setTitle("Poo*Bert");
			add(tablero=new Drawer(this), BorderLayout.CENTER);
			centre();
	}


	private void centre() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xEsquina = (screen.width - getSize().width) / 2;
		int yEsquina = (screen.height - getSize().height) / 2;
		setLocation(xEsquina, yEsquina);
	}
	public char getSelection(){
		return father.getSelection();
	}
	public String getPlayer1Name() {
		return father.getPlayer1Name();
	}
	public String getPlayer2Name() {
		return father.getPlayer1Name();
	}
	
}