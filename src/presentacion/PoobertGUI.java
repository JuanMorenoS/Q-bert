/**
 * 
 */
package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author blackphantom
 *
 */
public class PoobertGUI extends JFrame {
	private JMenuBar menu;
	private JMenu men;
	private JMenuItem jugar;
	private JMenuItem restart;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem exit;
	private JFileChooser explorer;

	private Drawer tablero;
	private Menu father;
	private JPanel p1;
	private JPanel p2;
	private JLabel vidas1, puntos1, vidas2, puntos2, poderes1, poderes2;

	public PoobertGUI(Menu god) {
		super();
		father = god;
		setLayout(new BorderLayout());
		prepareElementos();
		prepareAcciones();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void prepareAcciones() {
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guarde();
			}
		});
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccione();
			}
		});
	}

	private void prepareElementos() {
		setTitle("Poo*Bert");
		add(tablero = new Drawer(this), BorderLayout.CENTER);
		prepareMenuPrincipal();
		add(menu, BorderLayout.NORTH);
		centre();
		prepareP1();
		add(p1, BorderLayout.WEST);
		prepareP2();
		add(p2, BorderLayout.EAST);
	}

	private void prepareP1() {
		p1 = new JPanel();
		p1.setLayout(new GridLayout(4, 1));
		p1.add(new JLabel("Player 1 "));
		p1.add(vidas1 = new JLabel("Vidas: " + tablero.getVidas1()));
		p1.add(puntos1 = new JLabel("Puntos: " + tablero.getPuntos1()));
		p1.add(poderes1 = new JLabel("Poder: " + tablero.getPoderes1()));

	}

	private void prepareP2() {
		p2 = new JPanel();
		p2.setLayout(new GridLayout(4, 1));
		p2.add(new JLabel("Player 2 "));
		p2.add(vidas2 = new JLabel("Vidas: " + tablero.getVidas2()));
		p2.add(puntos2 = new JLabel("Puntos: " + tablero.getPuntos2()));
		p2.add(poderes2 = new JLabel("Poder: " + tablero.getPoderes2()));
	}

	public void refresque1() {
		vidas1.setText("Vidas: " + tablero.getVidas1());
		puntos1.setText("Puntos: "+tablero.getPuntos1());
		poderes1.setText("Podere: "+tablero.getPoderes1());
	}

	public void refresque2() {
		vidas2.setText("Vidas: " + tablero.getVidas2());
		puntos2.setText("Puntos: "+tablero.getPuntos2());
		poderes2.setText("Podere: "+tablero.getPoderes2());

	}

	private void centre() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xEsquina = (screen.width - getSize().width) / 2;
		int yEsquina = (screen.height - getSize().height) / 2;
		setLocation(xEsquina, yEsquina);
	}

	public char getSelection() {
		return father.getSelection();
	}

	public String getPlayer1Name() {
		return father.getPlayer1Name();
	}

	public String getPlayer2Name() {
		return father.getPlayer1Name();
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

	private void guarde() {
		explorer = new JFileChooser();
		explorer.setDialogTitle("Save...");
		if (explorer.showSaveDialog(open) == JFileChooser.APPROVE_OPTION) {
			File file = new File(explorer.getSelectedFile().getAbsolutePath() + ".dat");
			tablero.save(file);
		}
	}

	private void seleccione() {
		explorer = new JFileChooser();
		explorer.setDialogTitle("Open...");
		explorer.setFileSelectionMode(JFileChooser.FILES_ONLY);
		explorer.setFileFilter(new FileNameExtensionFilter("dat", "dat"));
		if (explorer.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			tablero.open(explorer.getSelectedFile());
		}
	}

	public char getHard() {
		System.out.println(father.getHard());
		return father.getHard().equals("Hard") ? 'H' : 'E';
	}

	public String[] getHelps() {
		String[] res = new String[father.getHelpSelection().size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = father.getHelpSelection().get(i);
		}
		return res;
	}

	public String[] getEnem() {
		String[] res = new String[father.getEnemSelection().size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = father.getEnemSelection().get(i);
		}
		return res;
	}

	public String getColor1() {
		return father.getColor1();
	}

	public String getColor2() {
		return father.getColor2();
	}

	public String getMachineMode() {
		return father.getMachineMode();
	}

}