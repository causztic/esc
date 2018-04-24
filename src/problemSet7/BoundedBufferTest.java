package problemSet7;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.*;

public class BoundedBufferTest {	
	private static final long LOCKUP_DETECT_TIMEOUT = 1000;
	
	@Test
	public void testIsEmptyWhenConstructed () {
		BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		assertTrue(bb.isEmpty());
		assertFalse(bb.isFull());
	}
	
	@Test
	public void testIsFullAfterPuts () throws InterruptedException {
		final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		
		Runnable task = new Runnable () {
			public void run() {
				try {
					bb.put((new Random()).nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		};

		Thread[] threads = new Thread[10];
		
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread (task);
			threads[i].start();
		}

		for (int i = 0; i < 10; i++) {
			threads[i].join();
		}

		assertTrue(bb.isFull());
		assertFalse(bb.isEmpty());
	}
	
	@Test
	public void testTakeBlocksWhenEmpty () {
		final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		Thread taker = new Thread() {
			public void run() {
				try {
					int unused = bb.take();
					assertTrue(false);
				} catch (InterruptedException success) {} //if interrupted, the exception is caught here
			}
		};
		
		try {
			taker.start();
			Thread.sleep(LOCKUP_DETECT_TIMEOUT);
			taker.interrupt();
			taker.join(LOCKUP_DETECT_TIMEOUT);
			assertFalse(taker.isAlive()); //the taker should not be alive for some time
		} catch (Exception unexpected) {
			assertTrue(false);
		}
	}
	
	@Test
	// this test will attempt to add a block into the buffer when it is full.
	public void testPutBlockWhenFull() throws InterruptedException {
		final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		Runnable task = new Runnable () {
			public void run() {
				try {
					bb.put((new Random()).nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		};

		Thread[] threads = new Thread[10];
		
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread (task);
			threads[i].start();
		}

		for (int i = 0; i < 10; i++) {
			threads[i].join();
		}
		
		assertTrue(bb.isFull());
		Thread putter = new Thread() {
			public void run() {
				try {
					bb.put(10);
					assertTrue(false);
				} catch (InterruptedException success) {} //if interrupted, the exception is caught here
			}
		};
		try {
			putter.start();
			Thread.sleep(LOCKUP_DETECT_TIMEOUT);
			putter.interrupt();
			putter.join(LOCKUP_DETECT_TIMEOUT);
			assertFalse(putter.isAlive()); //the putter should not be alive for some time
		} catch (Exception unexpected) {
			assertTrue(false);
		}
	}
	
	@Test
	// this test will attempt to verify that running the same number of gets and puts will result in an empty 
	// boundedbuffer regardless of order
	public void testOrder() throws InterruptedException{
		final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		Runnable task = new Runnable () {
			public void run() {
				try {
					bb.put((new Random()).nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		};
		
		Runnable task2 = new Runnable () {
			public void run() {
				try {
					bb.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		};

		List<Thread> threads = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			threads.add(new Thread(task));
		}
		
		for (int i = 0; i < 5; i++) {
			threads.add(new Thread(task2));
		}
		
		Collections.shuffle(threads);
		
		for (Thread thread: threads){
			thread.start();
		}
		for (Thread thread: threads){
			thread.join();
		}
		
		assertTrue(bb.isEmpty());
	}
}
