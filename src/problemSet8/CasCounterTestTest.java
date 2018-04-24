package problemSet8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class BarrierTimer implements Runnable {
    private boolean started;
    private long startTime, endTime;

    public synchronized void run() {
        long t = System.nanoTime();
        if (!started) {
            started = true;
            startTime = t;
        } else
            endTime = t;
    }

    public synchronized void clear() {
        started = false;
    }

    public synchronized long getTime() {
        return endTime - startTime;
    }
}
public class CasCounterTestTest {
    public static void main(String[] args) throws Exception {
        int tpt = 100000; 
        final Object lock = new Object();
        for (int nThreads = 32; nThreads <= 100; nThreads += 10) {
            System.out.print("number of threads: " + nThreads + "\t");
        	AtomicCasCounterTest t = new AtomicCasCounterTest(nThreads, tpt);
        	LockCasCounterTest l = new LockCasCounterTest(nThreads, tpt, lock);
            t.test();
            Thread.sleep(1000);
            l.test();
            System.out.println();
        }
        AtomicCasCounterTest.pool.shutdown();
    }
}

class LockCasCounterTest {
    private BarrierTimer timer = new BarrierTimer();
    protected static final ExecutorService pool = Executors.newCachedThreadPool();
    protected volatile int data;
    protected final Object lock;
    
    protected final int nTrials, nThreads;
    protected CyclicBarrier barrier;
    protected final int nIncrements = 10000;
    
    public LockCasCounterTest(int nThreads, int trials, Object lock) {
    	this.nThreads = nThreads;
    	this.nTrials = trials;
    	this.lock = lock;
        barrier = new CyclicBarrier(nThreads + 1, timer);
    }

    public void test() {
        try {
            timer.clear();
            for (int i = 0; i < nThreads; i++) {
                pool.execute(new Runnable () {
					public void run() {
			            try {
							barrier.await();						
							for (int i = 0; i < nIncrements; i++) {
								//TODO: perform the data operation
								synchronized(lock) {
									data += 1;
								}
							}
							barrier.await();
						} catch (InterruptedException | BrokenBarrierException e) {
							e.printStackTrace();
						}
					}                	
                });
            }
            barrier.await();
            barrier.await();
            System.out.print("Lock Total Time: " + timer.getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


class AtomicCasCounterTest {
    private BarrierTimer timer = new BarrierTimer();
    protected static final ExecutorService pool = Executors.newCachedThreadPool();
    protected final AtomicInteger data = new AtomicInteger();
    
    protected final int nTrials, nThreads;
    protected CyclicBarrier barrier;
    protected final int nIncrements = 10000;
    
    public AtomicCasCounterTest(int nThreads, int trials) {
    	this.nThreads = nThreads;
    	this.nTrials = trials;
        barrier = new CyclicBarrier(nThreads + 1, timer);
    }

    public void test() {
        try {
            timer.clear();
            for (int i = 0; i < nThreads; i++) {
                pool.execute(new Runnable () {
					public void run() {
			            try {
							barrier.await();						
							for (int i = 0; i < nIncrements; i++) {
								//TODO: perform the data operation
								data.incrementAndGet();
							}
							barrier.await();
						} catch (InterruptedException | BrokenBarrierException e) {
							e.printStackTrace();
						}
					}                	
                });
            }
            barrier.await();
            barrier.await();
            System.out.print("Atomic Total Time: " + timer.getTime() + "\t");
            if (data.get() != (nThreads * nIncrements))
            	System.err.println("incorrect increments.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class LockCounter {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int increment() {
        return value++;
    }
}