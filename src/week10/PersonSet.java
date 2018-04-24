package week10;

import java.util.HashSet;
import java.util.Set;

//this class is thread-safe.
public class PersonSet {
	//@guarded by "this"
	private final Set<Person> mySet = new HashSet<Person>();
	//note that HashSet is not thread-safe!
	
	public synchronized void addPerson(Person p) {
		mySet.add(p);
	}
	
	public synchronized boolean containsPerson (Person p) {
		return mySet.contains(p);
	}

	class Person {
		public int x; 
	}
}
