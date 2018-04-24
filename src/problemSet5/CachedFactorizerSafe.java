package problemSet5;

import net.jcip.annotations.GuardedBy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MyRunnable implements Runnable {
	private CachedFactorizerSafe factorizer;
	
	public MyRunnable (CachedFactorizerSafe factorizer) {
		this.factorizer = factorizer; 
	}
	
	public void run () {
		Random random = new Random ();
		int i = random.nextInt(100);
		System.out.println("Factors for " + i + ": " + factorizer.service(i));
	}
}

public class CachedFactorizerSafe {
	@GuardedBy("number")
	private int lastNumber;
	@GuardedBy("number")
	private List<Integer> lastFactors;
	
	@GuardedBy("hits")
	private long hits;
	@GuardedBy("hits")
	private long cacheHits;
	
	public long getHits () {
		return hits;
	}
	
	public synchronized double getCacheHitRatio () {
		return (double) cacheHits/ (double) hits;
	}
	
	public synchronized List<Integer> service (int input) {
		List<Integer> factors = null;
		++hits;
		
		if (input == lastNumber) {
			++cacheHits;
			factors = new ArrayList<Integer>(lastFactors);
		}
		
		if (factors == null) {
			factors = factor(input);
			lastNumber = input;
			lastFactors = new ArrayList<Integer>(factors);
		}
		
		return factors;
	}
	
	public List<Integer> factor(int n) {		
		List<Integer> factors = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
		        factors.add(i);
		        n /= i;
		    }
		}
		
		return factors;
	}
	
	public static void main (String[] args) {
		CachedFactorizerSafe factorizer = new CachedFactorizerSafe();
		Thread tr1 = new Thread (new MyRunnable(factorizer));
		Thread tr2 = new Thread (new MyRunnable(factorizer));
		tr1.start();
		tr2.start();
		
		try {
			tr1.join();
			tr2.join();
		}
		catch (Exception e) {
			
		}
	}
}