package logicalT;

import java.io.Serializable;

public abstract class Player extends Mobile implements Serializable{
	protected String color;
	/**
	 * me dice si un jugador tiene ataque
	 * @return si tiene un ataque
	 */
	public boolean haveAttack(){
		return true;
	}
	public void lose() {
		// TODO Auto-generated method stub
		
	}
}
