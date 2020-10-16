package banking.pojo;


public class SerializeDemo {
	public static void main(String[] args) {
			BankRequest br1 = new BankRequest(BankRequest.WITHDRAW,3,1000);
			System.out.println(br1);
			String s1 = br1.toString();
			BankRequest br2 = new BankRequest(s1);
			System.out.println(br2);	
	}
}
