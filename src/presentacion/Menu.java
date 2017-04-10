/**
 * 
 */
package presentacion;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.GapContent;

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
	/* Botones */
	private JButton play;
	/**/
	private JPanel panelStart;
	private JPanel cuerpo;
	private JPanel nameSpace;
	private JRadioButton human;
	private JRadioButton machine;
	private ButtonGroup group;
	private JTextField player1;
	private JTextField player2;
	private JComboBox box;
	public Menu() {
		super();
		prepareElementos();
		prepareAcciones();
	}

	private void prepareElementos() {
		setTitle("Mega Q*bert - Menu");
		setBackground(Color.black);
		setSize(600, 600);
		centre();
		prepareMenuPrincipal();
		add(menu, BorderLayout.NORTH);
		preparecuerpo();
		add(cuerpo, BorderLayout.CENTER);
		prepareStart();
		add(play, BorderLayout.SOUTH);

	}

	private void preparecuerpo() {
		cuerpo = new JPanel();
		cuerpo.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),
				new TitledBorder("<html><font color='white'> Game Options:<font></html>")));
		cuerpo.setBackground(Color.BLACK);
		cuerpo.setLayout(new GridLayout(7,1));
		human = new JRadioButton("<html><font color='white'> Human vs Human<font></html>");
		machine = new JRadioButton("<html><font color='white'> Human vs Machine<font></html>");
		human.setBackground(Color.black);
		machine.setBackground(Color.black);
		cuerpo.add(human, true);
		cuerpo.add(machine, false);
		group = new ButtonGroup();
		group.add(human);
		group.add(machine);
		human.setSelected(true);
		player1 = new JTextField(10);
		player2 = new JTextField(10);
		nameSpace= new JPanel();
		nameSpace.setBackground(Color.BLACK);
		nameSpace.setLayout(new GridLayout(2, 2));
		nameSpace.add(new JLabel("<html><font color='white'>Player 1 </font></html>"));
		nameSpace.add(player1);
		nameSpace.add(new JLabel("<html><font color='white'>Player 2 </font></html>"));
		nameSpace.add(player2);
		cuerpo.add(nameSpace);
		cuerpo.add(new JLabel("<html><font color='white'>Level</font></html>"));
		String [] ejemplo = new String[]{"Easy","Hard"};
		box = new JComboBox(ejemplo);
		cuerpo.add(box);
	}

	private void prepareStart() {
		panelStart = new JPanel();
		// panelBoton.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5,
		// 5),new TitledBorder("juego")));
		panelStart.setLayout(new FlowLayout());
		play = new JButton("Play");
		panelStart.add(play);
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
		play.addActionListener(new ActionListener() {
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
		setVisible(false);
		PoobertGUI vis = new PoobertGUI();
		vis.setVisible(true);
		//System.exit(0);
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
