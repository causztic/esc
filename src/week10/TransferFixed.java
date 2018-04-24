package week10;

public class TransferFixed {
	private static final Object tieLock = new Object ();
	
	private void transfer (Account fromAccount, Account toAccount, int amount) throws Exception {
		if (fromAccount.getBalance() < amount) {
			throw new Exception();
		}
		else {
			fromAccount.debit(amount);
			toAccount.credit(amount);
		}
	}
	
	public void transferMoney (Account fromAccount, Account toAccount, int amount) throws Exception {		
		int fromHash = System.identityHashCode(fromAccount);
		int toHash = System.identityHashCode(toAccount);
		
		if (fromHash < toHash) {
			// hold the lock of from > to always. this ensures multiple threads will hold the same global order.
			synchronized (fromAccount) {
				synchronized (toAccount) {
					transfer(fromAccount, toAccount, amount);
				}
			}
		}
		else if (fromHash > toHash) {
			// hold the lock of from > to always. this ensures multiple threads will hold the same global order.
			synchronized (toAccount) {
				synchronized (fromAccount) {
					transfer(fromAccount, toAccount, amount);
				}
			}			
		}
		else {
			// if they are the same account, only ensure one thread is performing a transfer on it. 
			// though it is pretty nonsensical
			synchronized (tieLock) {
				synchronized (fromAccount) {
					synchronized (toAccount) {
						transfer(fromAccount, toAccount, amount);
					}
				}
			}
		}
	}
}

class Account {
	private int amount; 
	
	public int getBalance () {
		return amount;
	}
	
	public void debit (int n) {
		amount = amount - n;
	}
	
	public void credit (int n) {
		amount = amount + n;
	}
}

