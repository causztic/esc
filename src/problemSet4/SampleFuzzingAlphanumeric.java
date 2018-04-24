package problemSet4;

import java.util.Random;

public class SampleFuzzingAlphanumeric {
	// Often input strings are expected to be alphanumeric.
	// Modify the simple fuzzer to generate random alphanumeric strings of
	// length between 0 and 1024. You can use any programming language.
	private static final String[] POOL = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f", "g", "h", "i","j", "k", "l", "m", "n", "o", "p",
			"q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	public static String generate(){
		// generate length
		int length = new Random().nextInt(1025);
		String result = "";
		for (int i = 0; i < length; i++){
			int index = new Random().nextInt(POOL.length);
			result += POOL[index];
		}
		return result;
	}
	public static void main(String[] args){
		System.out.println(SampleFuzzingAlphanumeric.generate());
	}
}
