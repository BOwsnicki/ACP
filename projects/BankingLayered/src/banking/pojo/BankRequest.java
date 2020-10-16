package banking.pojo;

public class BankRequest {

	public static final int DEPOSIT = 0;
	public static final int WITHDRAW = 1;
	public static final int BALANCE = 2;
	
	private int request;
	private int account;
	private double amount;
	
	public BankRequest(int request, int account, double amount) {
		super();
		this.request = request;
		this.account = account;
		this.amount = amount;
	}

	public BankRequest (String j) {
		String[] values = j.split(" ");
		this.request = Integer.parseInt(values[0]);
		this.account = Integer.parseInt(values[1]);
		this.amount = Double.parseDouble(values[2]);
	}
	
	public int getRequest() {
		return request;
	}

	public void setRequest(int request) {
		this.request = request;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return request + " " + account + " " + amount;
	}
}
