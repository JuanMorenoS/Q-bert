package logicalT;

public class GoodCube extends Help{
	public GoodCube(String[] color) {
		colors = color;
		actualColor=colors[0];
		visited=false;
	}
	public String toString() {
		return actualColor;
	}
	public void visited() {
		if(!visited)
			actualColor=colors[3];
		visited=true;
	}
	public boolean isVisited() {
		return visited;
	}
	public void unVisited() {
		actualColor=colors[0];
		visited=false;
	}	
}
