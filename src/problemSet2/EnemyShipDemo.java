package problemSet2;
import java.util.Scanner;

public class EnemyShipDemo {

	public static void main(String[] args){
			
		EnemyShip theEnemy = null;
				
		Scanner userInput = new Scanner(System.in);
			
		String enemyShipOption = "";
			
		System.out.print("What type of ship? (U or R)");
			
		if (userInput.hasNextLine()){	
			enemyShipOption = userInput.nextLine();				
		}
		
		userInput.close();
		
		theEnemy = EnemyShipFactory.createShip(enemyShipOption);
			
		doStuffEnemy(theEnemy);
	}
		
	public static void doStuffEnemy(EnemyShip anEnemyShip){
		anEnemyShip.displayEnemyShip();
	}
}

class EnemyShipFactory {
	public static EnemyShip createShip(String type){
		EnemyShip ship = null;
		
		if (type.equals("U")){	
			ship = new UFOEnemyShip();				
		} else if (type.equals("R")){
			ship = new RocketEnemyShip();	
		} else {		
			ship = 	new BigUFOEnemyShip();
		}
		return ship;
	}
}

abstract class EnemyShip {
	
	private String name;
	// sudiptac: damage factor distinguishes a ship
	private double amtDamage;
	
	public String getName() { return name; }
	public void setName(String newName) { name = newName; }
	
	public double getDamage() { return amtDamage; }
	public void setDamage(double newDamage) { amtDamage = newDamage; }
	
	public void displayEnemyShip(){		
		System.out.println(getName() + " is on the screen");
	}
}

class UFOEnemyShip extends EnemyShip {
	public UFOEnemyShip(){	
		setName("UFO Enemy Ship");		
		setDamage(20.0);		
	}
}

class BigUFOEnemyShip extends EnemyShip {
	public BigUFOEnemyShip(){	
		setName("Big UFO Enemy Ship");		
		setDamage(40.0);		
	}
}

class RocketEnemyShip extends EnemyShip {
	public RocketEnemyShip(){
		setName("Rocket Enemy Ship");
		setDamage(10.0);
	}
}
