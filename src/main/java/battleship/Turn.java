package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Turn implements BattleshipState {
    private List<Player> playerList;
    private int currentPlayerIndex;
    private Player currentPlayer;
    private Player targetPlayer;
    private BattleshipContext battleshipContext;

    public Turn(List<Player> playerList,
                int currentPlayerIndex,
                BattleshipContext battleshipContext) {
        this.playerList = new ArrayList<>(playerList);
        this.currentPlayerIndex = currentPlayerIndex;
        this.currentPlayer = playerList.get(currentPlayerIndex);
        this.targetPlayer = playerList.get(getTargetPlayerIndex());
        this.battleshipContext = battleshipContext;
    }

    private int getTargetPlayerIndex() {
        int nextPlayerIndex = currentPlayerIndex + 1;
        if (nextPlayerIndex >= playerList.size())
            nextPlayerIndex = 0;
        return nextPlayerIndex;
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
        int nextTurnPlayerIndex = getTargetPlayerIndex();
        if (isCurrentPlayerOutOfMissiles()) {
            playerList.remove(currentPlayerIndex);
            if (playerList.size() == 0) {
                GameOver gameOver = new GameOver(battleshipContext);
                battleshipContext.setState(gameOver);
                return;
            }
            nextTurnPlayerIndex = currentPlayerIndex;
        }

        Turn nextTurn = new Turn(playerList, nextTurnPlayerIndex, battleshipContext);
        battleshipContext.setState(nextTurn);
    }

    private boolean isCurrentPlayerOutOfMissiles() {
        return currentPlayer.getNumOfMissilesAvailable() == 0;
    }

    public Map<Player, PlayerReport> getPlayerReports() {
        throw new IllegalStateException("Turn state does not have getPlayerReports() method");
    }
}
