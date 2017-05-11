package logicalT;

public abstract class Player extends Mobile{
	protected String color;
	/**
	 * me dice si un jugador tiene ataque
	 * @return si tiene un ataque
	 */
	public boolean haveAttack(){
		return true;
	}
}
