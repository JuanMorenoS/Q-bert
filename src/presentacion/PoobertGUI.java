/**
 * 
 */
package presentacion;

import java.awt.Color;

import javax.swing.*;

/**
 * @author blackphantom
 *
 */
public class PoobertGUI extends JFrame{
	
	public PoobertGUI(){
		super();
		prepareElementos();
		prepareAcciones();
	}

	private void prepareAcciones() {
		setTitle("Poo*Bert");
		setSize(600, 600);
	}

	private void prepareElementos() {
		UltraPanel u = new UltraPanel(this,"Red","Yellow");
		add(u);
		u.changeColor(Color.yellow);
		
	}
	
}