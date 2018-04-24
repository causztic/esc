package problemSet3;
import org.junit.*;
import static org.junit.Assert.*;

public class RussianWhiteBoxTest {
	
	@Test
	public void testNSmallerThanOne(){
		int result = Russian.multiply(5, 0);
		assertEquals(result, 5*0);
	}
	
	@Test
	public void testNOdd(){
		int result = Russian.multiply(5, 3);
		assertEquals(result, 5*3);
	}
	
	@Test
	public void testNEven(){
		int result = Russian.multiply(5, 4);
		assertEquals(result, 5*4);
	}
}
