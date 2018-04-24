package problemSet7;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimedTestConcMap {
	private BarrierTimer timer = new BarrierTimer();
	private final CyclicBarrier barrier;
    private static final ExecutorService pool = Executors.newCachedThreadPool();
	private final Map<Integer, Integer> map;
	private int nTrials, threadCount;
	
	public TimedTestConcMap(int nTrials, int threadCount){
		map = new ConcurrentHashMap<Integer, Integer>(nTrials);
		this.nTrials = nTrials;
		this.threadCount = threadCount;
        barrier = new CyclicBarrier(threadCount + 1, timer);
	}
	
	public void test(){
		// test the map by having 10000 inserts across different number of threads
		try {
			timer.clear();
	        for (int i = 0; i < threadCount; i++) {
	            pool.execute(new Producer());
	        }
	        barrier.await(); // start timer
	        barrier.await(); // stop timer
	        System.out.printf(
	        		"Average time taken to insert 10000 items with %d threads: %d ns/item\n", 
	        		threadCount, (timer.getTime() / 10000));
		} catch (Exception e){
            throw new RuntimeException(e);
		}

	}
	
	public static void main(String[] args){
		for (int i = 10; i < 100; i+=10){
			TimedTestConcMap test = new TimedTestConcMap(10000, i);
			test.test();
		}
		pool.shutdown();
	}
	
    class Producer implements Runnable {
        public void run() {
            try {
            	barrier.await();
                int seed = (this.hashCode() ^ (int) System.nanoTime());
                for (int i = nTrials; i > 0; --i) {
                	// no synchronized block needed.
                	map.put(i, seed);
                }
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}