package problemSet2.cohortExercise6;

public class Test {
	public static void main(String[] args){
		System.out.println("Testing Post Observers\n");
		Subscriber s1 = new Subscriber("John Doe");
		Post post = s1.makePost("wowowow");
		Subscriber s2 = new Subscriber("Dancing Pangolin");
		Subscriber s3 = new Subscriber("Not A Subscriber");
		
		s2.subscribeToPost(post);
		s1.editPost(post, "changed");
		
		System.out.println("\nAttempting to edit someone else's post");
		s2.editPost(post, "This is not my post");
		
		s2.makeCommentOnPost(post, "I love this post!");
		
		s2.leavePost(post);
		
		s2.makeCommentOnPost(post, "I can still comment but I shouldn't get a ping");

	}
}
