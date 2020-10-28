package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBRunnable implements Runnable {
	private static final String QUERY_STRING = "SELECT * FROM Songs WHERE mood = ?";
	private static final String INSERT_STRING = "INSERT INTO Songs VALUES (?, ?, ?)";
	
	private Socket socket;
	private Connection connection;
	
	private PreparedStatement query;
	private PreparedStatement insert;
	
	public DBRunnable(Connection connection, Socket socket) {
		this.socket = socket;
		this.connection = connection;
	}
	
	private String processQuery(String mood) {
		synchronized (connection) { // bit wide...
			String result = "{";
			try {
				query.setString(1,mood);
				ResultSet rs = query.executeQuery();
				boolean first = true; 
				while(rs.next()){ 
					if (!first) {
						result += ", ";
					}
					result += "(" + rs.getString(1).trim() + ", " + rs.getString(2).trim() + ", " + rs.getString(3).trim() + ")";
					first = false;  
				}
				result += "}";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	}
	
	private String processInsert(String title,String artist,String mood) {
		synchronized (connection) { // bit wide..
			try {
			insert.setString(1,title);
			insert.setString(2,artist);
			insert.setString(3,mood);
			int rowsAffected = insert.executeUpdate();
			return "insert: " + rowsAffected;
			} catch (Exception e) {
				e.printStackTrace();
				return "insert: fail";
			}
		}

	}
	
	private String processRequest(String request) {
		// Syntax: 	get : angry
		//			post: Good Life : One Republic : happy 
		String[] parsed = request.split(":");
		switch (parsed[0].trim().toLowerCase()) {
		case "get"  :	return processQuery(parsed[1].trim());
		case "post" :	return processInsert(parsed[1].trim(),parsed[2].trim(),parsed[3].trim());
		default:		return "unknown";
		}
	}
	
    public void run() {
        Scanner in = null;
        PrintWriter out = null;
        try {
    		query = connection.prepareStatement(QUERY_STRING);
        	insert = connection.prepareStatement(INSERT_STRING);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            String request;
            while (((request = in.nextLine()) != null) && !request.equalsIgnoreCase("quit")) {
            	System.out.println("Message received:" + request);
            	String response = processRequest(request);
            	System.out.println("Responding:" + response);
                out.println(response);
                out.flush();
            }
        } catch (IOException | SQLException ex) {
        	System.err.println("Unable to get streams from client");
        } finally {
            try {
            	System.out.println("\"quit\" received. Terminating.");
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
