package week11;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CohortExercise2 {
	public static void main(String[] args) {
		String[] grades = new String[] { "A", "B", "C", "C", "D", "F", "A", "E", "A", "B", "C", "C", "D", "F", "A", "E",
				"A", "B", "C", "C", "D", "F", "A", "E", "A", "B", "C", "C", "D", "F", "A", "E", "A", "B", "C", "C", "D",
				"F", "A", "E", "A", "B", "C", "C", "D", "F", "A", "E", "A", "B", "C", "C", "D", "F", "A", "E" };
		final int threadCount = 2;
		final Thread[] threads = new Thread[threadCount];
		final CountDownLatch countdown = new CountDownLatch(7);
		int chunk = grades.length / threadCount;
		for (int i = 0; i < threadCount; i++) {
			Thread t = new Thread(
					new GradeChecker(Arrays.copyOfRange(grades, i * chunk, i * chunk + chunk), countdown));
			threads[i] = t;
			t.start();
		}
		try {
			countdown.await(1L, TimeUnit.MINUTES);
			System.out.println("stopped");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class GradeChecker implements Runnable {
	CountDownLatch c;
	String[] grades;

	public GradeChecker(String[] grades, CountDownLatch c) {
		this.grades = grades;
		this.c = c;
	}

	public void run() {
		for (String grade : grades) {
			if (c.getCount() == 0) {
				break;
			}
			if (grade.equals("F")) {
				c.countDown();
				System.out.println("found an F");
			}
		}
	}
}
