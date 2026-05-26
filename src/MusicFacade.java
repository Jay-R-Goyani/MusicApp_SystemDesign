import Adapter.MusicProvider;
import Adapter.ProviderRegistry;
import Repository.PlayListRepository;
import Service.AuthService;
import Service.PlayListService;
import Strategy.PlayMethods;
import Strategy.PlaybackStrategy;
import domain.PlayList;
import domain.Song;
import domain.User;

public class MusicFacade {
    private final AuthService authService;
    private final PlayListService playListService;
    private final PlayListRepository playListRepository;
    private final ProviderRegistry providerRegistry;
    private final MusicPlayer player;
    private final PlaybackStrategy playbackStrategy;
    private User currentUser;

    public MusicFacade() {
        this.authService = new AuthService();
        this.playListRepository = PlayListRepository.getInstance();
        this.playListService = new PlayListService(playListRepository);
        this.providerRegistry = ProviderRegistry.getInstance();
        this.playbackStrategy = new PlaybackStrategy();
        this.player = new MusicPlayer(providerRegistry);
        this.player.setPlaybackStrategy(playbackStrategy);
    }

    public void register(String name, String email, String password) {
        authService.register(name, email, password);
    }

    public User login(String email, String password) {
        this.currentUser = authService.login(email, password);
        return currentUser;
    }

    public PlayList createPlaylist(String name) {
        requireLoggedIn();
        return playListService.createPlaylist(currentUser, name);
    }

    public void addSong(PlayList pl, MusicProvider provider, String songId) {
        requireLoggedIn();
        Song song = providerRegistry.get(provider).fetchSong(songId);
        playListService.addSong(song, currentUser, pl);
    }

    public void removeSong(PlayList pl, Song song) {
        requireLoggedIn();
        playListService.removeSong(currentUser, song, pl);
    }

    public void followPlaylist(PlayList pl) {
        requireLoggedIn();
        playListService.addFollower(currentUser, pl);
    }

    public void play(PlayList pl, PlayMethods method) {
        player.setStrategy(method, pl);
        player.playNext();
    }

    public void next() {
        player.playNext();
    }

    public void pause() {
        player.pause();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    private void requireLoggedIn() {
        if (currentUser == null) {
            throw new IllegalStateException("No user logged in. Call login() first.");
        }
    }
}