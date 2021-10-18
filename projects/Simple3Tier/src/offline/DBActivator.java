package offline;

import java.util.List;

import dbserver.DBServer;
import model.Song;

public class DBActivator {
	public static void main(String[] args) {
		DBServer dbs = new DBServer(true);
		List<Song> songs = dbs.queryArtist("Johnny Cash");
		System.out.println(songs);
	}
}
