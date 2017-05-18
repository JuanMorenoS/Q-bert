package logicalT;

import java.io.Serializable;

public abstract class Player extends Mobile implements Serializable{
	protected String color;
	protected String power=null;
	/**
	 * me dice si un jugador tiene ataque
	 * @return si tiene un ataque
	 */
	public boolean haveAttack(){
		return power!=null;
	}
	public void lose() {
		
	}
	public void addPower(String string) {
		power=string;
	}
	public String usePower(){
		return power;
	}
}
