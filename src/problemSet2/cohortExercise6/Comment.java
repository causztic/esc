package problemSet2.cohortExercise6;

public class Comment {
	private Post post;
	private String text;
	
	public Comment(Post post, String text){
		this.text = text;
		this.post = post;
		post.addComment(this);
	}
	
	public Post getPost(){
		return post;
	}
	
	public String getText(){
		return text;
	}
}
