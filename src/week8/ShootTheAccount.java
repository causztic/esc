package week8;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ShootTheAccount {
  private int balance = 0;
  private List<Transaction> ListOfAllTransactions = new ArrayList<Transaction>();
  private String lastDebitTime;
  private String accountType = "personal";
  
  public ShootTheAccount (String accountType) {
	  this.accountType = accountType;
  }

  public ShootTheAccount (int balance) {
    this.balance = balance;
  }
  
  public void deposit (int amount) {
    balance += amount;
  }
  
  public void withdraw (int amount) {
	  balance -= amount;
  }
    
  public void setBalance (int amount) {
    balance = amount;
  }
  
  public int getBalance () {
    return balance;
  }
  
  // this method has a long method smell
  public void CreditTransaction(int amount) {
	  
	//increase the amount
	deposit(amount);
	  
	//add to the transaction list
	ListOfAllTransactions.add(new Transaction("debit", amount));
	  	  
  }

  
  // this method has a long method smell
  public void DebitTransaction(int amount) {
	  
	  if (isValidBalance()) {
		  //reduce the amount
		  withdraw(amount);
	  
		  //add to the transaction list
		  ListOfAllTransactions.add(new Transaction("debit", amount));
	  
		  //update the last debit date
		  Calendar cal = Calendar.getInstance();
	  
		  lastDebitTime = cal.get(Calendar.DATE) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR);  
	  }	  
  }
  
  // this method has a long method smell
  public void Transfer(int amount, ShootTheAccount Benf) {
	  
	  if (isValidBalance()) {
		  //reduce the amount
		  withdraw(amount);
	  
		  //add to the transaction list
		  ListOfAllTransactions.add(new Transaction("debit", amount));
	  
		  //update the last debit date
		  Calendar cal = Calendar.getInstance();
	  
		  lastDebitTime = cal.get(Calendar.DATE) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR);  
		  
		  Benf.CreditTransaction(amount);
	  }	  
  }
  
  public boolean isValidBalance(){
	  int balanceTreshold = 500;
	  boolean valid = balance >= balanceTreshold || !accountType.equals("personal");
	  
	  if (!valid)
		  System.out.printf("Balance must be more than %d, please deposit\n", balanceTreshold);
	  
	  return valid;
  }

}                                                                                                                                                                                                       
