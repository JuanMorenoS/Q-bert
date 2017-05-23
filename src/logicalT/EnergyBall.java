package logicalT;

public class EnergyBall extends Help implements Capturable{

	public String toString() {
		return "EnergyBall";
	}

	public void destroyThis() {
		
	}

	public void use(Player a) {
		System.out.println("poder obtenido !");
		a.addPower("EnergyBallMove");
	}


}
