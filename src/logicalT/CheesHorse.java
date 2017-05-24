package logicalT;

import java.util.*;

public class CheesHorse extends Conduct {
	Queue<String[]> cola = new LinkedList<String[]>();

	public CheesHorse(Mobile a) {
		super(a);
	}

	public String[] nextMove() {
		if (cola.isEmpty())
			generateMoves();
		return cola.poll();
	}

	private void generateMoves() {
		int[] backup = element.getCoords();
		Random ran = new Random();
		while (cola.size() != 3) {
			cola.clear();
			String xx = ran.nextBoolean() ? "R" : "L";
			String yy = ran.nextBoolean() ? "U" : "D";
			int[] a = element.Premove(xx, yy);
			while (element.getLogic().isLandBad(a[0], a[1])) {
				xx = ran.nextBoolean() ? "R" : "L";
				yy = ran.nextBoolean() ? "U" : "D";
				a = element.Premove(xx, yy);
			}
			cola.add(new String[] { xx, yy });
			element.move(xx, yy);
			int[] a2 = element.Premove(xx, yy);
			if (!element.getLogic().isLandBad(a2[0], a2[1])) {
				cola.add(new String[] { xx, yy });
				element.move(xx, yy);// neg x+y || x+neg y
				int[] a3 = element.Premove(xx, yy.equals("U") ? "D" : "U");
				if (!element.getLogic().isLandBad(a3[0], a3[1])) {
					cola.add(new String[] { xx, yy.equals("U") ? "D" : "U" });
					a3 = element.Premove(xx.equals("R") ? "L" : "R", yy);
				} else if (!element.getLogic().isLandBad(a3[0], a3[1]))
					cola.add(new String[] { xx.equals("R") ? "L" : "R", yy });
			}
			element.setCx(backup[0]);
			element.setCy(backup[1]);
		}

	}

}
