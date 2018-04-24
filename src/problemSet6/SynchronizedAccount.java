package problemSet6;

import net.jcip.annotations.GuardedBy;

//As a rising star in a bank’s IT department, you have been given the job of creating a new bank
//	account class called SynchronizedAccount. This class must have methods to support the
//	following operations: deposit, withdraw, and check balance. Each method should print a status
//	message to the screen on completion. Also, the method for withdraw should return false and do
//	nothing if there are insufficient funds. Because the latest system is multi-threaded, these methods
//	must be designed so that the bookkeeping is consistent even if many threads are accessing a
//	single account. Additionally, to maximize concurrency, SynchronizedAccount should be
//	synchronized differently for read and write accesses. Any number of threads should
//	simultaneously be able to check the balance on an account, but only one thread can deposit or
//	withdraw at a time.

public class SynchronizedAccount {
	
	// Student note:
	// Atomic variables can be used instead of double to improve efficiency. 
	
	@GuardedBy("this")
	private double funds;

	// only one thread can deposit at a time.
	// technically multiple deposits should be able to be made.. but just following the question
	public synchronized void deposit(float amount) {
		funds += amount;
		System.out.println("Deposited: "+ funds);
	}

	// only one thread can withdraw at a time.
	public synchronized boolean withdraw(float amount) {
		boolean withdrawable = (funds < amount);
		if (withdrawable) {
			funds -= amount;
			System.out.println("Withdrawed: " + funds);
		} else {
			System.out.println("Not enough funds.");
		}
		return withdrawable;
	}

	// not synchronized to maximize reads.
	public void checkBalance() {
		System.out.println("Balance: " + funds);
	}
}
