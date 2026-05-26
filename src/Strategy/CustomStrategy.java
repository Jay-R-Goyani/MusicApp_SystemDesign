package Strategy;

import java.util.List;

import domain.PlayList;
import domain.Song;

public class CustomStrategy implements Strategy {
    private PlayList playList;
    private List<Song> customOrder;
    private int currentIndex;
    private int playListIndex;
    private boolean inCustomOrder;

    public CustomStrategy(PlayList playList, List<Song> customOrder) {
        this.playList = playList;
        this.customOrder = customOrder;
        this.currentIndex = 0;
        this.playListIndex = 0;
        this.inCustomOrder = true;
    }

    @Override
    public Song execute() {
        if (inCustomOrder) {
            if (currentIndex < customOrder.size()) {
                Song song = customOrder.get(currentIndex);
                currentIndex++;
                if (currentIndex == customOrder.size()) {
                    playListIndex = playList.getSongs().indexOf(song) + 1;
                    inCustomOrder = false;
                }
                return song;
            }
            inCustomOrder = false;
        }
        if (playListIndex < playList.getSongs().size()) {
            Song song = playList.getSongs().get(playListIndex);
            playListIndex++;
            return song;
        }
        System.out.println("No more songs in the playlist");
        return null;
    }
}