package logicalT;

public class Human extends Player{
	/**
	 * Constructor Human 
	 * @param i el cubo en y
	 * @param j el cubo en x
	 * @param name el nombre del jugador
	 */
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
