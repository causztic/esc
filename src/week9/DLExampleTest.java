package week9;

import org.junit.Test;

public class DLExampleTest {
	@Test
	public void testDeadlock() throws InterruptedException{
		Dispatcher d = new Dispatcher();
		Taxi taxi = new Taxi(d);
		d.addTaxi(taxi);
		Thread t1 = new Thread(){
			public void run(){
		        taxi.setLocation(new Point());
			}
		};
		
		Thread t2 = new Thread(){
			public void run(){
				d.getImage();
			}
		};
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
}