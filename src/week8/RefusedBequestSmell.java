package week8;
abstract class Employee {

}

class Professor extends Employee {

	// Professor do research, teaching, admin, outReach

  void doResearch() {

	}
  
	void doTeaching() {

	}
  
	void doAdmin() {

	}
  
	void doOutreach() {

	}
}

class Postdoc extends Professor {

	//Postdoc do research (but no teaching, admin or outreach duties)
	// inheriting from professor class is flawed as many inherited functions 
  // remain completely unused
	
	// This is refused Bequest

}

class Admin extends Employee {
	//Admins help with administrative matter
}

public class RefusedBequestSmell {

}

