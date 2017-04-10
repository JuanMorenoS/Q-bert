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

/**
 * @author blackphantom
 *
 */
public class PoobertGUI extends JFrame {
	private UltraPanel tablero;

	public PoobertGUI() {
		super();
		setLayout(new BorderLayout());
		prepareElementos();
		prepareAcciones();
	}

	private void prepareAcciones() {
		setTitle("Poo*Bert");
		setSize(600, 600);
		centre();
	}

	private void prepareElementos() {
		try {
			leerNivel();
		} catch (IOException e) {
		}
		add(tablero, BorderLayout.CENTER);
	}

	private void leerNivel() throws IOException {
		BufferedReader in;
		in = new BufferedReader(new FileReader("resources/Levels/1.level"));
		tablero = new UltraPanel(this, in.readLine(),in.readLine(), 10);
		int a=Integer.parseInt(in.readLine());
		for(int i=0;i<a;i++){
			int q=0;
			for(char j:in.readLine().toCharArray()){
				if(j!='X' && j!='x')
					tablero.add(i,q);
				q++;
			}
		}
		in.close();
	}

	private void centre() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xEsquina = (screen.width - getSize().width) / 2;
		int yEsquina = (screen.height - getSize().height) / 2;
		setLocation(xEsquina, yEsquina);
	}

}