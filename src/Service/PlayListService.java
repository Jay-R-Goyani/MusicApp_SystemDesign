package Service;

import Notification.PlaylistObservable;
import Repository.PlayListRepository;
import domain.PlayList;
import domain.Song;
import domain.User;

public class PlayListService {

    private PlayListRepository playListRepository;

    public PlayListService(PlayListRepository playListRepository) {
        this.playListRepository = playListRepository;
    }

    public PlayList createPlaylist(User owner, String name){
        PlayList playList = new PlayList(name, owner);
        playListRepository.save(playList);
        return playList;
    }

    public void addSong(Song song, User user, PlayList playList) {
        if(user!=playList.getOwner()){
            throw new RuntimeException("User is not a Owner of this playlist");
        }
        playList.getSongs().add(song);
        PlaylistObservable playlistObservable = playListRepository.getPlaylistObservable(playList);
        playlistObservable.notify(song ,"ADD");
    }

    public void addFollower(User user, PlayList playList) {
        playList.getFollowers().add(user);
        PlaylistObservable playlistObservable = playListRepository.getPlaylistObservable(playList);
        playlistObservable.addObserver(user);
    }

    public void removeSong(User user, Song song, PlayList playList) {
        if (playList.getOwner().equals(user)) {
            playList.getSongs().remove(song);
        } else {
            throw new RuntimeException("User is not a Owner of this playlist");
        }
        PlaylistObservable playlistObservable = playListRepository.getPlaylistObservable(playList);
        playlistObservable.notify(song,"REMOVE");
    }

    public void removeFollower(User user, PlayList playList) {
        playList.getFollowers().remove(user);
        PlaylistObservable playlistObservable = playListRepository.getPlaylistObservable(playList);
        playlistObservable.removeObserver(user);
    }
}