package battleship;

import java.util.ArrayList;
import java.util.List;

public class BattleshipContextImpl implements BattleshipContext {
    private BattleshipState state;
    private List<Player> allPlayers = new ArrayList<>();

    public BattleshipContextImpl(Player... players) {
        for (Player player: players)
            allPlayers.add(player);

        int firstPlayerTurn = 0;
        this.state = new Turn(allPlayers, firstPlayerTurn, this);
    }

    public void setState(BattleshipState state) {
        this.state = state;
    }

    public BattleshipState getState() {
        return state;
    }

    public Player getCurrentPlayer() {
        return state.getCurrentPlayer();
    }

    public void attack(int[] coordinate) {
        state.attack(coordinate);
    }

}
