package banking.pojo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
   A bank account has a balance that can be changed by 
   deposits and withdrawals.
*/
public class BankAccount {

	   private double balance;

   /**
      Constructs a bank account with a zero balance.
   */
   public BankAccount() {   
      balance = 0;
   }

   /**
      Constructs a bank account with a given balance.
      @param initialBalance the initial balance
   */
   public BankAccount(double initialBalance) {   
      balance = initialBalance;
   }

   /**
      Deposits money into the bank account.
      @param amount the amount to deposit
   */
   public synchronized void deposit(double amount) {  
       try {
         double newBalance = balance + amount;
         balance = newBalance;
      }
      finally {
       }
   }

   /**
      Withdraws money from the bank account.
      @param amount the amount to withdraw
   */
   public synchronized void withdraw(double amount) {   
       try {
         double newBalance = balance - amount;
         balance = newBalance;
      }
      finally {
       }
   }

   /**
      Gets the current balance of the bank account.
      @return the current balance
   */
   public double getBalance() {   
      return balance;
   }

}

