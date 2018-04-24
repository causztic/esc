package problemSet4;

public class cohortExercise6 {
	public static void main(String[] args){
		S s = new S();
		try {
			System.out.println(s.generateRandomRule());
		} catch(StackOverflowError e){
            System.err.println("Stack Overflow! Try running the program again.");
        }
	}
}
