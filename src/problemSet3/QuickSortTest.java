package problemSet3;
//package Week4;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.*;

// sudiptac: include this for running parameterized tests
@RunWith(Parameterized.class)

public class QuickSortTest {
	public int[] array;
	public int[] expected;
    
	// classic constructor
	public QuickSortTest(int[] array, int[] expected) { 
    	this.array = array;
    	this.expected = expected;
    }

	@Parameters 
   	public static Collection<int[][]> parameters() {
        return Arrays.asList(new int[][][] {
            {{1,2,3,4}, {1,2,3,4}},
            {{4,3,2,1}, {1,2,3,4}},
            {{1,2,4,3}, {1,2,3,4}},
            {{1,4,3,2}, {1,2,3,4}}
        });
    }

   @Test 
   public void sortTest() {
	   QuickSort qs = new QuickSort();
	   qs.sort(array);
	   assertArrayEquals(array, expected);
   }
}
