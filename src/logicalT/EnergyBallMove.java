package logicalT;

public class EnergyBallMove extends Enemy {
	Player play;

	/**
	 * Constructor EnergyBall
	 * 
	 * @param a el jugador que lanza la bola
	 */
	public EnergyBallMove(Player a) {
		play = a;
		con = new Projectile(a);
		cx = a.getCx();
		cy = a.getCy();
		int[] cord = Premove(a.getDirx(), a.getDiry());
		move(a.getDirx(), a.getDiry());
	}

	public String toString() {
		return "powerball";
	}
	
	public String[] move() {
		String[] temp = con.nextMove();
		logic.moveObject(getCoords(), temp[0], temp[1]);
		return temp;
	}

}
