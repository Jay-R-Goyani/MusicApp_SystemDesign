package Adapter;

import domain.Song;

public interface MusicProviderAdapter {
    MusicProvider getType();
    Song fetchSong(String songId);
}