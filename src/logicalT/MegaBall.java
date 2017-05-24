package logicalT;

public class MegaBall extends Enemy{

	public MegaBall(int i, int j){
		cx=j;
		cy=i;
		if(logic.getDifficult()=='H' || true)
			con = new UnThinking(this);
	}
	public String[] move() {
		String[] temp=con.nextMove();
		logic.moveObject(getCoords(), temp[0], temp[1]);
		logic.unVisitLand(cy, cx);
		return temp;
	}

	public String toString() {
		return "MegaBall";
	}
}
