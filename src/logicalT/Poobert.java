package logicalT;

import java.io.*;

public class Poobert {
	private static Player[] players;
	private static Mobile[][] mobiles;
	private static Static[][] statics;
	private int level, yLevel, xLevel;
	private String[] colors,playerNames;
	private char selection;
	public Poobert(String[] names,char selection) {
		this.selection = selection;
		level = 1;
		players = new Player[2];
		playerNames=names;
		try {
			readLevel();
		} catch (Exception e) {
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
				statics[i][j]=c != 'x'?new GoodCube(colors):new BadCube();
				if (c == 'Q')
					mobiles[i][j]=players[0]=new Human(i,j,playerNames[0]);
				if (c == 'P' && selection != '1')
					mobiles[i][j]=players[1]= selection=='2'?new Human(i,j,playerNames[1]):new Machine();
			}
		}
		in.close();
	}
	public String[] getColors(){
		return colors;
	}
	public String getMobile(int y,int x){
		return mobiles[y][x].toString();
	}
	public String getStatic(int y,int x){
		return statics[y][x].toString();
	}

	public void movePlayer1(String string, String string2) {
		players[0].
	}

	public void movePlayer2(String string, String string2) {
		
	}
		
}
