package battleship;

import java.util.List;
import java.util.Map;

public class Turn implements BattleshipState {
    private List<Player> playerList;
    private int currentPlayerIndex;
    private Player currentPlayer;
    private Player targetPlayer;
    private BattleshipContext battleshipContext;

    public Turn(int currentPlayerIndex, BattleshipContext battleshipContext) {
        this.currentPlayerIndex = currentPlayerIndex;
        this.battleshipContext = battleshipContext;
        this.playerList = battleshipContext.getAllPlayers();
        this.currentPlayer = playerList.get(currentPlayerIndex);
        this.targetPlayer = playerList.get(getTargetPlayerIndex());

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
        BattleshipState nextState;
        if (isAllPlayersOutOfMissiles()) {
            nextState = new GameOver(battleshipContext);
        } else {
            int nextTurnPlayerIndex = getTargetPlayerIndex();
            nextState = new Turn(nextTurnPlayerIndex, battleshipContext);
        }
        battleshipContext.setState(nextState);
    }

    private boolean isAllPlayersOutOfMissiles() {
        return playerList.stream().allMatch(player -> player.getNumOfMissilesAvailable() == 0);
    }

    public Map<Player, PlayerReport> getPlayerReports() {
        throw new IllegalStateException("Turn state does not have getPlayerReports() method");
    }
}
