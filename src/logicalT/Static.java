package logicalT;

public abstract class Static {
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();
	/**
	 * lo marac como visitado
	 */
	public abstract void visited();
	/**
	 * @return si esta visitado
	 */
	public abstract boolean isVisited();
	/**
	 *  lo marca como no visitado
	 */
	public abstract void unVisited();
}
