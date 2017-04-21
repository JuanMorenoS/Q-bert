package aplicacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import presentacion.Drawer;

public class Poobert{
	private ArrayList<Cube[]> land;
	private String[] color;
	private int tam;
	private Player[] players;
	private int level,xLevel,yLevel;
	private String[] playersNames;
	private char selection;
	
	public Poobert(String[] strings, char selection) {
		level=1;
		players=new Player[2];
		playersNames=strings;
		this.selection=selection;
		try {
			leerNivel(level);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void tablero(int b) {
		land = new ArrayList<>();
		for (int i = 0; i < b; i++) {
			if (i % 2 == 0)
				land.add(new Cube[b]);
			else
				land.add(new Cube[(b)]);
		}
		for (int i = 0; i < b; i++) {
			for (int j = 0; j < land.get(i).length; j++) {
				if (i % 2 == 0) {
					land.get(i)[j] = (new Cube(tam, null));
					land.get(i)[j].move(j * tam * 4, i * tam * 3);
				} else {
					land.get(i)[j] = (new Cube(tam, null));
					land.get(i)[j].move((j * tam * 4) + tam * 2, i * tam * 3);
				}
			}
		}
	}
	public void add(int x, int y) {
		land.get(x)[y].setColors(color,false);
	}

	public void changeColor(Player play) {
		if (!land.get(play.cy)[play.cx].visited())
			play.lose('F');
		
	}
	public void setPlayer1(int i, int q,String name) {
		int [] temo = land.get(i)[q].getCords();
		players[0] = new Player(temo[0], temo[1],tam,q,i,name,'n');
	}
	public void setPlayer2(int i, int q,String name) {
		int [] temo = land.get(i)[q].getCords();
		players[1] = new Player(temo[0], temo[1],tam,q,i,name,'b');
	}
	
	private void leerNivel(int le) throws IOException {
		int zoom=20 *3;
		tam=zoom/3;
		BufferedReader in;
		in = new BufferedReader(new FileReader("resources/Levels/"+le+".level"));
		xLevel=Integer.parseInt(in.readLine());
		yLevel=Integer.parseInt(in.readLine());
		color = new String[] { in.readLine(), "lightGray", "darkGray", in.readLine() };
		tablero(yLevel);
		for(int i=0;i<xLevel;i++){
			int q=0;
			for(char j:in.readLine().toCharArray()){ 
				if(j!='x')
					add(i,q);
					if(j=='Q')
						setPlayer1(i,q,playersNames[0]);
					if(j=='P' && selection!='1')
						setPlayer2(i,q,playersNames[1]);
				if (j!='*')
					q++;
			}
		}
		in.close();
	}

	public void move1(String a, String b) {
		players[0].move(a, b);
		changeColor(players[0]);
	}
	public void move2(String a, String b) {
		players[1].move(a, b);
		changeColor(players[1]);
	}

	public Player[] getPlayers() {
		return players;
	}

	public ArrayList<Cube[]> getLand() {
		return land;
	}

	public int getSizeX() {
		return xLevel*20*3;
	}
	public int getSizeY() {
		return yLevel*20*3;
	}
}
