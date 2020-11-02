package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import model.Song;

public class DBRunnable implements Runnable {
	private static final String QUERY_STRING = "SELECT * FROM Songs WHERE ";
	private static final String INSERT_STRING = "INSERT INTO Songs VALUES ";
	
	private Socket socket;
	private Connection connection;
	
	private Statement query;
	private Statement insert;
	
	public DBRunnable(Connection connection, Socket socket) {
		this.socket = socket;
		this.connection = connection;
	}
	
	private String processQuery(String whereClause) {
		synchronized (connection) { // bit wide...
			String result = "[";
			try {
				String effectiveQuery = QUERY_STRING + whereClause;
				System.out.println("|" + effectiveQuery + "|");
				ResultSet rs = query.executeQuery(effectiveQuery);
				boolean first = true;
				while(rs.next()){ 
					Song s = new Song(rs.getString(1).trim(),rs.getString(2).trim(),rs.getString(3).trim());
					if (!first) {
						result += ", ";
					} else {
						first = false;
					}
					result += s.toString();
				}
				result += "]";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	}
	
	private String processInsert(String jsonString) {
		synchronized (connection) { // bit wide..
			try {
				Song newSong = Song.fromString(jsonString);
				String effectiveQuery = INSERT_STRING + 
						"('" + newSong.getTitle() + "','" + newSong.getArtist() 
						+ "','" + newSong.getMood() + "')";
				System.out.println("|" + effectiveQuery + "|");
				int rowsAffected = insert.executeUpdate(effectiveQuery);
				return "{" + rowsAffected + "}";
				} catch (Exception e) {
					e.printStackTrace();
					return "{}";
				}
			}

	}
	
	private String processRequest(String request) {
		// Syntax: 	get # mood = 'angry' --> JSON ARRAY of Songs
		//			insert # JSON Song --> { outcome } 
		String[] parsed = request.split("#");
		switch (parsed[0].trim().toLowerCase()) {
		case "get"  	:	return processQuery(parsed[1].trim());
		case "insert" 	:	return processInsert(parsed[1].trim());
		default:			return "unknown";
		}
	}
	
    public void run() {
        Scanner in = null;
        PrintWriter out = null;
        try {
    		query = connection.createStatement();
        	insert = connection.createStatement();
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            String request;
            // while (((request = in.nextLine()) != null)) {
            while (true) {
            	request = in.nextLine();
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
            	System.out.println("Terminating.");
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
