package logicalT;

public class Switch extends Help implements UnCapturable{

	public void use(Player a) {
		int[] step = a.Premove("L", "U");
		step = a.Premove("L", "D");
		step = a.Premove("R", "U");
		step = a.Premove("R", "D");
	}

	public String toString() {
		return "Switch";
	}

}
