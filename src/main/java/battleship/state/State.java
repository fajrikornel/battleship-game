package battleship.state;

import battleship.player.Player;
import battleship.player.PlayerReport;

import java.util.List;
import java.util.Map;

public interface State {
    Player getCurrentPlayer();
    void attack(List<Integer> attackCoordinate);
    Map<Player, PlayerReport> getPlayerReports();
    String getStateString();
}
