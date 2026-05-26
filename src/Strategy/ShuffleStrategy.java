package Strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.PlayList;
import domain.Song;

public class ShuffleStrategy implements Strategy {
    private PlayList playList;
    private List<Song> shuffled;
    private int currentIndex;

    public ShuffleStrategy(PlayList playList) {
        this(playList, 0);
    }

    public ShuffleStrategy(PlayList playList, int currentIndex) {
        this.playList = playList;
        this.shuffled = new ArrayList<>(playList.getSongs());
        Collections.shuffle(this.shuffled);
        this.currentIndex = currentIndex;
    }

    @Override
    public Song execute() {
            Song song = shuffled.get(currentIndex);
            currentIndex = (int) (Math.random() * shuffled.size());
            return song;
    }
}