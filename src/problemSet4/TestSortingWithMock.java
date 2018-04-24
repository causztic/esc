package problemSet4;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

public class TestSortingWithMock {
	// Given Sorter.java and FindMaxUsingSorting.java, write a test case
	// for method FindMax without implementing Sorter.java first.
	
	@Test
	public void computeMax(){
		int[] list = { 1,2,4,3 };
		int[] sortedList = { 1,2,3,4 };
		Mockery context = new JUnit4Mockery();
		final Sorter sorter = context.mock(Sorter.class);
        context.checking(new Expectations() {{
            oneOf(sorter).sort(list);
            will(returnValue(sortedList));    
        }});
		assertTrue(FindMaxUsingSorting.findmax(list, sorter) == 4);
		context.assertIsSatisfied();
	}
}
