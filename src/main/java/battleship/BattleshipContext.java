package battleship;

import java.util.List;
import java.util.Map;

public interface BattleshipContext {
    void setState(BattleshipState state);
    BattleshipState getState();
    List<Player> getAllPlayers();
    Player getCurrentPlayer();
    void attack(List<Integer> coordinate);
    Map<Player, PlayerReport> getPlayerReports();
}
