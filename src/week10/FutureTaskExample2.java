package week10;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskExample2 {
	public static void main(String[] args) {
        FutureTask<String> future = new FutureTask<>(new CallableTask());
        //Cancelling code before run
        /*boolean b = future.cancel(true);
        System.out.println("Cancelled="+b);*/        
        DemoFutureTask<String> myThread = new DemoFutureTask<String>(future);
        myThread.start();
        //Cancelling code after run
        /*boolean b = future.cancel(true);
        System.out.println("Cancelled="+b);*/
        System.out.println("Result=");
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("EXCEPTION!!!");
            e.printStackTrace();
        }
    }
}

class DemoFutureTask<E> extends Thread {
	private final FutureTask<E> task; 
	public DemoFutureTask (FutureTask<E> task) {
		this.task = task;
	}
	
	public void run () {
		task.run();
	}
}