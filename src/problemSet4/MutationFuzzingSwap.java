package problemSet4;

import java.util.Random;

public class MutationFuzzingSwap extends Mutator{
	// Given an input string, implement a mutation operator that choose a random position
	// in the string and swap the adjacent characters. Meaning if SUTD is an input string and 2 is chosen
	// as a random position, the output should be SUDT. Careful about the string length bound check.
	public String mutate(String str){
		int index = new Random().nextInt(str.length() - 1);
		// convert str to array
		char[] arr = str.toCharArray();
		char temp = str.charAt(index+1);
		arr[index+1] = str.charAt(index);
		arr[index] = temp;
		
		return new String(arr);
	}
	
	public static void main(String[] args){
		// bonus marks maybe?
		MutationFuzzingSwap mfs = new MutationFuzzingSwap();
		System.out.println(mfs.mutate("SUDIPTA"));
	}
}
