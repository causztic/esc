package problemSet6;

public class MyStackThreadSafeComplete {
	private final int maxSize; // maxSize is not modified.
	private final long[] stackArray; // the object reference is not updated.
	private int top; // guarded by this
	
	public MyStackThreadSafeComplete(int s) { 
		maxSize = s;
	    stackArray = new long[maxSize];
	    top = -1;
	}
	
	// top < maxSize
	// j is added to the stack
	public synchronized void push(long j) {
		while (!isFull()){
			try {
				wait();
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		notifyAll();
		stackArray[++top] = j;
	}

	// top >= 0
	// top element is removed
	public synchronized long pop() {		
		while (!isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		notifyAll();
	    return stackArray[top--];
	}
	
	// top >= 0
	// top element is returned
	public synchronized long peek() {
		while (!isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		notifyAll();
	    return stackArray[top];
	}

	// this method does not modify nor require any values, no need to wait
	public synchronized boolean isEmpty() {
		return (top == -1);
	}
	
	// this method does not modify nor require any values, no need to wait
	public synchronized boolean isFull() {
		return (top == maxSize - 1);
	}
}