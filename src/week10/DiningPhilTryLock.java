package week10;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilTryLock {
	private static int N = 5;

	public static void main(String[] args) throws Exception {
		PhilosopherTryLock[] phils = new PhilosopherTryLock[N];
		ForkTryLock[] forks = new ForkTryLock[N];

		for (int i = 0; i < N; i++) {
			forks[i] = new ForkTryLock(i);
		}

		for (int i = 0; i < N; i++) {
			phils[i] = new PhilosopherTryLock(i, forks[i], forks[(i + N - 1) % N]);
			phils[i].start();
		}
	}
}

class PhilosopherTryLock extends Thread {
	private final int index;
	private final ForkTryLock left;
	private final ForkTryLock right;

	public PhilosopherTryLock(int index, ForkTryLock forks, ForkTryLock forks2) {
		this.index = index;
		this.left = forks;
		this.right = forks2;
	}

	public void run() {
		Random randomGenerator = new Random();
		try {
			while (true) {
				Thread.sleep(randomGenerator.nextInt(100)); // not sleeping but
															// thinking
				System.out.println("Phil " + index + " finishes thinking.");
				left.pickup();
				System.out.println("Phil " + index + " picks up left fork.");
				Thread.sleep(100);
				right.pickup();
				System.out.println("Phil " + index + " picks up right fork.");
				Thread.sleep(randomGenerator.nextInt(100)); // eating
				System.out.println("Phil " + index + " finishes eating.");
				left.putdown();
				System.out.println("Phil " + index + " puts down left fork.");
				right.putdown();
				System.out.println("Phil " + index + " puts down right fork.");
			}
		} catch (InterruptedException e) {
			System.out.println("Don't disturb me while I am sleeping, well, thinking.");
		}
	}
}

class ForkTryLock {
	private final int index;
	private boolean isAvailable = true;
	private final ReentrantLock lock = new ReentrantLock(true);

	public ForkTryLock(int index) {
		this.index = index;
	}

	public void pickup () throws InterruptedException {
		isAvailable = !lock.tryLock(10, TimeUnit.MILLISECONDS);
	}
	
	public void putdown() throws InterruptedException {
		// ensure that you can only drop fork if it's held by the same person
		if (lock.isHeldByCurrentThread()){
			lock.unlock();
			isAvailable = true;
		}
	}

	public String toString() {
		if (isAvailable) {
			return "Fork " + index + " is available.";
		} else {
			return "Fork " + index + " is NOT available.";
		}
	}
}