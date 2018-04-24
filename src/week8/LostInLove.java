package week8;


class Project {
	
}

class GirlFriend {
	Project myProject;
	
	public GirlFriend(Project myProject) {
		this.myProject = myProject;
	}
	
	public Project getProject() {
		return this.myProject;
	}
}

public class LostInLove {

	GirlFriend myGirlfriend;
	Project myProject;
	
	public LostInLove(GirlFriend myGirlfriend, Project myProject) {
		this.myGirlfriend = myGirlfriend;
		this.myProject = myProject;
	}
	
	// sudiptac: this is a misplaced responsibility
	// if the two classes (in this case, "GirlFriend" and "LostInLove") are talking 
	// to each other a bit too much, there is a feature envy smell
	public void doHerProject() {
		Project herProject = myGirlfriend.getProject();
		workOnProject(herProject);
	}
	
	public void doProject() {
		workOnProject(myProject);
	}
	
	public void workOnProject(Project p) {
		//some code here
	}
}
