package logicalT;

import java.util.Random;

public class UnThinking extends Conduct {

	/**
	 * @param a el objeto
	 */
	public UnThinking(Mobile a) {
		super(a);
	}
	/* (non-Javadoc)
	 * @see logicalT.Conduct#nextMove()
	 */
	public String[] nextMove() {
		Random x = new Random();
		String xx = x.nextBoolean() ? "R" : "L";
		String yy = x.nextBoolean() ? "U" : "D";
		int[] a = element.Premove(xx, yy);
		while (element.getLogic().isLandBad(a[0], a[1]) && element.logic.getMobile(a[0], a[1]).equals("0")) {
			xx = x.nextBoolean() ? "R" : "L";
			yy = x.nextBoolean() ? "U" : "D";
			a = element.Premove(xx, yy);
		}
		return new String[]{xx,yy};
	}

}
