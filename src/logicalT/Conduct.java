package logicalT;

public abstract class Conduct {
	protected Mobile element;
	public abstract String[] nextMove();
	public Conduct(Mobile a){
		element=a;
	}
}
