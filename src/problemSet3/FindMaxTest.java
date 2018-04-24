package problemSet3;
import static org.junit.Assert.*;
import org.junit.Test;

public class FindMaxTest {
	
	@Test
	public void computeCorrect(){
		int[] list = { 1,2,4,3 };
		assertTrue(FindMax.max(list) == 4);
	}
	
	@Test
	public void computeIncorrect(){
		int[] list = { 1,2,3,4 };
		assertTrue(FindMax.max(list) == 4);
	}
	
	@Test
	public void raiseError(){
		FindMax.max(null);
	}
}
