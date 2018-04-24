package problemSet3;

import org.junit.*;
import static org.junit.Assert.*;

public class DiskTest {
	private Disk disk;
	// Test statements.
	@Test
	public void testStatement(){
		disk = new Disk(6, 500); // first and third condition
		disk.manipulate();
	}
	
	@Test
	public void testStatement2(){
		disk = new Disk(4, 12); // second and third condition
		disk.manipulate();
	}
	
	@Test
	public void testStatement3(){
		disk = new Disk(1001, -2); // first and fourth condition
		disk.manipulate();
	}
	
	// Three test cases were used to go through every statement. It is the minimum number of test cases,
	
	// Test branches
	@Test
	public void testBranch(){
		disk = new Disk(500, 500); // test the while loop alternate
		disk.manipulate();
	}
	
	@Test
	public void testBranch2(){ // test 1st and 3rd
		disk = new Disk(6, 500);
		disk.manipulate();
	}
	
	@Test
	public void testBranch3(){ // test 1st alt, 2nd and 3rd
		disk = new Disk(4, 11);
		disk.manipulate();
	}
	
	@Test
	public void testBranch4(){ // test 1st alt, 2nd alt, and 3rd
		disk = new Disk(4, 13);
		disk.manipulate();
	}
	
	@Test
	public void testBranch5(){ // test 3rd alt and 4th
		disk = new Disk(1001, -2);
		disk.manipulate();
	}
	
	// 5 test cases were written to cover all branches. It is the minimum.
	// the 4th alt is unreachable as no condition will fulfill it.
}
