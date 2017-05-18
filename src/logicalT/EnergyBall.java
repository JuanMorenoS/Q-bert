package logicalT;

public class EnergyBall extends Help implements Capturable{

	public String toString() {
		return "EnergyBall";
	}

	public void destroyThis() {
		
	}

	public void use(Player a) {
		a.addPower("EnergyBallMove");
	}


}
