package logicalT;

public class Machine extends Player {

	public Machine(int i, int j, String string) {
		cx=j;
		cy=i;
		dirx="L";
		diry="D";
		color="b";
	}

	public String toString() {
		return "qbert"+color+"-"+diry+dirx;
	}

}
