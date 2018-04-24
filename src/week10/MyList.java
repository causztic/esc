package week10;

public class MyList {
	//@guardedby("this")
	private final int maxSize;
	//@guardedby("this")
	private long[] array;
	//@guardedby("this")
	private int length; 
	
	public MyList(int s) { //Do we need "synchronized" for the constructor?
		maxSize = s;
	    array = new long[maxSize];
	    length = 0;
	}
	
	public synchronized void add(long j) {
		array[length++] = j;
	}

	public synchronized boolean contain (long j) {
		for (int i = 0; i < length; i++) {
			if (array[i] == j) {
				return true;
			}
		}
		
		return false;
	}
	
	//add a method for add-if-absent
	public synchronized boolean addIfAbsent (long j) {
		if (!contain(j)) {
			add(j);			
			return true;
		}
		
		return false;
	}
}