package Adapter;

import domain.Song;

public class YoutubeMusicProvider implements MusicProviderAdapter {
    private String apiKey;

    public YoutubeMusicProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public MusicProvider getType() {
        return MusicProvider.YOUTUBE;
    }

    @Override
    public Song fetchSong(String songId) {
        System.out.println("[YOUTUBE] GET /videos/" + songId + " (apiKey=" + apiKey + ")");
        YoutubeVideoDto dto = callYoutubeApi(songId);
        return toSong(dto);
    }

    private YoutubeVideoDto callYoutubeApi(String songId) {
        YoutubeVideoDto dto = new YoutubeVideoDto();
        dto.videoId = songId;
        dto.title = "Youtube Song " + songId;
        dto.channelName = "Youtube Channel";
        dto.category = "Music";
        dto.lengthSeconds = 240;
        dto.viewCount = 12345;
        return dto;
    }

    private Song toSong(YoutubeVideoDto dto) {
        Song song = new Song();
        song.name = dto.title;
        song.artist = dto.channelName;
        song.genre = dto.category;
        song.duration = dto.lengthSeconds;
        song.likes = dto.viewCount;
        song.provider = MusicProvider.YOUTUBE;
        song.externalId = dto.videoId;
        return song;
    }

    private static class YoutubeVideoDto {
        String videoId;
        String title;
        String channelName;
        String category;
        int lengthSeconds;
        int viewCount;
    }
}