package backend;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBServer {
	private static final int DB_PORT = 7070;

	private Connection conn;
	
	private void loadSongs() {
		Statement stmt;
		try {
			stmt = conn.createStatement();
	        try {
	            stmt.executeUpdate("DROP TABLE Songs");
	        } catch (SQLException e) {
	        }
			stmt.execute("CREATE TABLE Songs (title CHAR(50), artist CHAR(50), mood CHAR(50))");
			stmt.execute("INSERT INTO Songs VALUES ('Walking on Sunshine','Katrina and the Waves','happy')");
			stmt.execute("INSERT INTO Songs VALUES ('Poison and Wine','The Civil Wars','sad')");
			stmt.execute("INSERT INTO Songs VALUES ('So What','Pink','angry')");
			stmt.execute("INSERT INTO Songs VALUES ('Hurt','Johnny Cash','sad')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Connection getConnection() {
		return conn;
	}
	
	public DBServer() {	
			String driver = "org.apache.derby.jdbc.EmbeddedDriver";
			try {
				Class.forName(driver);			
				conn = DriverManager.getConnection("jdbc:derby:Songs;create=true","APP","APP");
				loadSongs();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
	}
	
    public static void main(String[] args) {
    	DBServer dbServ = new DBServer();
        ServerSocket server = null;
        
        try {
            // Obtain server socket
            server = new ServerSocket(DB_PORT);
            System.out.println("DB started on port " + DB_PORT);
            while (true) {
                Socket socket = server.accept();
                Thread t = new Thread(new DBRunnable(dbServ.getConnection(),socket));
                t.start();
            }
        } catch (IOException ex) {
            System.err.println("Unable to start server.");
        } finally {
            try {
              if (server != null)
                  server.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }
    }
}
