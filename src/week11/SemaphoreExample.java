package week11;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {	
	  private final Semaphore roomOrganizer=new Semaphore(3, true); //true: first come, first serve

	  public static void main(String[] args) {
		  System.out.println("lalal");		  SemaphoreExample test=new SemaphoreExample();
		  test.mystart();		  
	  }
	  
	  public void mystart() {
		  for(int i=0; i<10; i++) {
				 People people=new People();
				 people.start();
			 }
	  }
	  
	  class People extends Thread {
		  public void run() {
			  try {
				  roomOrganizer.acquire();
			  } catch (InterruptedException e) {
				  System.out.println("received InterruptedException");
				  return;
			  }
		
			  System.out.println("Thread "+this.getId()+" starts to use the room");
		
			  try {
				  sleep(1000);
			  } catch (InterruptedException e) {		  
			  }
		
			  System.out.println("Thread "+this.getId()+" leaves the room\n");
			  roomOrganizer.release();
		  }
	  }
}