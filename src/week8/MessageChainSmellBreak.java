package week8;
// My president duties
class PresidentDuties extends Duties {
	public PresidentDuties() {
		
	}
	
	public Duties nextDuty() {
		return new AdminDuties();
	}

	public void performPresidentJob() {
		
	}
}

public class MessageChainSmellBreak {
	public static void main (String args[]) {
		Duties myDuties = new TeachingDuties();
		// sudiptac: this is a long message chain smell
		// likely to introduce bug in the future
		//((AdminDuties) myDuties.nextDuty().nextDuty()).performAdminJob();
	}
}
