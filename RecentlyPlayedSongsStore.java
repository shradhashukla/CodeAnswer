import java.util.*;
class RecentlyPlayedSongsStore {
    
    public static void main(String[] args){
        RecentlyPlayedSongsStore store = new RecentlyPlayedSongsStore(3);
    
    store.addSong("user1", "song1");
    store.addSong("user1", "song2");
    store.addSong("user1", "song3");
    store.addSong("user1", "song4");
    
    store.addSong("user2", "songA");
    store.addSong("user2", "songB");
    
    List<String> user1Songs = store.getRecentlyPlayedSongs("user1");
    List<String> user2Songs = store.getRecentlyPlayedSongs("user2");
    
    System.out.println(user1Songs);
    System.out.println(user2Songs);
    }
    private final int capacity;
    private Map<String, Deque<String>> songMap;

    public RecentlyPlayedSongsStore(int capacity) {
        this.capacity = capacity;
        this.songMap = new HashMap<>();
    }

    public void addSong(String user, String song) {
        Deque<String> userSongs = songMap.get(user);
        if (userSongs == null) {
            userSongs = new LinkedList<>();
            songMap.put(user, userSongs);
        }

        if (userSongs.size() >= capacity) {
            userSongs.pollLast();
        }

        userSongs.offerFirst(song);
    }

    public List<String> getRecentlyPlayedSongs(String user) {
        Deque<String> userSongs = songMap.get(user);
        if (userSongs == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(userSongs);
    }
}
