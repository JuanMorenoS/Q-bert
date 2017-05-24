package logicalT;

import java.io.Serializable;

public abstract class Land implements Serializable{
	protected boolean visited;
	protected String[] colors = new String[]{"Black"};
	protected String actualColor;
	protected Player player;

	public String toString() {
		return actualColor;
	}
	/**
	 * 
	 * @param a el jugador visitado
	 */
	public abstract void visited(Player a);
	/**
	 * @return a booleano si es visitado
	 */
	public abstract boolean isVisited();
	/**
	 * lo marca como no visitado
	 */
	public void unVisited(){
		actualColor = colors[0];
		player=null;
		visited = false;
	}
}
