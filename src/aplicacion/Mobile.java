package aplicacion;

public abstract class Mobile{
	protected int x, y, cx, cy;
	protected int[] origen;
	abstract public boolean move(String a, String b);
	public Mobile(int x,int y,int cx, int cy){
		this.x=x;
		this.cx=cx;
		this.y=y;
		this.cy=cy;
	}
	/**
	 * @return the x
	 */
	public final int getX() {
		return x;
	}
	/**
	 * @return the y
	 */
	public final int getY() {
		return y;
	}
	/**
	 * @return the cx
	 */
	public final int getCx() {
		return cx;
	}
	/**
	 * @return the cy
	 */
	public final int getCy() {
		return cy;
	}	
	
}
