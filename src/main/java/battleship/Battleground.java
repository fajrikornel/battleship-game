package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Battleground {
    private Player player;
    private int battlegroundSize;
    private int numOfShips;
    private List<int[]> shipPositions;
    private List<int[]> successfulAttacks = new ArrayList<int[]>();
    private List<int[]> failedAttacks = new ArrayList<int[]>();

    public Battleground(
            Player player,
            int battlegroundSize,
            int numOfShips,
            List<int[]> shipPositions) {
        this.player = player;
        this.battlegroundSize = battlegroundSize;
        this.numOfShips = numOfShips;
        this.shipPositions = shipPositions;

        BattlegroundPropertyValidator propertyValidator = new BattlegroundPropertyValidator(
                battlegroundSize,
                numOfShips,
                shipPositions);
        propertyValidator.validateBattlegroundProperties();
    }

    protected int getBattlegroundSize() {
        return battlegroundSize;
    }

    public List<int[]> getSuccessfulAttacks() {
        return successfulAttacks;
    }

    public List<int[]> getFailedAttacks() {
        return failedAttacks;
    }

    public void attacked(int[] attackCoordinate) {
        if (isCoordinateInList(attackCoordinate, shipPositions) &&
                !isCoordinateInList(attackCoordinate, successfulAttacks)) {
            successfulAttacks.add(attackCoordinate);
            if (isAllShipsDestroyed())
                updateAliveStatus();
        } else {
            failedAttacks.add(attackCoordinate);
        }
    }

    private boolean isCoordinateInList(int[] coordinate, List<int[]> positions) {
        return positions.stream().anyMatch(pos -> Arrays.equals(pos, coordinate));
    }

    private boolean isAllShipsDestroyed() {
        return Arrays.deepEquals(shipPositions.toArray(), successfulAttacks.toArray());
    }

    protected void updateAliveStatus() {
        this.player.setAliveStatus(!isAllShipsDestroyed());
    }
}
