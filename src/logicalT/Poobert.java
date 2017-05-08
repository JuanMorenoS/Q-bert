package logicalT;

import java.io.*;

public class Poobert {
	private static Player[] players;
	private static Mobile[][] mobiles;
	private static Static[][] statics;
	private int level, yLevel, xLevel,totalC;
	private String[] colors, playerNames;
	private char selection;
	public Poobert(String[] names, char selection) {
		this.selection = selection;
		level = 3;
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
		Mobile.setLogic(this);
		colors = new String[] { in.readLine(), "lightGray", "darkGray", in.readLine() };
		for (int i = 0; i < yLevel; i++) {
			int j = 0;
			for (char c : in.readLine().trim().toCharArray()) {
				statics[i][j] = (c != 'x') ? new GoodCube(colors) : new BadCube();
				totalC = (c != 'x') ? totalC+1 :totalC;
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

	public String[] getColors() {
		return colors;
	}

	public String getMobile(int y, int x) {
		return mobiles[y][x] == null ? "0" : mobiles[y][x].toString();
	}

	public String getStatic(int y, int x) {
		return statics[y][x].toString();
	}

	public void movePlayer1(String string, String string2) {
		int[] step = players[0].Premove(string, string2);
		if (!isBad(step[0], step[1])) {
			moveObject(new int[]{players[0].getCx(),players[0].getCy()}, string,string2);
			statics[step[1]][step[0]].visited();
			
		}
	}

	public void movePlayer2(String string, String string2) {
		int[] step = players[1].Premove(string, string2);
		if (!isBad(step[0], step[1])) {
			moveObject(new int[]{players[1].getCx(),players[1].getCy()}, string,string2);
			statics[step[1]][step[0]].visited();
		}
	}
		
	private void moveObject(int[] pre,String a,String b){
		int[] pos = mobiles[pre[1]][pre[0]].Premove(a, b);
		mobiles[pos[1]][pos[0]] = mobiles[pre[1]][pre[0]];
		mobiles[pre[1]][pre[0]]=null;
		mobiles[pos[1]][pos[0]].move(a, b);
		
	}
	public void player1Attack() {
		if(players[0].haveAttack()){
			
		}
	}

	public void player2Attack() {
		if(players[1].haveAttack()){
			
		}
	}

	public static boolean isBad(int x, int y) {
		return (statics[y][x] instanceof Bad || mobiles[y][x] != null);
	}

	public int getXLevel() {
		return xLevel;
	}

	public int getYLevel() {
		return yLevel;
	}

	/* debug */
	public void printMats() {
		for (int i = 0; i < yLevel; i++) {
			for (Mobile a : mobiles[i]) {
				System.out.print(a != null ? "q" + " " : 0 + " ");
			}
			System.out.print("---- ");
			for (Static a : statics[i]) {
				System.out.print(a instanceof Bad ? "x " : "c ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
