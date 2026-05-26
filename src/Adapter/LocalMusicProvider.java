package Adapter;

import domain.Song;

public class LocalMusicProvider implements MusicProviderAdapter {
    private String musicDirectory;

    public LocalMusicProvider(String musicDirectory) {
        this.musicDirectory = musicDirectory;
    }

    @Override
    public MusicProvider getType() {
        return MusicProvider.LOCAL;
    }

    @Override
    public Song fetchSong(String songId) {
        System.out.println("[LOCAL] Reading file " + musicDirectory + "/" + songId + ".mp3");
        LocalFileMetadata meta = readMetadata(songId);
        return toSong(meta);
    }

    private LocalFileMetadata readMetadata(String songId) {
        LocalFileMetadata meta = new LocalFileMetadata();
        meta.externalId = songId;
        meta.fileName = songId + ".mp3";
        meta.tagTitle = "Local Song " + songId;
        meta.tagArtist = "Local Artist";
        meta.tagGenre = "Rock";
        meta.seconds = 180;
        return meta;
    }

    private Song toSong(LocalFileMetadata meta) {
        Song song = new Song();
        song.name = meta.tagTitle;
        song.artist = meta.tagArtist;
        song.genre = meta.tagGenre;
        song.duration = meta.seconds;
        song.likes = 0;
        song.provider = MusicProvider.LOCAL;
        song.externalId = meta.externalId;
        return song;
    }

    private static class LocalFileMetadata {
        String externalId;
        String fileName;
        String tagTitle;
        String tagArtist;
        String tagGenre;
        int seconds;
    }
}