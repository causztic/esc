package problemSet2.cohortExercise6;

import java.util.ArrayList;
import java.util.List;

public class Post implements iSubject{
	private List<Observer> observerList;
	private List<Comment> comments;
	private String text = "";
	
	public Post(String text){
		observerList = new ArrayList<>();
		comments = new ArrayList<>();
		this.text = text;
	}

	@Override
	public void register(Observer o) {
		// check if the observerList already has it
		if (!observerList.contains(o))
			observerList.add(o);
		else
			System.err.println("Already subscribed.");
	}

	@Override
	public void unregister(Observer o) {
		int observerIndex = observerList.indexOf(o);
		if (observerIndex < 0){
			System.err.println("You are not currently subscribed to this post.");
		} else {
			System.out.println(o.getUsername() + " has stopped subscription.");
			observerList.remove(observerIndex);
		}
	}

	@Override
	public void notifyObserversForPost() {
		for (Observer obs: observerList){
			obs.notifyPostUpdate(this);
		}
	}
	
	@Override
	public void notifyObserversForComment(Comment comment) {
		for (Observer obs: observerList){
			obs.notifyCommentUpdate(comment);
		}
	}

	public List<Comment> getComments() {
		return comments;
	}
	
	public String getText(){
		return text;
	}
	
	protected void setText(String text){
		this.text = text;
		// notify observers after text is edited.
		this.notifyObserversForPost();
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
		// notify observers after comment is added
		this.notifyObserversForComment(comment);
	}
}
