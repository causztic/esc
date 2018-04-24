package week10;

import java.util.concurrent.atomic.AtomicInteger;

//is this class thread-safe?
public class Range {
	public final AtomicInteger lower = new AtomicInteger(0);
	public final AtomicInteger upper = new AtomicInteger(0);
	//invariant: lower <= upper
	
	public void setLower(int i) {
		if (i > upper.get()) {
			throw new IllegalArgumentException ("Can't set lower to " + i + " > upper");
		}
		
		lower.set(i);
	}
	
	public void setUpper(int i) {
		if (i < lower.get()) {
			throw new IllegalArgumentException ("Can't set upper to " + i + " < lower");
		}
		
		upper.set(i);
	}
	
	public boolean isInRange(int i) {
		return (i >= lower.get()) && i <= upper.get();
	}
	
	public boolean isValid() {
		return lower.get() <= upper.get();
	}
}
