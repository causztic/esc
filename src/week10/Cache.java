package week10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {
	private final Map<Integer, List<Integer>> results = new HashMap<Integer, List<Integer>> (); 

	public synchronized List<Integer> service (int input) {
		List<Integer> factors = results.get(input);
		
		if (factors == null) {
			factors = factor(input);
			results.put(input, factors);
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
}