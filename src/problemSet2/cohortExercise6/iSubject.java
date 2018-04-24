package problemSet2.cohortExercise6;
//package Week3;

public interface iSubject {
	public void register(Observer o);
	public void unregister(Observer o);
	public void notifyObserversForPost();
	public void notifyObserversForComment(Comment c);
}
