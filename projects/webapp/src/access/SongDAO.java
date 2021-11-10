package access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Song;

/**
 *
 * @author Bernd OK
 */
public class SongDAO {
	// A very simple Data Access Object abstraction for DB access via JDBC
	private List<Song> resultList;
	private Statement stmt;
	private Connection conn;
	private ResultSet rs;

	public SongDAO() {
		stmt = null;
		conn = null;
		rs = null;
		final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/Songs?serverTimezone=UTC&user=root&password=mysql4me";

		try {
			resultList = new ArrayList<>();
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DB_URL);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * from Songs");
			while (rs.next()) {
				Song d = new Song(rs.getString(1), rs.getString(2), rs.getString(3));
				resultList.add(d);
			}
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public List<Song> findAll() {
		return findWhere("1=1");
	}

	public List<Song> findWhere(String whereString) {
		String sql = "SELECT * from Songs WHERE (" + whereString + ")";
		resultList.clear();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Song d = new Song(rs.getString(1), rs.getString(2), rs.getString(3));
				resultList.add(d);
			}
		} catch (Exception e) {
			Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return resultList;
	}
}
