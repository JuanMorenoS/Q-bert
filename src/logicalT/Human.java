package logicalT;

public class Human extends Player{
	public Human(int i, int j, String name) {
		cx=j;
		cy=i;
		dirx="L";
		diry="D";
		color="n";
		
	}
	public String toString() {
		return "qbert"+color+"-"+diry+dirx;
	}
	

}
