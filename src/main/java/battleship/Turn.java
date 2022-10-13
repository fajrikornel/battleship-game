package battleship;

import java.util.List;
import java.util.Map;

public class Turn implements State {
    private List<Player> playerList;
    private int currentPlayerIndex;
    private Player currentPlayer;
    private Player targetPlayer;
    private Context context;
    private String stateString;

    public Turn(int currentPlayerIndex, Context context) {
        this.currentPlayerIndex = currentPlayerIndex;
        this.context = context;
        this.playerList = context.getAllPlayers();
        this.currentPlayer = playerList.get(currentPlayerIndex);
        this.targetPlayer = playerList.get(getTargetPlayerIndex());
        this.stateString = "P" + (currentPlayerIndex + 1) + "'S TURN";

        if (isCurrentPlayerOutOfMissiles()) {
            switchState();
        }
    }

    private int getTargetPlayerIndex() {
        int nextPlayerIndex = currentPlayerIndex + 1;
        if (nextPlayerIndex >= playerList.size())
            nextPlayerIndex = 0;
        return nextPlayerIndex;
    }

    private boolean isCurrentPlayerOutOfMissiles() {
        return currentPlayer.getNumOfMissilesAvailable() == 0;
    }

    protected List<Player> getPlayerList() {
        return playerList;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void attack(List<Integer> attackCoordinate) {
        currentPlayer.attack(targetPlayer, attackCoordinate);
        switchState();
    }

    private void switchState() {
        State nextState;
        if (isAllPlayersOutOfMissiles()) {
            nextState = new GameOver(context);
        } else {
            int nextTurnPlayerIndex = getTargetPlayerIndex();
            nextState = new Turn(nextTurnPlayerIndex, context);
        }
        context.setState(nextState);
    }

    private boolean isAllPlayersOutOfMissiles() {
        return playerList.stream().allMatch(player -> player.getNumOfMissilesAvailable() == 0);
    }

    public Map<Player, PlayerReport> getPlayerReports() {
        throw new IllegalStateException("Turn state does not have getPlayerReports() method");
    }
    public String getStateString() {
        return stateString;
    }
}
