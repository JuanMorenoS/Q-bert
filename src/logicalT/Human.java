package logicalT;

public class Human extends Player{
	public Human(int i, int j, String name) {
		live=3;
		cx=j;
		cy=i;
		dirx="L";
		diry="D";
		color="n";
		this.name=name;
		
	}
	public String toString() {
		return "qbert"+color+"-"+diry+dirx;
	}
	public String[] move() {
		return null;
	}
	

}
