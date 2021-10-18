
package model;

import java.util.ArrayList;
import java.util.List;
import javax.json.bind.JsonbBuilder;

public class SongList {

    private List<Song> songs;

    public SongList() {
        songs = new ArrayList<>();
    }

    public void add(Song s) {
        songs.add(s);
    }

    public String toString() {
        return JsonbBuilder.create().toJson(songs);
    }
}