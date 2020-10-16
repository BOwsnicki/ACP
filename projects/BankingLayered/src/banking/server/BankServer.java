package banking.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

import banking.pojo.Bank;

public class BankServer
{  
   private static final int ACCOUNTS_LENGTH = 10;
   
   private static final String LOG_FILE = "/home/cop4856/logs/bank.log";
   private static final Logger LOGGER = Logger.getLogger(BankServer.class.getName());
   static {
       FileHandler fh = null;
		try {
			fh = new FileHandler(LOG_FILE);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       LogManager.getLogManager().reset();
       LOGGER.addHandler(fh);
       SimpleFormatter formatter = new SimpleFormatter();  
       fh.setFormatter(formatter);  
   }
   
   
   public static void main(String[] args) throws IOException
   {  
      Bank bank = new Bank(ACCOUNTS_LENGTH);
      
      final int SBAP_PORT = 8888;
      ServerSocket server = new ServerSocket(SBAP_PORT);
      System.err.println("Waiting for clients to connect...");
      
      while (true)
      {
         Socket s = server.accept();
         LOGGER.log(Level.INFO,"Client connected.");
         BankService service = new BankService(LOGGER, s, bank);
         Thread t = new Thread(service);
         t.start();
      }
   }
}