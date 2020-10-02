package threads.raceconditions.synched2;

/**
 * This program runs threads that deposit and withdraw money from the same bank
 * account.
 */
public class BankAccountThreadRunner {
	public static void main(String[] args) {
		BankAccount account = new BankAccount();
		final double AMOUNT = 100;
		final int REPETITIONS = 100;

		Runnable d = new DepositRunnable(account, AMOUNT, REPETITIONS);
		Runnable w = new WithdrawRunnable(account, AMOUNT, REPETITIONS);

		Thread dt = new Thread(d);
		Thread wt = new Thread(w);

		dt.start();
		wt.start();
		System.out.println("balance = $" + account.getBalance());
	}
}
