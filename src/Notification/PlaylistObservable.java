package Notification;

import domain.PlayList;
import domain.Song;
import domain.User;

import java.util.ArrayList;
import java.util.List;

public class PlaylistObservable implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private PlayList playList;
    private Song song;
    private User user;
    private String action;

    public PlaylistObservable(PlayList playList) {
        this.playList = playList;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void notify(Song song, String action) {
        this.song = song;
        this.action = action;
        notifyObservers();
    }

    public Song getSong() {
        return song;
    }

    public String getAction() {
        return action;
    }
}