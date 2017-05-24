package logicalT;

public class Circular extends Conduct{
	private boolean up;
	private boolean left;
	
	public Circular(Mobile a) {
		super(a);
		up=true;
		left=true;
	}
	public String[] nextMove() {
		String[] res = new String[2];
		return res;
	}
	
}
