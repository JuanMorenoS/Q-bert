package logicalT;

import java.io.Serializable;

public abstract class Land implements Serializable{
	protected boolean visited;
	protected String[] colors;
	protected String actualColor;
	protected Player player;

	public String toString() {
		return actualColor;
	}
	/**
	 * maraca el cubo como visitado
	 */
	public abstract void visited(Player a);
	/**
	 * @return a booleano si es visitado
	 */
	public abstract boolean isVisited();
	/**
	 * lo marca como no visitado
	 */
	public abstract void unVisited();
}
