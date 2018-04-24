package problemSet4;

import java.util.Random;

public class MutationFactory {
	static final Mutator[] mutators = { 
			new MutationFuzzingBitFlip(), 
			new MutationFuzzingSwap(), 
			new MutationFuzzingTrim() };
	
	public static Mutator getRandomMutator(){
		int index = new Random().nextInt(mutators.length);
		return mutators[index];
	}
}
