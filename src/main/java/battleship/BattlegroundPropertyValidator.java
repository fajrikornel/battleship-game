package battleship;

import java.util.Set;

public class BattlegroundPropertyValidator {
    private int battlegroundSize;
    private int numOfShips;
    private Set<int[]> shipPositions;

    public BattlegroundPropertyValidator(
            int battlegroundSize,
            int numOfShips,
            Set<int[]> shipPositions) {
        this.battlegroundSize = battlegroundSize;
        this.numOfShips = numOfShips;
        this.shipPositions = shipPositions;
    }

    public void validateBattlegroundProperties() {
        validateBattlegroundSizeProperty();
        validateNumOfShipsProperty();
        validateShipPositions();
    }

    private void validateBattlegroundSizeProperty() {
        int lowerBound = 0;
        int upperBound = 10;

        if (battlegroundSize <= lowerBound || battlegroundSize >= upperBound) {
            throw new IllegalArgumentException("Invalid range for Battleground size: Must be in (0..10)");
        }
    }

    private void validateNumOfShipsProperty() {
        int lowerBound = 0;
        int upperBound = battlegroundSize*battlegroundSize/2;

        if (numOfShips <= lowerBound || numOfShips > upperBound) {
            throw new IllegalArgumentException("Invalid range for numOfShips: Must be in (0..battlegroundSize**2/2]");
        }
    }

    private void validateShipPositions() {
        validateShipPositionsToNumOfShips();
        validateShipPositionsInsideBattleground();
    }

    private void validateShipPositionsToNumOfShips() {
        if (shipPositions.size() != numOfShips) {
            throw new IllegalArgumentException("shipPositions must align with numOfShips and no duplicate ships.");
        }
    }

    private void validateShipPositionsInsideBattleground() {
        shipPositions.forEach(this::validateCoordinateIsInsideBattleground);
    }

    private void validateCoordinateIsInsideBattleground(int[] coordinate) {
        if (coordinate[0] >= battlegroundSize || coordinate[0] < 0 ||
                coordinate[1] >= battlegroundSize || coordinate[1] < 0) {
            throw new IllegalArgumentException("All ships must be inside the battlefield.");
        }
    }
}
