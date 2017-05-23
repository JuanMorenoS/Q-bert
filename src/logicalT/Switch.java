package logicalT;

public class Switch extends Help implements UnCapturable{

	public void use(Player a) {
		int[] step = a.Premove("L", "U");
		a.logic.visitLand(step[1], step[0], a);
		step = a.Premove("L", "D");
		a.logic.visitLand(step[1], step[0], a);
		step = a.Premove("R", "U");
		a.logic.visitLand(step[1], step[0], a);
		step = a.Premove("R", "D");
		a.logic.visitLand(step[1], step[0], a);
		a.logic.visitLand(a.getCy(),a.getCx()+1 , a);
		a.logic.visitLand(a.getCy(),a.getCx()-1 , a);
	}

	public String toString() {
		return "Switch";
	}

}
