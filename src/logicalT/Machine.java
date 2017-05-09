package logicalT;

public class Machine extends Player {
	private Conduct con;
	public Machine(int i, int j, String string) {
		live=3;
		cx=j;
		cy=i;
		dirx="L";
		diry="D";
		color="b";
		con = new UnThinking(this);
		this.name=string;
	}

	public String toString() {
		return "qbert"+color+"-"+diry+dirx;
	}
	public String[] move(){
		String[] temp=con.nextMove();
		logic.movePlayer2(temp[0],temp[1]);
		return temp;
	}
}
