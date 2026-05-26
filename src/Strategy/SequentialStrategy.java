package Strategy;

import domain.PlayList;
import domain.Song;

public class SequentialStrategy implements Strategy {
    private PlayList playList;
    private int currentIndex;

    public SequentialStrategy(PlayList playList) {
        this(playList, 0);
    }

    public SequentialStrategy(PlayList playList, int currentIndex) {
        this.playList = playList;
        this.currentIndex = currentIndex;
    }

    public void setPlayList(PlayList playList) {
        setPlayList(playList, 0);
    }

    public void setPlayList(PlayList playList, int currentIndex) {
        this.playList = playList;
        this.currentIndex = currentIndex;
    }

    @Override
    public Song execute() {
        if (currentIndex < playList.getSongs().size()) {
            Song song = playList.getSongs().get(currentIndex);
            currentIndex++;
            return song;
        } else {
            System.out.println("No more songs in the playlist");
            return null;
        }
    }
}