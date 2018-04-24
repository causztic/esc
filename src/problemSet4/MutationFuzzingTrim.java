package problemSet4;

import java.util.Random;

public class MutationFuzzingTrim extends Mutator{

	@Override
	String mutate(String str) {
		// trim a random position
		int index = new Random().nextInt(str.length());
		StringBuilder sb = new StringBuilder(str);
		sb.deleteCharAt(index);
		return sb.toString();
	}

}
