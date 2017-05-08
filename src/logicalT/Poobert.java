package logicalT;

import java.io.*;


public class Poobert {
	private static Player[] players;
	private static Mobile[][] mobiles;
	private static Static[][] statics;
	private int level, yLevel, xLevel;
	private String[] colors, playerNames;
	private char selection;

	public Poobert(String[] names, char selection) {
		this.selection = selection;
		level = 1;
		players = new Player[2];
		playerNames = names;
		try {
			readLevel();
		} catch (IOException e) {
		}
	}

	private void readLevel() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("resources/Levels/" + level + ".level"));
		yLevel = Integer.parseInt(in.readLine());
		xLevel = Integer.parseInt(in.readLine());
		mobiles = new Mobile[yLevel][xLevel];
		statics = new Static[yLevel][xLevel];
		colors = new String[] { in.readLine(), "lightGray", "darkGray", in.readLine() };
		for (int i = 0; i < yLevel; i++) {
			int j = 0;
			for (char c : in.readLine().trim().toCharArray()) {
				statics[i][j] = (c != 'x') ? new GoodCube(colors) : new BadCube();
				if (c == 'Q')
					mobiles[i][j] = players[0] = new Human(i, j, playerNames[0]);
				if (c == 'P' && selection != '1')
					mobiles[i][j] = players[1] = selection == '2' ? new Human(i, j, playerNames[1]) : new Machine();
				j++;
			}
		}
		in.close();
	}

	public String[] getColors() {
		return colors;
	}

	public String getMobile(int y, int x) {
		return mobiles[y][x].toString();
	}

	public String getStatic(int y, int x) {
		return statics[y][x].toString();
	}

	public void movePlayer1(String string, String string2) {
		int[] step = players[0].Premove(string, string2);
		if (!isBad(step[0], step[1])) {
			mobiles[step[1]][step[0]] = mobiles[players[0].getCy()][players[0].getCx()];
			mobiles[players[0].getCy()][players[0].getCx()] = null;
			players[0].move(string, string2);
		}
	}

	public void movePlayer2(String string, String string2) {

	}

	public static boolean isBad(int x, int y) {
		return (statics[x][y] instanceof Bad || mobiles[x][y] != null);
	}

	public int getXLevel() {
		return xLevel;
	}

	public int getYLevel() {
		return yLevel;
	}

	/* debug */
	public void printMats() {
		for (int i=0;i<yLevel;i++){
			for (Mobile a: mobiles[i]){
				System.out.print(a!=null?a+" ":0+" ");
			}
			System.out.print("---- ");
			for (Static a: statics[i]){
				System.out.print(a+" ");
			}
			System.out.println();
		}
		System.out.println();
	}


}
