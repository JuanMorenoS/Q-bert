/**
 * 
 */
package presentacion;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import logicalT.Poobert;

/**
 * @author blackphantom
 *
 */
public class Drawer extends JPanel implements ActionListener, KeyListener,Serializable {
	private Timer time = new Timer(5, this);
	private PoobertGUI father;
	private Poobert logic;
	private ArrayList<Cube[]> land;
	private int size, xLevel, yLevel;
	private String[] color;
	
	/**
	 * Le asigna los respectivos colores a un elemento del escenario
	 */
	public void paintComponent(Graphics g) {
		setBackground(Color.black);
		super.paintComponent(g);
		for (int i = 0; i < yLevel; i++) {
			for (int j = 0; j < xLevel; j++) {
				for (int k = 0; k < 3; k++) {
					if (k == 0)
						g.setColor(stringToColor(logic.getLand(i, j)));
					else
						g.setColor(land.get(i)[j].colors[k]);
					g.fillPolygon(land.get(i)[j].edges[k]);
				}
			}
		}
		
		for (int i = 0; i < yLevel; i++) {
			for (int j = 0; j < xLevel; j++) {
				if (!logic.getMobile(i, j).equals("0")) {
					try {
						g.drawImage(
								ImageIO.read(new File("resources/" + logic.getMobile(i, j) + ".png"))
										.getScaledInstance(size * 3, size * 3, Image.SCALE_SMOOTH),
								realCoordX(i, j), realCoordY(i, j), null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
	/**
	 * Constructor del dibujador
	 * @param god una instancia de la clase GUI principal
	 */
	public Drawer(PoobertGUI god) {
		father = god;
		time.start();
		size = 30;
		makePlaySpace();
		father.setSize(size * (xLevel * 4 + 2), size * (yLevel * 2 + 10));
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
	}
	/**
	 * Da la coordenada en X
	 * @param i indice de filas de la matriz
	 * @param j indice de columnas de la matriz
	 * @return la coordenada en X
	 */
	private int realCoordX(int i, int j) {
		int[] temo = land.get(i)[j].getCords();
		return temo[0] - (size * 3) / 2;
	}
	/**
	 * Da la coordenada en Y
	 * @param i indice de filas de la matriz
	 * @param j indice de columnas de la matriz
	 * @return la coordenada en Y
	 */
	private int realCoordY(int i, int j) {
		int[] temo = land.get(i)[j].getCords();
		return temo[1] - (size * 3);
	}
	/**
	 * Oyente para que dibuje un componente
	 */
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void keyTyped(KeyEvent e) {

	}
	
	public void keyPressed(KeyEvent e) {

	}
	/**
	 * Oyente para dibujar el movimiento de los jugadores
	 */
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
		if (key == 32) {
			logic.player1Attack();
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
		logic.printMats();
	}
	/**
	 * Dibuja el tablero 
	 * @param b pivote limite
	 */
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
	/**
	 * Agrega el cubo
	 * @param x coordenada x
	 * @param y coordenada y
	 */
	private void add(int x, int y) {
		land.get(x)[y].setColors(color, false);
	}
	/**
	 * Cambia una cadena de color a un Color 
	 * @param arg la cadena de color
	 * @return el color de tipo Color
	 */
	public static Color stringToColor(String arg) {
		Color color = null;
		try {
			Field field = Class.forName("java.awt.Color").getField(arg);
			color = (Color) field.get(null);
		} catch (Exception e) {
		}
		return color;
	}
	/**
	 * Hace el tablero
	 */
	private void makePlaySpace() {
		logic = new Poobert(new String[] { father.getPlayer1Name(), father.getPlayer2Name() }, father.getSelection());
		xLevel = logic.getXLevel();
		yLevel = logic.getYLevel();
		color = logic.getColors();
		tablero(xLevel);
		for (int i = 0; i < yLevel; i++) {
			for (int j = 0; j < xLevel; j++) {
				if (!logic.getLand(i, j).equals("black"))
					add(i, j);
			}
		}
	}
	public void save(File file){
		logic.save(file);
	}
	public void open(File file){
		try {
			logic = logic.open(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logic.printMats();
		//makePlaySpace();
	}
}
