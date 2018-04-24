package problemSet4;

import java.util.Random;

public abstract class Grammar implements RuleSet{
	private String result = "";
	
	public Grammar(){
	}
	
	public Grammar(String result){
		this.result = result;
	}
	
	public String generateRandomRule(){
		String buffer = this.result;
		Grammar[][] rules = getRules();
		if (rules != null){
			Grammar[] ruleSet = rules[new Random().nextInt(rules.length)];
			for (Grammar rule: ruleSet){
				buffer += rule.generateRandomRule();
			}
		}

		return buffer;
	}
}
