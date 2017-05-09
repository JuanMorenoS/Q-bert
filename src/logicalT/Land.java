package logicalT;

public abstract class Land {
	protected boolean visited;
	protected String[] colors;
	protected String actualColor;
	public String toString() {
		return actualColor;
	}
	public abstract void visited();
	public abstract boolean isVisited();
	public abstract void unVisited();
}
