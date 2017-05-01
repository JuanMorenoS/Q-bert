package aplicacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Poobert {
	
	private String[] color;
	private Player[] players;
	private int level, yLevel, xLevel;
	private String[] playersNames;
	private char selection;
	private char charLand[][];

	public Poobert(String[] strings, char selection) {
		level = 2;
		players = new Player[2];
		playersNames = strings;
		this.selection = selection;
		try {
			leerNivel(level);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * cambia el color en la posicioin que le jugador esta
	 * 
	 * @param play
	 *            el jugador
	 */
	public void changeColor(Player play) {
		if (!land.get(play.cy)[play.cx].visited())
			play.lose('F');

	}

	

	/**
	 * lee un mundo dado el nivel en numero
	 * 
	 * @param le
	 *            el nivel
	 * @throws IOException
	 */
	private void leerNivel(int le) throws IOException {
		BufferedReader in;
		in = new BufferedReader(new FileReader("resources/Levels/" + le + ".level"));
		yLevel = Integer.parseInt(in.readLine());
		xLevel = Integer.parseInt(in.readLine());
		charLand = new char[yLevel][xLevel];
		color = new String[] { in.readLine(), "lightGray", "darkGray", in.readLine() };
		for (int i = 0; i < yLevel; i++) {
			int q = 0;
			for (char j : in.readLine().trim().toCharArray()) {
				charLand[i][q] = j;
			}
		}
		in.close();
	}

	/**
	 * mueve el jugador 1
	 * 
	 * @param a
	 *            en x
	 * @param b
	 *            en y
	 */
	public void move1(String a, String b) {
		System.out.println(getChar(4, 1));
		charLand[players[0].cy][players[0].cx]='c';
		if (players[0].move(a, b))
			changeColor(players[0]);
		charLand[players[0].cy][players[0].cx]='Q';
	}

	/**
	 * mueve el jugador 2
	 * 
	 * @param a
	 *            en x
	 * @param b
	 *            en y
	 */
	public void move2(String a, String b) {
		charLand[players[1].cy][players[1].cx]='c';
		if (players[1].move(a, b))
			changeColor(players[1]);
		charLand[players[1].cy][players[1].cx]='P';
	}

	/**
	 * @return devuelve los jugadores
	 */
	public Player[] getPlayers() {
		return players;
	}

	public char getChar(int x, int y){
		return charLand[y][x];
	}
	public int getYLevel(){
		return yLevel;
	}
	public int getXLevel(){
		return xLevel;
	}



	public String[] getColors() {
		return color;
	}
	
	/* debug */
	public void printMat() {
		for (char[] a : charLand) {
			System.out.println(Arrays.toString(a));
		}
		System.out.println('\n');
	}
	
	
	
	
	
	
	
}
