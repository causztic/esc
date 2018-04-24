package week10;

public class ExtendedPairWrong extends NewPair {
	public ExtendedPairWrong (int x, int y) {
		super(x, y);
	}
	
	public synchronized void increment () {
		incrementX();
		incrementY();
	}
}

class NewPair {
	private int x;
	private int y; 
	private Object lockx = new Object ();
	private Object locky = new Object ();
	
	
	public NewPair(int x, int y) { 
		this.x = x;
		this.y = y;
	}
	
	public void incrementX() {
		synchronized (lockx) {
			x++;			
		}
	}
	
	public void incrementY() {
		synchronized (locky) {
			y++;			
		}
	}
}