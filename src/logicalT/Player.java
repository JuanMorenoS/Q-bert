package logicalT;

public abstract class Player extends Mobile{
	protected String dirx,diry;
	public void move(String a, String b) {
		if (cy % 2 != 0)
			cx = (a.equals("L")) ? cx : cx + 1;
		else
			cx = (a.equals("R")) ? cx : cx - 1;
		cy = (b.equals("U")) ? cy - 1 : cy + 1;
		dirx=a;
		diry=b;
	}
}
