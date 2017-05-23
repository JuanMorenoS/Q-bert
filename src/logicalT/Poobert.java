package logicalT;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Poobert implements Serializable {
	private Player[] players;
	private Mobile[][] mobiles;
	private Land[][] land;
	private Static[][] StaticObjects;
	private int level, yLevel, xLevel, totalC;
	private double seconds;
	private String[] colors, playerNames;
	private char selection;
	private String[] posibleMobileElements = new String[] { "Ugg", "MegaBallMove", "Snake" };
	// private String[] posibleStaticElements = new
	// String[]{"UltraSpeed","UltraShield","Mine","EnergyBall","Switch"};
	private String[] posibleStaticElements = new String[] { "Mine", "EnergyBall" };

	/**
	 * @param names
	 *            los nombre de los jugadores
	 * @param selection
	 *            la seleccion de modalida de juegdo
	 */
	public Poobert(String[] names, char selection) {
		this.selection = selection;
		level = 1;
		players = new Player[2];
		playerNames = names;
		try {
			readLevel();
		} catch (IOException e) {
		}
		startThread();

	}

	public void startThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						nextFrame();
						seconds += 0.5;
						Thread.sleep(500);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

	/**
	 * @throws IOException
	 *             si no existen los archivos ....
	 */
	private void readLevel() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("resources/Levels/" + level + ".level"));
		yLevel = Integer.parseInt(in.readLine());
		xLevel = Integer.parseInt(in.readLine());
		mobiles = new Mobile[yLevel][xLevel];
		land = new Land[yLevel][xLevel];
		StaticObjects = new Static[yLevel][xLevel];
		Mobile.setLogic(this);
		colors = new String[] { in.readLine(), "lightGray", "darkGray", in.readLine() };
		for (int i = 0; i < yLevel; i++) {
			int j = 0;
			for (char c : in.readLine().trim().toCharArray()) {
				land[i][j] = (c != 'x') ? new GoodCube(colors) : new BadCube();
				totalC = (c != 'x') ? totalC + 1 : totalC;
				if (c == 'Q')
					mobiles[i][j] = players[0] = new Human(i, j, playerNames[0]);
				if (c == 'P' && selection != '1')
					mobiles[i][j] = players[1] = selection == '2' ? new Human(i, j, playerNames[1])
							: new Machine(i, j, playerNames[1]);
				j++;
			}
		}
		in.close();
	}

	/**
	 * @return los colores del juego
	 */
	public String[] getColors() {
		return colors;
	}

	/**
	 * @param y
	 *            coordenada en y de un Mobile
	 * @param x
	 *            coordenada en x de un Mobile
	 * @return la representacion en String
	 */
	public String getMobile(int y, int x) {
		return mobiles[y][x] == null ? "0" : mobiles[y][x].toString();
	}

	/**
	 * @param y
	 *            coordenada en y de un Land
	 * @param x
	 *            coordenada en x de un LAnd
	 * @return la representacion en String
	 */
	public String getLand(int y, int x) {
		return land[y][x].toString();
	}

	/**
	 * @param i
	 *            coordenada en y de un Static
	 * @param j
	 *            coordenada en x de un Static
	 * @return la representacion en String
	 */
	public String getStaticObjects(int i, int j) {
		return StaticObjects[i][j] == null ? "0" : StaticObjects[i][j].toString();
	}

	/**
	 * @param string
	 *            el movimiento en x
	 * @param string2
	 *            el movimiento en y
	 */
	public void movePlayer1(String string, String string2) {
		int[] step = players[0].Premove(string, string2);
		if (!isBad(step[0], step[1])) {
			moveObject(players[0].getCoords(), string, string2);
			land[step[1]][step[0]].visited();
			if (StaticObjects[step[1]][step[0]] instanceof Help) {
				StaticObjects[step[1]][step[0]].use(players[0]);
			}
		} else {
			players[0].lose();
			// destroyMobile(step[0], step[1]);
		}
		destroyStatic(step[0], step[1]);
	}

	/**
	 * @param string
	 *            el movimiento en x
	 * @param string2
	 *            el movimiento en y
	 */
	public void movePlayer2(String string, String string2) {
		if (!string.equals("C")) {
			int[] step = players[1].Premove(string, string2);
			if (!isBad(step[0], step[1])) {
				moveObject(players[1].getCoords(), string, string2);
				land[step[1]][step[0]].visited();
			}
		} else {
			players[1].move();
		}
	}

	/**
	 * @param el
	 *            pre movimiento
	 * @param a
	 *            el lado para el que se mueve
	 * @param b
	 *            el lado para el que se mieve
	 */
	public void moveObject(int[] pre, String a, String b) {
		int[] pos = mobiles[pre[1]][pre[0]].Premove(a, b);
		if (land[pos[1]][pos[0]] instanceof BadCube && mobiles[pre[1]][pre[0]].getLive() <= 2)
			destroyMobile(pre[1], pre[0]);
		else {
			mobiles[pos[1]][pos[0]] = mobiles[pre[1]][pre[0]];
			mobiles[pre[1]][pre[0]] = null;
			mobiles[pos[1]][pos[0]].move(a, b);
		}

	}

	/**
	 * destruye un !!mobile no esta termiando
	 * 
	 * @param i
	 *            la pos en y
	 * @param j
	 *            la pos en x
	 */
	public void destroyMobile(int i, int j) {
		mobiles[i][j] = null;
	}

	/**
	 * si es posible el jugador1 ataca
	 */
	public void player1Attack() {
		int[] temp = players[0].Premove(players[0].getDirx(), players[0].getDiry());
		if (players[0].haveAttack()) {
			if (land[temp[1]][temp[0]] instanceof GoodCube) {
				try {
					mobiles[temp[1]][temp[0]] = (Mobile) Class.forName("logicalT." + players[0].usePower())
							.getConstructor(Player.class).newInstance(players[0]);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException
						| ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				players[0].addPower(null);
			}
		}
	}

	/**
	 * si es posible el jugador2 ataca
	 */
	public void player2Attack() {
		if (players[1].haveAttack()) {
			int[] temp = players[1].Premove(players[1].getDirx(), players[1].getDiry());
			if (land[temp[1]][temp[0]] instanceof GoodCube)
				mobiles[temp[1]][temp[0]] = new EnergyBallMove(players[0]);
		}
	}

	/**
	 * me da la siguiente esena del juego
	 */
	public void nextFrame() {
		ArrayList<Mobile> temp = new ArrayList<>();
		for (Mobile[] a : mobiles)
			for (Mobile b : a)
				if (b != null)
					temp.add(b);
		for (Mobile b : temp) {
			b.move();
		}
		if ((seconds + 1) % 2 == 0)
			putRandomStaticObject();
	}

	public static int[] getRandomNumbers(int x, int y) {
		Random a = new Random();
		return new int[] { a.nextInt(x) + 1, a.nextInt(y) + 1 };
	}

	/**
	 * mira si una zona es mala
	 * 
	 * @param x
	 *            la zona en x
	 * @param y
	 *            la zona en y
	 * @return si es mala
	 */
	public boolean isBad(int x, int y) {
		return (isStaticBad(x, y) || isMobileBad(x, y));
	}

	/**
	 * si una zona estatica es mala
	 * 
	 * @param x
	 *            la zona estatica en x
	 * @param y
	 *            la zona estatica en y
	 * @return si es mala
	 */
	public boolean isStaticBad(int x, int y) {
		return (land[y][x] instanceof BadCube || StaticObjects[y][x] instanceof Bad);
	}

	/**
	 * si una zona estatica es mala
	 * 
	 * @param x
	 *            la zona estatica en x
	 * @param y
	 *            la zona estatica en y
	 * @return si es mala
	 */
	public boolean isMobileBad(int x, int y) {
		return (mobiles[y][x] != null);
	}

	/**
	 * @return el xLevel
	 */
	public int getXLevel() {
		return xLevel;
	}

	/**
	 * @return el Ylevel
	 */
	public int getYLevel() {
		return yLevel;
	}

	/**
	 * destruye un statico
	 * 
	 * @param i
	 *            la pos en y
	 * @param j
	 *            la pos en x
	 */
	public void destroyLand(int i, int j) {
		land[i][j] = new GoodCube(colors);
	}

	public void destroyStatic(int j, int i) {
		StaticObjects[i][j] = null;
	}

	/**
	 * pone objetos estaticos al azar
	 */
	public void putRandomStaticObject() {
		Random ran = new Random();
		int a = ran.nextInt(posibleMobileElements.length + 1);
		int x = ran.nextInt(xLevel);
		int y = ran.nextInt(yLevel);
		while (isStaticBad(x, y) || mobiles[y][x] != null) {
			y = ran.nextInt(yLevel);
			x = ran.nextInt(xLevel);
		}
		try {
			StaticObjects[y][x] = (Static) Class.forName("logicalT." + posibleStaticElements[a]).getConstructor()
					.newInstance();
		} catch (Exception e) {
		}
	}

	/**
	 * pone objetos mobiles al azar
	 */
	public void putRandomMobileObject() {
		Random ran = new Random();
		int a = ran.nextInt(posibleMobileElements.length + 1);
		int y = ran.nextInt(xLevel + 1);
		int x = ran.nextInt(yLevel + 1);
		while (isStaticBad(x, y) && mobiles[y][x] == null) {
			y = ran.nextInt(xLevel + 1);
			x = ran.nextInt(yLevel + 1);
		}
		try {
			mobiles[y][x] = (Mobile) Class.forName("logicalT." + posibleMobileElements[a]).getConstructor()
					.newInstance();
		} catch (Exception e) {

		}
	}

	public void save(File file) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(this);
			out.close();
		} catch (IOException e) {

		}
	}

	public Poobert open(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ob = new ObjectInputStream(new FileInputStream(file));
		Poobert temp = (Poobert) ob.readObject();
		ob.close();
		return temp;
	}

	/* debug */
	/**
	 * 
	 */
	public void printMats() {
		for (int i = 0; i < yLevel; i++) {
			for (Mobile a : mobiles[i]) {
				System.out.print(a != null ? a.getClass().getName().charAt(9) + " " : 0 + " ");
			}
			System.out.print("---- ");
			for (Land a : land[i]) {
				System.out.print(a instanceof BadCube ? "x " : "c ");
			}
			System.out.print("---- ");
			for (Static a : StaticObjects[i]) {
				System.out.print(a != null ? a.getClass().getName().charAt(9) + " " : 0 + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
/*
 * try{ edicion
 * =(Normal)Class.forName("ICPC."+tipo).getConstructor().newInstance(); }
 * catch(Exception e){ } } }
 * 
 * 
 */
