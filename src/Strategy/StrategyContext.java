package Strategy;

import domain.PlayList;
import domain.Song;

public interface StrategyContext {
    void setStrategy(Strategy strategy);
    Song executeStrategy();
}