package logicalT;

public class Snake extends Enemy{
	public Snake(int i, int j){
		cx=j;
		cy=i;
		con = new Flow(this);
	}
	public String toString() {
		return "Snake";
	}

	public String[] move() {
		String[] temp=con.nextMove();
		logic.moveObject(getCoords(), temp[0], temp[1]);
		return temp;
	}
	
}
