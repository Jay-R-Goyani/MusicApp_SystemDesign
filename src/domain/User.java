package domain;

import Notification.Observable;
import Notification.Observer;
import Notification.PlaylistObservable;

public class User implements Observer {
    public static int id = 0;
    public String name;
    public String email;
    public String password;

    public User(String name, String email, String password) {
        this.id = ++id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public void update(Observable observable) {
        PlaylistObservable po = (PlaylistObservable) observable;
        System.out.println("User " + name + " received notification : "
                + po.getSong().name + " by " + po.getSong().artist
                + " - " + po.getAction());
    }
}