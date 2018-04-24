package week12;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
 
public class ParallelRecursive<T> {
	public<T> void sequentialRecursive (List<Node<T>> nodes, Collection<T> results) {
		for (Node<T> n : nodes) {
			results.add(n.compute());
			sequentialRecursive(n.getChildren(), results);
		}
	}
	
	public<T> void parallelRecursive(final Executor exec, List<Node<T>> nodes, final Collection<T> results) {
		for (final Node<T> n : nodes) {
			exec.execute(new Runnable () {
				public void run() {
					results.add(n.compute());
				}
			});

			parallelRecursive(exec, n.getChildren(), results);
		}		
	}
	
	public<T> Collection<T> getParallelResults (List<Node<T>> nodes) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Queue<T> resultQueue = new ConcurrentLinkedQueue<T>();
		parallelRecursive(exec, nodes, resultQueue);
		exec.shutdown();
		exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		return resultQueue;
	}
}