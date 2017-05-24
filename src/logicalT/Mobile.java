package logicalT;

import java.io.Serializable;

public abstract class Mobile implements Serializable{
	protected int cx,cy,live;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();
	protected String dirx,diry,name;
	public static Poobert logic;
	/**
	 * @param a el movimiento en x 
	 * @param b el movimiento en y
	 * @return retorna un arreglo de enteros con la posicion 
	 */
	public int[] Premove(String a, String b) {
		int cxn,cyn;
		if (cy % 2 != 0)
			cxn = (a.equals("L")) ? cx : cx + 1;
		else
			cxn = (a.equals("R")) ? cx : cx - 1;
		cyn = (b.equals("U")) ? cy - 1 : cy + 1;
	return new int[] {cxn,cyn};
	}
	/**
	 * @return retorna cx
	 */
	public int getCx(){
		return cx;
	}
	/**
	 * @return retorna cy
	 */
	public int getCy(){
		return cy;
	}
	/**
	 * mueve el jugador 
	 * @param a movimiento en x
	 * @param b movimiento en y
	 */
	public void move(String a, String b) {
		if (cy % 2 != 0)
			cx = (a.equals("L")) ? cx : cx + 1;
		else
			cx = (a.equals("R")) ? cx : cx - 1;
		cy = (b.equals("U")) ? cy - 1 : cy + 1;
		dirx=a;
		diry=b;
	}
	public void setCx(int x){
		cx=x;
	}
	public void setCy(int y){
		cy=y;
	}
	/**
	 * mueve el jugador deacuerdo a su conducta
	 * @return la siguiente jugada
	 */
	public abstract String[] move();
	
	/**
	 * setea el poobert a la clase Mobile para ser usada
	 * @param a el poobert
	 */
	public static void setLogic(Poobert a){
		logic=a;
	}
	/**
	 * obtiene las coordenadas en el campo de un jugador
	 * @return las coordenadas x,y
	 */
	public int[] getCoords(){
		return new int[]{cx,cy};
	}
	/**
	 * compara si un Mobile es igual a otro
	 * @param o otro ombile
	 * @return si son igguales
	 */
	public boolean equals(Mobile o){
		return cx==o.getCx() && cy==o.getCy();
	}
	/**
	 * obtiene direccion en x
	 * @return dirx
	 */
	public String getDirx(){
		return dirx;
	}
	/**
	 * obtiene la direccion en y
	 * @return diry
	 */
	public String getDiry(){
		return diry;
	}
	/**
	 * me da las vidas que quedan
	 * @return live
	 */
	public int getLive() {
		return live;
	}
	public Poobert getLogic(){
		return logic;
	}
}
