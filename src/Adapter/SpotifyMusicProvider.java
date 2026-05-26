package Adapter;

import domain.Song;

public class SpotifyMusicProvider implements MusicProviderAdapter {
    private String apiKey;

    public SpotifyMusicProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public MusicProvider getType() {
        return MusicProvider.SPOTIFY;
    }

    @Override
    public Song fetchSong(String songId) {
        System.out.println("[SPOTIFY] GET /tracks/" + songId + " (apiKey=" + apiKey + ")");
        SpotifyTrackDto dto = callSpotifyApi(songId);
        return toSong(dto);
    }

    private SpotifyTrackDto callSpotifyApi(String songId) {
        SpotifyTrackDto dto = new SpotifyTrackDto();
        dto.trackId = songId;
        dto.trackName = "Spotify Song " + songId;
        dto.artistName = "Spotify Artist";
        dto.albumGenre = "Pop";
        dto.durationMs = 215000;
        dto.popularity = 87;
        return dto;
    }

    private Song toSong(SpotifyTrackDto dto) {
        Song song = new Song();
        song.name = dto.trackName;
        song.artist = dto.artistName;
        song.genre = dto.albumGenre;
        song.duration = dto.durationMs / 1000;
        song.likes = dto.popularity;
        song.provider = MusicProvider.SPOTIFY;
        song.externalId = dto.trackId;
        return song;
    }

    private static class SpotifyTrackDto {
        String trackId;
        String trackName;
        String artistName;
        String albumGenre;
        int durationMs;
        int popularity;
    }
}