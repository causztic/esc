package problemSet3;
import org.junit.*;
import static org.junit.Assert.*;

public class RussianBlackBoxTest {
	
	@Test
	public void testMultiplication(){
		int result = Russian.multiply(5, 4);
		assertEquals(result, 5*4);
	}
}
