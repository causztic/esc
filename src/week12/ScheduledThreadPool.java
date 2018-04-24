package week12;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
 
public class ScheduledThreadPool {
	private static final int NTHREADS = 10;
	//private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
	private static final ScheduledThreadPoolExecutor exec = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(NTHREADS);


    public static void main(String[] args) throws Exception {

		Runnable task = new Runnable () {
			public void run() {
				handleRequest(10);
			}
		};
			
		/* scheduled periodically after initial 5 seconds delay */
		//exec.scheduleAtFixedRate(task, 5, 5, TimeUnit.SECONDS);
		/* schedule only once after 5 seconds */
		exec.schedule(task, 5, TimeUnit.SECONDS);

		TimeUnit.SECONDS.sleep(30);

		exec.shutdown(); 
	}

	protected static void handleRequest(int count) {
		System.out.println("Task " + count + " is executing");
	}
}
