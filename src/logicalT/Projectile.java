package logicalT;


public class Projectile extends Conduct{
	private String[] direction;
	public Projectile(Player a) {
		super(a);
		direction = new String[]{a.getDirx(),a.getDiry()};
	}
	public String[] nextMove() {
		return direction;
	}

}
