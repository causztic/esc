package problemSet2.cohortExercise6;

import java.util.List;
import java.util.ArrayList;

public class Subscriber implements Observer{
	
	// duplicate here to determine the owner of posts and comments
	private List<Post> posts;
	private List<Comment> comments;
	private String username;
	
	public Subscriber(String username){
		posts = new ArrayList<>();
		comments = new ArrayList<>();
		this.setUsername(username); 
	}
	
	public Post makePost(String text){
		Post post = new Post(text);
		post.register(this);
		posts.add(post);
		SocialMedia.posts.add(post);
		System.out.println("Post made and subscribed to.");
		return post;
	}
	
	public void editPost(Post post, String text){
		boolean hasEdited = false;
		for (Post p: posts){
			if (p.equals(post)){
				// the post is created by this subscriber.
				p.setText(text);
				hasEdited = true;
				break;
			}
		}
		if (!hasEdited)
			System.err.println("Attempted to edit a post that is not found / not yours.");
	}
	
	public void subscribeToPost(Post post){
		post.register(this);
	}
	
	public void makeCommentOnPost(Post post, String text){
		Comment comment = new Comment(post, text);
		comments.add(comment);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void leavePost(Post post){
		post.unregister(this);
	}

	@Override
	public void notifyPostUpdate(Post p) {
		// Run when post is updated.
		System.out.printf("%s received Post Update about \"%s\"\n", username, p.getText());
	}

	@Override
	public void notifyCommentUpdate(Comment c) {
		// Run when a comment is created.
		System.out.printf("%s received Comment Update on id: %d, Comment is: \"%s\"\n", username, c.getPost().hashCode(), c.getText());
	}

}
