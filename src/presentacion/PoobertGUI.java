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
			try {
				leerNivel();
			} catch (IOException e) {
			}
		add(tablero, BorderLayout.CENTER);
	}

	private void leerNivel() throws IOException {
		int zoom=20 *3;
		BufferedReader in;
		in = new BufferedReader(new FileReader("resources/Levels/2.level"));
		setTitle("Poo*Bert");
		int a=Integer.parseInt(in.readLine());
		int b=Integer.parseInt(in.readLine());
		setSize(b*zoom,a*zoom);
		centre();
		tablero = new UltraPanel(b,this, in.readLine(),in.readLine(),zoom/3);
		for(int i=0;i<a;i++){
			int q=0;
			for(char j:in.readLine().toCharArray()){ 
				if(j!='x')
					tablero.add(i,q);
					if(j=='Q')
						tablero.setPlayer1(i,q,father.getPlayer1Name());
					if(j=='P' && father.getSelection()!='1')
						tablero.setPlayer2(i,q,father.getPlayer2Name());
				if (j!='*')
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