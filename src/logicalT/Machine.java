package logicalT;

public class Machine extends Player {
	private Conduct con;
	private String modeq;
	/**
	 * 
	 * @param i pos x
	 * @param j pos y
	 * @param string nombre 
	 * @param col color 
	 * @param mode modo 
	 * @param a jugador
	 */
	public Machine(int i, int j, String string,String col,String mode,int a) {
		pl=a;
		live=4;
		cx=j;
		cy=i;
		dirx="L";
		diry="D";
		modeq=mode;
		color=col.substring(0,1).toLowerCase();
		if (mode.equals("Irreflexivo"))
			con = new UnThinking(this);
		else if(mode.equals("Timido"))
			con = new Shy(this);
		else
			con = new Offensive(this);
		this.name=string;
	}
	public String toString() {
		return "qbert"+color+"-"+diry+dirx;
	}
	public String[] move(){
		if (modeq.equals("Irreflexivo"))
			logic.player2Attack();
		else if(modeq.equals("Ofensivo"))
			logic.player2Attack();
		String[] temp=con.nextMove();
		logic.movePlayer2(temp[0],temp[1]);
		return temp;
	}
}
