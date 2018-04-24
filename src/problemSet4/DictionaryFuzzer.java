package problemSet4;

import java.util.Random;

public class DictionaryFuzzer {
	private static final String[] ANIMALS = { "alpaca", "llama", "horse", "giraffe", "armadillo", "pony", "zebra",
			"snake", "sheep", "lamb", "flamingo", "pangolin", "lion", "tiger", "chihuahua", "cat", "bunny" };
	private static final String[] ACTIONS = { "dancing", "pouting", "raving", "prancing", "swimming", "jumping",
			"flapping", "yodelling", "drinking", "flying", "slithering", "roaring" };
	
	public static String generateWrongEmail() {
		return 	ACTIONS[new Random().nextInt(ACTIONS.length)] 
				+ "-"
				+ ANIMALS[new Random().nextInt(ANIMALS.length)] 
				+ "@animalmail.com";
	}
}
