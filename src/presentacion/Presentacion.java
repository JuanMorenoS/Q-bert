/**
 * 
 */
package presentacion;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

/**
 * @author Juan Moreno
 *
 */
public class Presentacion extends JFrame {
	/* Presentacion */
	private JPanel panelImagen;
	private JPanel panelBoton;
	private JPanel panelDescripcion;
	/*Botones*/
	private JButton botonStart;
	
	
	private Presentacion() {
		super();
		prepareElementos();
		prepareAcciones();
	}

	public static void main(String[] args) {
		Presentacion gui = new Presentacion();
		gui.setVisible(true);
	}

	private void prepareElementos() {
		setTitle("Mega Q*bert");
		setSize(600, 600);
		centre();
		elementosBoton();
		add(panelBoton,BorderLayout.SOUTH);
	}

	private void elementosBoton(){
		panelBoton= new JPanel();
		//panelBoton.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),new TitledBorder("juego")));
		panelBoton.setLayout(new GridLayout(3, 1));
		botonStart = new JButton("Start!");
		panelBoton.add(botonStart);
		
	}
	private void centre() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xEsquina = (screen.width - getSize().width) / 2;
		int yEsquina = (screen.height - getSize().height) / 2;
		setLocation(xEsquina, yEsquina);
	}
	/*acciones*/
	private void prepareAcciones() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				accionCerrar();
			}
		});
		botonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionStart();
			}
		});
	}
	
	private void accionCerrar() {
		int opcion = JOptionPane.showConfirmDialog(this, "Exit Q*Bert?", "Exit", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (opcion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	private void accionStart() {
		this.setVisible(false);
		Menu men = new Menu();
		men.setVisible(true);
	}

}
