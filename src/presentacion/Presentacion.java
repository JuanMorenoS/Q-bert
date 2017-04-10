/**
 * 
 */
package presentacion;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


/**
 * @author Juan Moreno
 *
 */
public class Presentacion extends JFrame {
	/* Presentacion */
	private JPanel panelImagen;
	private JPanel panelBoton;
	private JPanel panelDescripcion;
	private JPanel footer;
	/*Botones*/	
	private JButton botonStart;
	private BufferedImage imagen;
	
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
		getContentPane().setBackground(Color.BLACK);
		setResizable(false);
		centre();
		elementosBoton();
		prepareCentro();
		prepareDescripcion();
		add(footer,BorderLayout.SOUTH);
		add(panelImagen,BorderLayout.CENTER);
		
	}
	private void prepareDescripcion() {
		panelDescripcion = new JPanel();
		panelDescripcion.setBackground(Color.black);
		JLabel text= new JLabel("<html><font color='yellow'>Escuela Colombiana de Ingenieria Julio Garavito <br> "
				+ "Made by: </font></html>");
		panelDescripcion.add(text);
		footer = new JPanel();
		footer.setBackground(Color.black);
		footer.setLayout(new FlowLayout());
		footer.add(panelDescripcion);
		footer.add(panelBoton);
	}
	private void prepareCentro(){
		panelImagen = new JPanel();
		panelImagen.setBackground(Color.black);
		panelImagen.setLayout(new FlowLayout());
		ImageIcon imagen = new ImageIcon("resources/Pbert-2.jpg");
		Image scaleimage =imagen.getImage().getScaledInstance(getWidth(),(getWidth()*imagen.getIconHeight())/imagen.getIconWidth(), Image.SCALE_SMOOTH);
		imagen = new ImageIcon(scaleimage);
		JLabel label = new JLabel("", imagen, JLabel.CENTER);
		panelImagen.add(label, BorderLayout.CENTER);
	}
	private void elementosBoton(){
		panelBoton= new JPanel();
		//panelBoton.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),new TitledBorder("juego")));
		panelBoton.setLayout(new GridLayout(3, 1));
		botonStart = new JButton("Start!");
		panelBoton.setBackground(Color.black);
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
