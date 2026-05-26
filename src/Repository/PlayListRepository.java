package Repository;

import java.util.HashMap;
import java.util.Map;

import Notification.PlaylistObservable;
import domain.PlayList;
import domain.User;

public class PlayListRepository {
    private final Map<Key, PlayList> playLists = new HashMap<>();
    private Map<PlayList, PlaylistObservable> playlistObservables = new HashMap<>();
    private static PlayListRepository instance = null;

    private PlayListRepository() {
    }

    public static PlayListRepository getInstance() {
        if (instance == null) {
            instance = new PlayListRepository();
        }
        return instance;
    }

    public void save(PlayList playList) {
        playLists.put(new Key(playList.getName(), playList.getOwner()), playList);
        playlistObservables.put(playList, new PlaylistObservable(playList));
    }

    public PlayList findByNameAndOwner(String name, User owner) {
        return playLists.getOrDefault(new Key(name, owner), null);
    }

    public PlaylistObservable getPlaylistObservable(PlayList playList) {
        return playlistObservables.getOrDefault(playList, null);
    }

    private record Key(String name, User owner) {
    }
}