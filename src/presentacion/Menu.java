/**
 * 
 */
package presentacion;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame {
	/* Menu */
	private JMenuBar menu;
	private JMenu men;
	private JMenuItem jugar;
	private JMenuItem restart;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem exit;
	/* Botones*/
	private JButton start;
	/**/
	JPanel panelStart;
	public Menu() {
		super();
		prepareElementos();
		prepareAcciones();
	}

	private void prepareElementos() {
		setTitle("Mega Q*bert - Menu");
		setSize(600, 600);
		centre();
		prepareMenuPrincipal();
		add(menu, BorderLayout.NORTH);
		prepareStart();
		add(start,BorderLayout.SOUTH);

	}
	private void prepareStart(){
		panelStart= new JPanel();
		//panelBoton.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),new TitledBorder("juego")));
		panelStart.setLayout(new GridLayout(3, 1));
		start = new JButton("Start!");
		panelStart.add(start);
	}
	private void centre() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xEsquina = (screen.width - getSize().width) / 2;
		int yEsquina = (screen.height - getSize().height) / 2;
		setLocation(xEsquina, yEsquina);
	}

	/* acciones */
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
		start.addActionListener(new ActionListener() {
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
	private void accionStart(){
		this.setVisible(false);
		NameSelector name= new NameSelector();
		name.setVisible(true);
	}
	/* menu */
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
}
