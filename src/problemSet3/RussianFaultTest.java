package problemSet3;
import org.junit.*;
import static org.junit.Assert.*;

public class RussianFaultTest {
	
	@Test
	public void testNegativeN(){
		int result = Russian.multiply(5, -1);
		assertEquals(result, -5);
	}
}
