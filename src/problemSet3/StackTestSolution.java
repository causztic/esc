package problemSet3;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StackTestSolution {
	private Stack<Integer> stack;
	// setUp method using @Before syntax
	// @Before methods are run before each test
	@Before 
	public void runBeforeEachTest(){
	    stack = new Stack<Integer>();
	}
	
	@Test 
	public void testRepOk(){
	   assertEquals(true, stack.repOK());
	}
	
	@Test
	public void testPushRep(){
		stack.push(new Integer(1));
		assertEquals(true, stack.repOK());
	}
	
	@Test
	public void testPopRep(){
		stack.pop();
		assertEquals(true, stack.repOK());
	}
	
	@Test
	public void testPushPopRep(){
		stack.push(new Integer(1));
		stack.pop();
		assertEquals(true, stack.repOK());
	}
}
