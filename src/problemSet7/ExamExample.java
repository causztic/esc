package problemSet7;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class ExamExample {
	// Write a program to simulate the following.
	// During exam, the examiner (a thread) who waits patiently for a known
	// number of students (one thread for each student) to get ready; the exam
	// starts when all students are ready, and it starts at the same time for
	// all. Afterwards, the examiner patiently waits for all students to hand in
	// their scripts. Students are free to leave once they hand in the exam.
	// Once the last student hands in the exam, the examiner stops waiting and
	// leaves with the scripts. Choose the right synchronizer to simplify your
	// task.

	
	private Phaser phaser;
	private final int numberOfStudents;
	private static final long PREPARATION_TIME = 15;
	private static final long EXAM_TIME = 2;
	
	public ExamExample(int numberOfStudents){
		this.numberOfStudents = numberOfStudents;
		phaser = new Phaser();
	}
	
	public void startExam(){
		try {
			for (int i = 0; i < numberOfStudents; i++){
				new Thread(new Student(i)).start();
			}
			phaser.awaitAdvanceInterruptibly(0, PREPARATION_TIME, TimeUnit.MINUTES);
			// waits for 15 minutes. otherwise just start the exam.
			System.out.println("starting exam!");
			// reset the countdownlatch.
			// waits for at most the length of the exam. 
			phaser.awaitAdvanceInterruptibly(1, EXAM_TIME, TimeUnit.HOURS);
			System.out.println("Examminer collected all papers and leaves with the scripts.");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		// examiner is the main thread.
		ExamExample ee = new ExamExample(3);
		ee.startExam();
	}
	
	class Student implements Runnable {
		private int index;
		
		Student(int index){
			this.index = index;
		}
		
		private void getReady() throws Exception{
			phaser.register();
			Thread.sleep(new Random().nextInt(5000));
			System.out.println("Student " + index + " is ready.");

		}
		
		private void doPaper() throws Exception{
			Thread.sleep(new Random().nextInt(5000));
		}
		
		private void handInPaper() throws Exception{
			System.out.println("Student " + index + " is done with the paper! handed in.");
			phaser.arriveAndDeregister();
		}
		
		@Override
		public void run() {
			try {
				getReady();
				phaser.arriveAndAwaitAdvance();
				doPaper();
				handInPaper();
			} catch (Exception e) {
				System.err.println("Student died halfway through the exam. RIP");
			} finally {
				System.out.println("Student " + index + " has left the exam venue.");
			}
		}
		
	}
}
