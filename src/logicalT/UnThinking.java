package logicalT;

import java.util.Random;

public class UnThinking extends Conduct {

	public UnThinking(Mobile a) {
		super(a);
	}
	public String[] nextMove() {
		Random x = new Random();
		String xx = x.nextBoolean() ? "R" : "L";
		String yy = x.nextBoolean() ? "U" : "D";
		int[] a = element.Premove(xx, yy);
		while (Poobert.isBad(a[0], a[1])) {
			xx = x.nextBoolean() ? "R" : "L";
			yy = x.nextBoolean() ? "U" : "D";
			a = element.Premove(xx, yy);
		}
		return new String[]{xx,yy};
	}

}
