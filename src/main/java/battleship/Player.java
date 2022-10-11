package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private Battleground battleground;
    private int numOfShips;
    private int numOfMissiles;
    private List<int[]> shipPositions;
    private List<int[]> successfulAttacks = new ArrayList<int[]>();
    private List<int[]> failedAttacks = new ArrayList<int[]>();

    public Player(
            Battleground battleground,
            int numOfShips,
            int numOfMissiles,
            List<int[]> shipPositions) {
        this.battleground = battleground;
        this.numOfShips = numOfShips;
        this.numOfMissiles = numOfMissiles;
        this.shipPositions = shipPositions;

        PlayerPropertyValidator propertyValidator = new PlayerPropertyValidator(
                this.battleground.getBattlegroundSize(),
                this.numOfShips,
                this.numOfMissiles,
                this.shipPositions
        );
        propertyValidator.validatePlayerProperties();
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
        if (shipPositions.stream().anyMatch(shipPos -> Arrays.equals(shipPos, coordinate)))
            return true;
        return false;
    }
}
