package problemSet2;

public class PizzaStore {

	public Pizza orderPizza (String type) {
		Pizza pizza = PizzaFactory.generatePizza(type);
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
}

class Pizza {

	public void prepare() {
	}

	public void box() {
	}

	public void cut() {
	}

	public void bake() {
	}
}

class CheesePizza extends Pizza {}
class GreekPizza extends Pizza {}
class PepperoniPizza extends Pizza {}

class PizzaFactory{
	public static Pizza generatePizza(String type){
		
		if (type.equals("cheese")) {
			return new CheesePizza();
		}
		if (type.equals("greek")) {
			return new GreekPizza();
		}
		if (type.equals("pepperoni")) {
			return new PepperoniPizza();
		}
		return null;
	}
}
