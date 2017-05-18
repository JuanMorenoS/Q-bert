package logicalT;

import java.io.Serializable;

public abstract class Static implements Serializable{
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();

	public abstract void use(Player player);
}
