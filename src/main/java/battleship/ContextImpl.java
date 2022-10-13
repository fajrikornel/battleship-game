package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContextImpl implements Context {
    private State state;
    private List<Player> allPlayers = new ArrayList<>();

    public ContextImpl(Player... players) {
        for (Player player: players)
            allPlayers.add(player);

        int firstPlayerTurn = 0;
        this.state = new Turn(firstPlayerTurn, this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
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

    public String getStateString() {
        return state.getStateString();
    }

    public List<String> getBattlegroundResults() {
        List<String> battlegroundResults = new ArrayList<>();
        for (Player player: allPlayers) {
            Battleground battleground = player.getBattleground();
            String battlegroundResult = BattlegroundResultParser.getBattlegroundReport(battleground);
            battlegroundResults.add(battlegroundResult);
        }
        return battlegroundResults;
    }
}
