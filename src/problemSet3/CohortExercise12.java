package problemSet3;

import org.junit.*;

public class CohortExercise12 {

	@Test(timeout=10000)
	public void testMethodTerminates(){
		Disk disk = new Disk(1001, -2); 
		// threshold - (x+y) > 0, but since x > 5, y > 12, x > 1000, y < 1,
		// threshold += 1 per iteration.
		// Hence threshold continously increases
		disk.manipulate();
	}
}
