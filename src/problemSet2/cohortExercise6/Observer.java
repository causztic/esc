package problemSet2.cohortExercise6;

public interface Observer {
	public void notifyPostUpdate(Post p);
	public void notifyCommentUpdate(Comment c);
	public String getUsername();
}
