package battleship.state;

import battleship.player.Player;
import battleship.player.PlayerReport;

import java.util.List;
import java.util.Map;

public interface Context {
    void setState(State state);
    State getState();
    List<Player> getAllPlayers();
    Player getCurrentPlayer();
    void attack(List<Integer> coordinate);
    Map<Player, PlayerReport> getPlayerReports();
    String getStateString();
    List<String> getBattlegroundResults();
}
