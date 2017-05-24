package logicalT;

import java.util.Random;

public class Ugg extends Enemy {

	public Ugg(int i, int j) {
		cx = j;
		cy = i;
		if (logic.getDifficult() == 'H' || true)
			con = new CheesHorse(this);
	}

	public String toString() {
		return "Ugg";
	}

	public String[] move() {
		String[] temp = con.nextMove();
		Random ran = new Random();
		if (ran.nextBoolean())
			logic.putMine(cy, cx);
		logic.moveObject(getCoords(), temp[0], temp[1]);
		return temp;
	}

}
