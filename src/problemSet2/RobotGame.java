package problemSet2;
class Robot {
	String name;
	IBehaviour behaviour;

	public Robot(String name)
	{
		this.name = name;
	}

	public void behave()
	{
		System.out.printf("%s moves %d\n", name, behaviour.moveCommand());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setBehavior(IBehaviour behaviour) {
		this.behaviour = behaviour;
	}
}

interface IBehaviour {
	public int moveCommand();
}

class AggressiveBehaviour implements IBehaviour{

	@Override
	public int moveCommand() {
		// TODO Auto-generated method stub
		// IT'S OVER 9000!
		return 9001;
	}
	
}
class DefensiveBehaviour implements IBehaviour {

	@Override
	public int moveCommand() {
		// TODO Auto-generated method stub
		// WEAK ASS
		return -1;
	}
	
}
class NormalBehaviour implements IBehaviour {

	@Override
	public int moveCommand() {
		// TODO Auto-generated method stub
		return 1;
	}
	
}

public class RobotGame {

	public static void main(String[] args) {

		Robot r1 = new Robot("Big Robot");
		Robot r2 = new Robot("George v.2.1");
		Robot r3 = new Robot("R2");

		r1.setBehavior(new NormalBehaviour());
		r2.setBehavior(new AggressiveBehaviour());
		r3.setBehavior(new DefensiveBehaviour());
		
		r1.behave();
		r2.behave();
		r3.behave();

		//change the behaviors of each robot.
		r1.setBehavior(new DefensiveBehaviour());
		r2.setBehavior(new NormalBehaviour());
		r3.setBehavior(new AggressiveBehaviour());
		
		r1.behave();
		r2.behave();
		r3.behave();
	}
}
