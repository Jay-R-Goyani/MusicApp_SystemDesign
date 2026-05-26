import Adapter.MusicProvider;
import Adapter.ProviderRegistry;
import Strategy.PlayMethods;
import Strategy.PlaybackStrategy;
import Strategy.StrategyProvider;
import domain.PlayList;
import domain.Song;

public class MusicPlayer {
    private Song song;
    private PlaybackStrategy playbackStrategy;
    private ProviderRegistry providerRegistry;

    public MusicPlayer() {
        this(ProviderRegistry.getInstance());
    }

    public MusicPlayer(ProviderRegistry providerRegistry) {
        this.providerRegistry = providerRegistry;
    }

    void load(MusicProvider provider, String songId) {
        this.song = providerRegistry.get(provider).fetchSong(songId);
    }

    void setPlaybackStrategy(PlaybackStrategy playbackStrategy) {
        this.playbackStrategy = playbackStrategy;
    }

    void setStrategy(PlayMethods method, PlayList playList) {
        playbackStrategy.setStrategy(StrategyProvider.getStrategy(method, playList));
    }

    void play() {
        if (song == null) {
            System.out.println("No song loaded");
            return;
        }
        System.out.println("Playing: " + song.name + " by " + song.artist);
    }

    void pause() {
        if (song == null) {
            System.out.println("Nothing to pause");
            return;
        }
        System.out.println("Paused: " + song.name + " by " + song.artist);
    }

    void playNext() {
        Song next = playbackStrategy.executeStrategy();
        if (next != null) {
            load(next.provider, next.externalId);
            play();
        }
    }
}