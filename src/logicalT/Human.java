package logicalT;

public class Human extends Player{
	/**
	 * 
	 * @param i pos x
	 * @param j pos y 
	 * @param name nombre 
	 * @param col color 
	 * @param a poasda
	 */
	public Human(int i, int j, String name,String col,int a) {
		pl=a;
		live=4;
		cx=j;
		cy=i;
		dirx="L";
		diry="D";
		color=col.substring(0, 1).toLowerCase();
		this.name=name;
		
	}
	public String toString() {
		return "qbert"+color+"-"+diry+dirx;
	}
	public String[] move() {
		return null;
	}
	

}
