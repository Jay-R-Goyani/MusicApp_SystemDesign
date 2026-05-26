package domain;

import Adapter.MusicProvider;

public class Song {
    public String artist;
    public String name;
    public String genre;
    public int likes;
    public int duration;
    public MusicProvider provider;
    public String externalId;
}