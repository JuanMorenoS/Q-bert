package logicalT;

public class EnergyBallMove extends Enemy{
	Player play;
	public EnergyBallMove(Player a){
		play=a;
		con=new Projectile(a);
		int[] cord=Premove(a.getDirx(), a.getDiry());
		cx=cord[0];
		cy=cord[1];
	}
	public String toString() {
		return "powerball";
	}

	public String[] move() {
		return con.nextMove();
	}

}
