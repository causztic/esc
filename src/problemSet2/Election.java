package problemSet2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// Strategy Design is used as the behaviour of the Electorates can change after voting

public class Election {
	private static List<Candidate> candidates = new ArrayList<>();
	static Map<Candidate, Integer> votes = new HashMap<>();
	
	public static void addCandidate(Candidate c){
		candidates.add(c);
		votes.put(c, 0);
	}
	
	public static void vote(Candidate c){
		votes.put(c, votes.get(c) + 1);
	}
	
	public static void getWinner(){
		Candidate candidate = null;
		int currentHighest = 0;
		for (Entry<Candidate, Integer> vote: votes.entrySet()){
			if (vote.getValue() > currentHighest){
				candidate = vote.getKey();
				currentHighest = vote.getValue();
			}
		}
		
		System.out.println(candidate.getName() + " has won!");
	}
	
	public static void main(String[] args){
		Candidate a = new Candidate("A");
		Candidate b = new Candidate("B");
		addCandidate(a);
		addCandidate(b);
		for (int i = 0; i < 5; i++){
			Electorate e = new Electorate("Voter " + i);
			e.vote(Math.random() > 0.5 ? a : b);
		}
		getWinner();
	}
}

interface Votable{
	public boolean votable();
	public void vote(Candidate c);
}

class Voted implements Votable {
	public boolean votable(){
		return false;
	}

	@Override
	public void vote(Candidate c) {
		System.err.println("Already voted!");
	}
}

class NotYetVoted implements Votable {
	public boolean votable(){
		return true;
	}

	@Override
	public void vote(Candidate c) {
		System.out.println("Voted for " + c);
		Election.vote(c);
	}
}

class Candidate {
	private String name = "";
	public Candidate(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}

class Electorate {
	private Votable votable;
	private String name = "";
	public Electorate(String name){
		this.votable = new NotYetVoted();
		this.name = name;
	}
	
	public void vote(Candidate c){
		votable.vote(c);
		this.votable = new Voted();
	}
	
	public String getName(){
		return name;
	}
}