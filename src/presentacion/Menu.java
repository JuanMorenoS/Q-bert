/**
 * 
 */
package presentacion;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.GapContent;

import logicalT.Snake;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
	private JRadioButton onePlayer;
	private ButtonGroup group;
	private JTextField player1;
	private JTextField player2;
	private JComboBox box,color1,color2,machineMode;
	private JCheckBox ugg,megaBall,snake,ultraSpeed,energyBall,mine,Switch,UltraShield;
	private JPanel comboxEnem,comboxHelp;
	public Menu() {
		super();
		prepareElementos();
		prepareAcciones();
	}

	private void prepareElementos() {
		setTitle("Mega Q*bert - Menu");
		setBackground(Color.black);
		setSize(800, 1000);
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
		cuerpo.setLayout(new GridLayout(9,1,5,5));
		onePlayer = new JRadioButton("<html><font color='white'> Only 1 Player<font></html>");
		human = new JRadioButton("<html><font color='white'> Human vs Human<font></html>");
		machine = new JRadioButton("<html><font color='white'> Human vs Machine<font></html>");
		human.setBackground(Color.black);
		machine.setBackground(Color.black);
		onePlayer.setBackground(Color.black);
		cuerpo.add(onePlayer,false);
		cuerpo.add(human, true);
		cuerpo.add(machine, false);
		group = new ButtonGroup();
		group.add(onePlayer );
		group.add(human);
		group.add(machine);
		onePlayer.setSelected(true);
		player1 = new JTextField(10);
		player2 = new JTextField(10);
		nameSpace= new JPanel();
		String [] colors = new String[]{"Orange","Blue"};
		nameSpace.setBackground(Color.BLACK);
		nameSpace.setLayout(new GridLayout(3,3,3,3));
		nameSpace.add(new JLabel("<html><font color='white'>Player 1 </font></html>"));
		nameSpace.add(player1);
		nameSpace.add(color1=new JComboBox<String>(colors));
		nameSpace.add(new JLabel("<html><font color='white'>Player 2 </font></html>"));
		nameSpace.add(player2);
		nameSpace.add(color2=new JComboBox<String>(colors));
		player2.setVisible(false);
		color2.setVisible(false);
		nameSpace.add(new JLabel("<html><font color='white'>Machine Mode </font></html>"));
		String [] modes = new String[]{"Timido","Ofensivo","Irreflexivo"};
		nameSpace.add(machineMode= new JComboBox<String>(modes));
		machineMode.setVisible(false);
		cuerpo.add(nameSpace);	
		cuerpo.add(new JLabel("<html><font color='white'>Level</font></html>"));
		String [] ejemplo = new String[]{"Easy","Hard"};
		box = new JComboBox<String>(ejemplo);
		cuerpo.add(box);
		comboxEnem= new JPanel(new GridLayout(1, 3));
		comboxHelp= new JPanel(new GridLayout(1, 5));
		comboxEnem.add(snake = new JCheckBox("Snake"));
		comboxEnem.add(ugg = new JCheckBox("Ugg"));
		comboxEnem.add(megaBall= new JCheckBox("Mega Ball"));
		cuerpo.add(comboxEnem);
		comboxHelp.add(UltraShield=new JCheckBox("Ultra Shield"));
		comboxHelp.add(ultraSpeed= new JCheckBox("Ultra Speed"));
		comboxHelp.add(Switch=new JCheckBox("Switch"));
		comboxHelp.add(mine = new JCheckBox("Mine"));
		comboxHelp.add(energyBall = new JCheckBox("Energy Ball"));
		cuerpo.add(comboxHelp);
	}
	public void getLevel(){
		//return 
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
		machine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				machineMode.setVisible(true);
				player2.setVisible(true);
				color2.setVisible(true);
			}
		});
		human.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player2.setVisible(true);
				color2.setVisible(true);
				machineMode.setVisible(false);
			}
		});
		onePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				machineMode.setVisible(false);
				player2.setVisible(false);
				color2.setVisible(false);
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
		PoobertGUI vis = new PoobertGUI(this);
		vis.setVisible(true);
	}
	public String getPlayer1Name(){
		return player1.getText();
	}
	public String getPlayer2Name(){
		return player2.getText();
	}
	/* menu */
	private void prepareMenuPrincipal() {
		menu = new JMenuBar();
		prepareMenuSecundario();
		menu.add(men);
	}
	public char getSelection(){
		char res;
		if(onePlayer.isSelected())
			res='1';
		else if(human.isSelected())
			res='2';
		else
			res='C';
		return res;
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
	public String getHard(){
		return (String) box.getSelectedItem();
	}
	public ArrayList<String> getHelpSelection(){
		ArrayList<String> res = new ArrayList<>();
		if(Switch.isSelected()) res.add("Switch");
		if(mine.isSelected()) res.add("Mine");
		if(energyBall.isSelected()) res.add("EnergyBall");
		if(UltraShield.isSelected()) res.add("UltraShield");
		if(ultraSpeed.isSelected()) res.add("UltraSpeed");
		return res;
	}
	public ArrayList<String> getEnemSelection(){
		ArrayList<String> res = new ArrayList<>();
		if(ugg.isSelected()) res.add("Ugg");
		if(snake.isSelected()) res.add("Snake");
		if(megaBall.isSelected()) res.add("MegaBall");
		return res;
	}
	public String getColor1(){
		return (String) color1.getSelectedItem();
	}
	public String getColor2(){
		return (String) color2.getSelectedItem();
	}
	public String getMachineMode(){
		return (String) machineMode.getSelectedItem();
	}
}
