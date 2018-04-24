package problemSet6;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilFixed1 {
	private static int N = 5;

	public static void main(String[] args) throws Exception {
		Philosopher[] phils = new Philosopher[N];
		Fork[] forks = new Fork[N];

		for (int i = 0; i < N; i++) {
			forks[i] = new Fork(i);
		}

		for (int i = 0; i < N; i++) {
			phils[i] = new Philosopher(i, forks[i], forks[(i + N - 1) % N]);
			phils[i].start();
		}
	}
}

class Philosopher extends Thread {
	private final int index;
	private final Fork left;
	private final Fork right;

	public Philosopher(int index, Fork forks, Fork forks2) {
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

				// change the order of pick up for the last Philosopher.
				if (index == 4){
					right.pickup();
					System.out.println("Phil " + index + " picks up right fork.");
					Thread.sleep(100);
					left.pickup();
					System.out.println("Phil " + index + " picks up left fork.");	
				} else {
					left.pickup();
					System.out.println("Phil " + index + " picks up left fork.");
					Thread.sleep(100);
					right.pickup();
					System.out.println("Phil " + index + " picks up right fork.");
				}

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

class Fork {
	private final int index;
	private boolean isAvailable = true;
	
	public Fork (int index) {
		this.index = index;
	}
	
	public synchronized void pickup () throws InterruptedException {
		while (!isAvailable) {
			wait();
		}
		
		isAvailable = false;
		notifyAll();
	}
	
	public synchronized void putdown() throws InterruptedException {
		while (isAvailable) {
			wait();
		}

		isAvailable = true;
		notifyAll();
	}
	
	public String toString () {
		if (isAvailable) {
			return "Fork " + index + " is available.";			
		}
		else {
			return "Fork " + index + " is NOT available.";						
		}
	}
}