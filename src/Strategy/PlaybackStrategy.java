package Strategy;

import domain.PlayList;
import domain.Song;

public class PlaybackStrategy implements StrategyContext {
    private Strategy strategy;

    public PlaybackStrategy() {
    }

    @Override
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public Song executeStrategy() {
        if (strategy == null) {
            throw new IllegalStateException("No strategy set");
        }
        return strategy.execute();
    }
}