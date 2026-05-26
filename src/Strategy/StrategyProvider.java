package Strategy;

import java.util.List;

import domain.PlayList;
import domain.Song;

public class StrategyProvider {

    public static Strategy getStrategy(PlayMethods method, PlayList playList) {
        return getStrategy(method, playList, 0);
    }

    public static Strategy getStrategy(PlayMethods method, PlayList playList, int currentIndex) {
        return switch (method) {
            case SEQUENTIAL -> new SequentialStrategy(playList, currentIndex);
            case SHUFFLE -> new ShuffleStrategy(playList, currentIndex);
            case CUSTOM -> throw new IllegalArgumentException(
                "Use getStrategy(playList, customOrder) for CUSTOM");
        };
    }
    public static Strategy getStrategy(PlayList playList, List<Song> customOrder) {
        return new CustomStrategy(playList , customOrder);
    }
}