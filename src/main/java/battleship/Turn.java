package battleship;

import java.util.List;

public class Turn implements BattleshipState {
    private List<Player> playerList;
    private int currentPlayerIndex;
    private Player currentPlayer;
    private Player targetPlayer;
    private BattleshipContext battleshipContext;

    public Turn(List<Player> playerList,
                int currentPlayerIndex,
                BattleshipContext battleshipContext) {
        this.playerList = playerList;
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void attack(int[] attackCoordinate) {
        currentPlayer.attack(targetPlayer, attackCoordinate);
        switchState();
    }

    private void switchState() {
        int nextTurnPlayerIndex = getTargetPlayerIndex();
        if (isCurrentPlayerOutOfMissiles()) {
            playerList.remove(currentPlayerIndex);
            nextTurnPlayerIndex = currentPlayerIndex;
        }

        Turn nextTurn = new Turn(playerList, nextTurnPlayerIndex, battleshipContext);
        battleshipContext.setState(nextTurn);
    }

    private boolean isCurrentPlayerOutOfMissiles() {
        return currentPlayer.getNumOfMissiles() == 0;
    }
}
