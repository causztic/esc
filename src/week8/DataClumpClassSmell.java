package week8;

public class DataClumpClassSmell {
	
	public DataClumpClassSmell() {
		
	}
	
	public void doSomething1(Point3D point) {
		
	}
	
	public void doSomething2(Point3D point) {
		
	}

	public void doSomething3(Point3D point) {
		
	}
	
	// do other things without x, y and z


}

// this is a data class, no more responsibilities except getter and setter
class Point3D {
	
	int x;
	int y;
	int z;
	
	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setZ(int z) {
		this.z = z;
	}

	public int getX(int x) {
		return x;
	}
	
	public int getY(int y) {
		return y;
	}
	
	public int getZ(int z) {
		return z;
	}
}
