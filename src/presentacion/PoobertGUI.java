/**
 * 
 */
package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
			File file = new File(explorer.getSelectedFile().getAbsolutePath()+".dat");
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
	public char getHard(){
		System.out.println(father.getHard());
		return father.getHard().equals("Hard")?'H':'E';
	}
	public String[] getHelps(){
		String[] res = new String[father.getHelpSelection().size()];
		for(int i=0;i<res.length;i++){
			res[i]=father.getHelpSelection().get(i);
		}
		return res;
	}
	public String[] getEnem(){
		String[] res = new String[father.getEnemSelection().size()];
		for(int i=0;i<res.length;i++){
			res[i]=father.getEnemSelection().get(i);
		}
		return res;
	}
	public String getColor1(){
		return father.getColor1();
	}
	public String getColor2(){
		return father.getColor2();
	}
	public String getMachineMode(){
		return father.getMachineMode();
	}
	
}