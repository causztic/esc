package week4;

import java.util.Scanner;

public class FactoryPatternDemoTelephoneSubscriber {

	public static void main(String[] args) {

		Switch theSwitch = null;

		Scanner userInput = new Scanner(System.in);

		String callOption = "";

		System.out.println("What type of call? (L:Local or R:Remote)");

		if (userInput.hasNextLine()) {
			callOption = userInput.nextLine();
		}

		if (callOption.equals("L")) {
			theSwitch = new localCallHandlingSystem();
		} else if (callOption.equals("R")) {
			theSwitch = new remoteCallHandlingSystem();
		} else {
			theSwitch = new remoteCallHandlingSystem();
		}

		makeCall(theSwitch);
	}

	public static void makeCall(Switch aSwitch) {
		aSwitch.displaySwitch();
		aSwitch.handleCall();
	}
}
