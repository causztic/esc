package week11;

//this class must be thread-safe. why?
// cannot have multiple threads modifying the barrier at the same time.
public class MyCyclicBarrier {
	private int count = 0;
	private Runnable torun;
	
	public MyCyclicBarrier (int count, Runnable torun) {
		this.count = count;
		this.torun = torun;
	}

	public MyCyclicBarrier (int count) {
		this.count = count;
	}
	
	//complete the implementation below.
	//hint: use wait(), notifyAll()
	public synchronized void await () {
		try {
			if (--count == 0){
				torun.run();
				notifyAll();
			} else {
				wait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
