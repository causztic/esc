package week8;

public class LiveAsCouple {
	Project[] ourProject;

	public LiveAsCouple(Project myProject, Project herProject) {
		this.ourProject[0] = myProject;
		this.ourProject[1] = herProject;
	}
	
	public Project[] getProject() {
		return this.ourProject;
	}
	
	// sudiptac: work as a team
	public void doProject() {
		workOnProject(ourProject[0]);
		workOnProject(ourProject[1]);
	}
	
	public void workOnProject(Project p) {
		//some code here
	}
}
