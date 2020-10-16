package banking.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import banking.pojo.Bank;

/**
 * Executes Simple Bank Access Protocol commands from a socket.
 */
public class BankService implements Runnable {
	private Logger logger;
	private Socket s;
	private Scanner in;
	private PrintWriter out;
	private Bank bank;
	
	/**
	 * Constructs a service object that processes commands from a socket for a bank.
	 * 
	 * @param aSocket the socket
	 * @param aBank   the bank
	 */
	public BankService(Logger logger, Socket s, Bank bank) {
		this.logger = logger;
		this.s = s;
		this.bank = bank;
	}

	public void run() {
		try {
			try {
				in = new Scanner(s.getInputStream());
				out = new PrintWriter(s.getOutputStream());
				doService();
			} finally {
				s.close();
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Executes all commands until the QUIT command or the end of input.
	 */
	public void doService() throws IOException {
		while (true) {
			if (!in.hasNext())
				return;
			String command = in.nextLine();
			String[] tokens = command.split(" ");
			if (tokens[0].equals("QUIT"))
				return;
			else
				executeCommand(tokens);
		}
	}

	public void executeCommand(String[] tokens) {
		logger.log(Level.INFO,"Command at start of executeCommand is " + tokens[0]);
		double amount;
		int account;
		try {
			account = Integer.parseInt(tokens[1]);
		} catch (Exception e) {
			logger.log(Level.WARNING,"Expected account number");
			logger.log(Level.WARNING,e.getMessage(),e);
			return;
		}
		switch (tokens[0]) {
			case "DEPOSIT" : 
				try {
					amount = Integer.parseInt(tokens[2]);
				} catch (Exception e) {
					logger.log(Level.WARNING,"Expected amount");
					logger.log(Level.WARNING,e.getMessage(),e);
					return;
				}
				logger.log(Level.INFO,"Deposit on account " + account + ": " + tokens[1] + ": " + amount);
				bank.deposit(account, amount);
				logger.log(Level.INFO,"Balance on account " + account + ": " + bank.getBalance(account));
				out.println(account + " " + bank.getBalance(account));
				out.flush();
				break;
			case "WITHDRAW" :
				try {
					amount = Integer.parseInt(tokens[2]);
				} catch (Exception e) {
					logger.log(Level.WARNING,"Expected amount");
					logger.log(Level.WARNING,e.getMessage(),e);
					return;
				}
				logger.log(Level.INFO,"WITHDRAW on account " + account + ": " + tokens[1] + ": " + amount);
				bank.withdraw(account, amount);
				logger.log(Level.INFO,"Balance on account " + account + ": " + bank.getBalance(account));
				out.println(account + " " + bank.getBalance(account));
				out.flush();
				break;
			case "BALANCE" :
				logger.log(Level.INFO,"Balance on account " + account + ": " + bank.getBalance(account));
				out.println(account + " " + bank.getBalance(account));
				out.flush();
				break;
			default :
				logger.log(Level.WARNING,"Invalid command " + tokens[0]);
				out.println("Invalid command");
				out.flush();			
		}
		logger.log(Level.INFO,"Transaction on account " + account + " " + tokens[0] + " terminated");		
	}
}
