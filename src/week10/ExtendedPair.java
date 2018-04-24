package week10;

public class ExtendedPair extends Pair {
	public ExtendedPair (int x, int y) {
		super(x, y);
	}
	
	public synchronized void incrementXY () {
		incrementX();
		incrementY();
	}
}

class Pair {
	private int x;
	private int y; 
	
	public Pair(int x, int y) { 
		this.x = x;
		this.y = y;
	}
	
	public synchronized void incrementX() {
		x++;
	}
	
	public synchronized void incrementY() {
		y++;
	}
}