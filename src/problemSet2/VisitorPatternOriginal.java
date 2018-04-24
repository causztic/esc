package problemSet2;

import java.util.ArrayList;

public class VisitorPatternOriginal {

	public static void main(String[] args){
		SUTD oneSUTD = new SUTD();
		
		ArrayList<Employee> employee = oneSUTD.getEmployee();
		Auditor auditor = new Auditor();
		for (Employee e: employee){
			e.accept(auditor);
		}
	}
}

class SUTD {
	private ArrayList<Employee> employee; 
	
	public SUTD() {
		employee = new ArrayList<Employee>(); 
		employee.add(new Professor("Sun Jun", 0));
		employee.add(new AdminStuff("Stacey", 5));
		employee.add(new Student("Allan", 3));		
	}
	
	public ArrayList<Employee> getEmployee () {
		return employee;
	}
}

abstract class Employee{
	abstract void accept(Visitor v);
}

class Auditor implements Visitor{
	
	@Override
	public void visit(Employee e){
		e.accept(this);
	}

	@Override
	public void visit(Professor p) {
		System.out.println("Prof: " + p.getName() + p.getNo_of_publications());
	}

	@Override
	public void visit(AdminStuff a) {
		System.out.println("Staff: " + a.getName() + a.getEfficiency());
	}

	@Override
	public void visit(Student s) {
		// TODO Auto-generated method stub
		System.out.println("Student: " + s.getName() + s.getGPA());
		
	}
	
}

interface Visitor {
	void visit (Employee e);
	void visit(Professor p);
	void visit(AdminStuff a); // should be AdminStaff?
	void visit(Student s);
}

class Professor extends Employee{
	private String name;
	private int no_of_publications;
	
	public Professor (String name, int no_of_publications) {
		this.name = name;
		this.no_of_publications = no_of_publications;
	}
	
	public void accept(Visitor v) { 
		v.visit(this); 
	}
	
	public String getName () {
		return name;
	}

	public int getNo_of_publications() {
		return no_of_publications;
	}
}

class AdminStuff extends Employee{
	private String name;
	private float efficiency;
	
	public AdminStuff (String name, float efficiency) {
		this.name = name;
		this.efficiency = efficiency;
	}
	
	public void accept(Visitor v) { 
		v.visit(this); 
	}
	
	public String getName() {
		return name;
	}

	public float getEfficiency() {
		return efficiency;
	}
}

class Student extends Employee{
	private String name;
	private float GPA;
	
	public Student (String name, float GPA) {
		this.name = name;
		this.GPA = GPA;
	}
	
	public void accept(Visitor v) { 
		v.visit(this); 
	}
	
	public String getName() {
		return name;
	}

	public float getGPA() {
		return GPA;
	}
}
