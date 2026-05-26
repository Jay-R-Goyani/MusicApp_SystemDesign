package domain;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
    private String name;
    private List<Song> songs;
    private List<User> followers;
    private User owner;

    public PlayList(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.songs = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public List<User> getFollowers() {
        return followers;
    }
}