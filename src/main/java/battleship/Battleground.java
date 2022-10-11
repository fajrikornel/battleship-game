package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Battleground {
    private int battlegroundSize;
    private int numOfShips;
    private List<int[]> shipPositions;
    private List<int[]> successfulAttacks = new ArrayList<int[]>();
    private List<int[]> failedAttacks = new ArrayList<int[]>();

    public Battleground(
            int battlegroundSize,
            int numOfShips,
            List<int[]> shipPositions) {
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
        if (isShipCoordinate(attackCoordinate)) {
            successfulAttacks.add(attackCoordinate);
        } else {
            failedAttacks.add(attackCoordinate);
        }
    }

    private boolean isShipCoordinate(int[] coordinate) {
        return shipPositions.stream().anyMatch(shipPos -> Arrays.equals(shipPos, coordinate));
    }
}
