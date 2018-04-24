package week8;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class Transaction {
	private String type;
	private int amount;
	
	public Transaction (String newType, int newAmount) {
		this.type = newType;
		this.amount = newAmount;
	}
}


public class AccountSmell {
  private int balance = 0;
  private List<Transaction> ListOfAllTransactions = new ArrayList<Transaction>();
  private String lastDebitTime;
  
  public AccountSmell () {
  }

  public AccountSmell (int balance) {
    this.balance = balance;
  }
  
  public void deposite (int amount) {
    balance += amount;
  }
    
  public void setBalance (int amount) {
    balance = amount;
  }
  
  public int getBalance () {
    return balance;
  }
  
  // this method has a long method smell
  public void DebitTransaction(int amount) {
	  //reduce the amount
	  balance = balance - amount;
	  
	  //add to the transaction list
	  ListOfAllTransactions.add(new Transaction("debit", amount));
	  
	  //update the last debit date
	  Calendar cal = Calendar.getInstance();
	  
	  lastDebitTime = cal.get(Calendar.DATE) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR);  
  }

}                                                                                                                                                                                                       
