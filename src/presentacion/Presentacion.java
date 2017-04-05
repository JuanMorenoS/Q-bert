/**
 * 
 */
package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Juan Moreno
 *
 */
public class Presentacion extends JFrame {
	/* Presentacion */
	private JPanel panelImagen;
	private JPanel panelBoton;
	private JPanel panelDescripcion;
	/* Menu */
	private JMenuBar menu;
	private JMenu men;
	private JMenuItem jugar;
	private JMenuItem restart;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem exit;

	public Presentacion() {
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
		prepareMenuPrincipal();
		add(menu, BorderLayout.NORTH);
	}

	private void prepareMenuPrincipal() {
		menu = new JMenuBar();
		prepareMenuSecundario();
		menu.add(men);
	}

	private void prepareMenuSecundario() {
		men = new JMenu("Menu");
		restart = new JMenuItem("Restart");
		men.add(restart);
		open = new JMenuItem("Open");
		men.add(open);
		save = new JMenuItem("Save");
		men.add(save);
		exit = new JMenuItem("Exit");
		men.add(exit);
	}

	private void prepareAcciones() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				accionCerrar();
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionCerrar();

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

	private void centre() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xEsquina = (screen.width - getSize().width) / 2;
		int yEsquina = (screen.height - getSize().height) / 2;
		setLocation(xEsquina, yEsquina);
	}
}
