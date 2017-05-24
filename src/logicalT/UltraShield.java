package logicalT;

public class UltraShield extends Help implements UnCapturable{

	public void use(Player a) {
		a.setShield();
	}

	public String toString() {
		return "UltraShield";
	}

}
