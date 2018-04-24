package problemSet4;

import java.util.Random;

public class MutationFuzzingBitFlip extends Mutator {
	
	public String mutate(String str){
		char[] arr = str.toCharArray();
		// flip the bit
		int index = new Random().nextInt(arr.length);
		arr[index] %= 7;
		return new String(arr);
	}
}
