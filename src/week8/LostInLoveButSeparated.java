package week8;
class SeparatedGirlFriend {
	Project myProject;
	
	public SeparatedGirlFriend(Project myProject) {
		this.myProject = myProject;
	}
	
	public Project getProject() {
		return this.myProject;
	}
	
	// sudiptac: everyone must take care of his/her own project
	// No more misplaced responsibility
	public void doProject() {
		workOnProject(myProject);
	}
	
	public void workOnProject(Project p) {
		//some code here
	}
}

public class LostInLoveButSeparated {

	SeparatedGirlFriend myGirlfriend;
	Project myProject;
	
	public LostInLoveButSeparated(SeparatedGirlFriend myGirlfriend, Project myProject) {
		this.myGirlfriend = myGirlfriend;
		this.myProject = myProject;
	}
	
	public void doProject() {
		workOnProject(myProject);
	}
	
	public void workOnProject(Project p) {
		//some code here
	}
}

