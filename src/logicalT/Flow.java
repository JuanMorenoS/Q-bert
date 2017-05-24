package logicalT;

import java.util.*;

public class Flow extends Conduct {

	public Flow(Mobile a) {
		super(a);
	}

	public String[] nextMove() {
		ArrayList<Integer> recorrido = element.logic.dfs((element.getCx() * 1000) + element.getCy());
		int x = recorrido.get(recorrido.size()-2) / 100;
		int y = recorrido.get(recorrido.size()-2) % 100;
		String yy = element.getCy() < y ? "D" : "U";
		String xx;
		if (element.getCx() % 2 != 0) {
			xx = x != element.getCx() ? "L" : "R";
		} else {
			xx = x != element.getCx() ? "R" : "L";
		}
		return new String[] { xx, yy };
	}

}
