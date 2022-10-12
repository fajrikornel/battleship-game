package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BattleshipContextImpl implements BattleshipContext {
    private BattleshipState state;
    private List<Player> allPlayers = new ArrayList<>();

    public BattleshipContextImpl(Player... players) {
        for (Player player: players)
            allPlayers.add(player);

        int firstPlayerTurn = 0;
        this.state = new Turn(firstPlayerTurn, this);
    }

    public void setState(BattleshipState state) {
        this.state = state;
    }

    public BattleshipState getState() {
        return state;
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    public Player getCurrentPlayer() {
        return state.getCurrentPlayer();
    }

    public void attack(List<Integer> coordinate) {
        state.attack(coordinate);
    }

    public Map<Player, PlayerReport> getPlayerReports() {
        return state.getPlayerReports();
    }
}
