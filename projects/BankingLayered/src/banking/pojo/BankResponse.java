package banking.pojo;

public class BankResponse {
	public static int STATUS_OK = 200;
	public static int STATUS_INVALID_REQUEST = 400;
	public static int STATUS_INVALID_ACCOUNT = 415;
	
	private int status;
	private int account;
	private double balance;
	
	public BankResponse(int status, int account, double balance) {
		super();
		this.status = status;
		this.account = account;
		this.balance = balance;
	}

	public BankResponse (String j) {
		String[] values = j.split(" ");
		this.status = Integer.parseInt(values[0]);
		this.account = Integer.parseInt(values[1]);
		this.balance = Double.parseDouble(values[2]);
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return status + " " + account + " " + balance;
	}
}
