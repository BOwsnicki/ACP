package threads.raceconditions.synched1.nocond;

/**
 * A bank account has a balance that can be changed by deposits and withdrawals.
 */
public class BankAccount {
	private double balance;

	/**
	 * Constructs a bank account with a zero balance.
	 */
	public BankAccount() {
		balance = 0;
	}

	/**
	 * Deposits money into the bank account.
	 * 
	 * @param amount the amount to deposit
	 */
	public synchronized void deposit(double amount) {
		System.out.print("Depositing " + amount);
		double newBalance = balance + amount;
		System.out.println(", new balance is " + newBalance);
		balance = newBalance;
	}

	/**
	 * Withdraws money from the bank account.
	 * 
	 * @param amount the amount to withdraw
	 */
	public synchronized void withdraw(double amount) throws InterruptedException {
		System.out.print("Withdrawing " + amount);
		double newBalance = balance - amount;
		System.out.println(", new balance is " + newBalance);
		balance = newBalance;
	}

	/**
	 * Gets the current balance of the bank account.
	 * 
	 * @return the current balance
	 */
	public double getBalance() {
		return balance;
	}
}
