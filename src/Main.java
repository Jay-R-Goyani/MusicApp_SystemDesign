import Adapter.LocalMusicProvider;
import Adapter.MusicProvider;
import Adapter.ProviderRegistry;
import Adapter.SpotifyMusicProvider;
import Adapter.YoutubeMusicProvider;
import Strategy.PlayMethods;
import domain.PlayList;

public class Main {
    public static void main(String[] args) {
        ProviderRegistry registry = ProviderRegistry.getInstance();
        registry.register(new LocalMusicProvider("/Users/jay/Music"));
        registry.register(new SpotifyMusicProvider("spotify-api-key-123"));
        registry.register(new YoutubeMusicProvider("youtube-api-key-456"));

        MusicFacade music = new MusicFacade();

        music.register("Jay", "jay@example.com", "secret");
        music.login("jay@example.com", "secret");

        PlayList mix = music.createPlaylist("Mix");
        music.addSong(mix, MusicProvider.SPOTIFY, "track-001");
        music.addSong(mix, MusicProvider.YOUTUBE, "video-001");
        music.addSong(mix, MusicProvider.LOCAL, "file-001");

        music.play(mix, PlayMethods.SEQUENTIAL);
        music.next();
        music.next();
    }
}