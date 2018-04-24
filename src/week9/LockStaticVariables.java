package week9;

import java.util.concurrent.atomic.AtomicInteger;

public class LockStaticVariables {
	public static AtomicInteger lock = new AtomicInteger();
	public static int saving = 5000;
	public static int cash = 0;
	
	public static void main(String args[]){   	
		int numberofThreads = 10000;
		WD[] threads = new WD[numberofThreads];
	
		for (int i = 0; i < numberofThreads; i++) {
			threads[i] = new WD();
			threads[i].start();
		}
		
		try {
			for (int i = 0; i < numberofThreads; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			System.out.println("some thread is not finished");
		}
		
		System.out.print("The result is: " + LockStaticVariables.cash);
	}
}

class WD extends Thread {
	public void run () {
		synchronized(LockStaticVariables.lock) {
			if (LockStaticVariables.saving >= 1000) {
				System.out.println("I am doing something.");	
				LockStaticVariables.saving = LockStaticVariables.saving - 1000;
				LockStaticVariables.cash = LockStaticVariables.cash + 1000;
			}
		}
	
	}	
}

