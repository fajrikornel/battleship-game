package battleship;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerPropertyValidator {
    private int battlegroundSize;
    private int numOfShips;
    private int numOfMissiles;
    private List<int[]> shipPositions;

    public PlayerPropertyValidator(
            int battlegroundSize,
            int numOfShips,
            int numOfMissiles,
            List<int[]> shipPositions) {
        this.battlegroundSize = battlegroundSize;
        this.numOfShips = numOfShips;
        this.numOfMissiles = numOfMissiles;
        this.shipPositions = shipPositions;
    }

    public void validatePlayerProperties() {
        validateNumOfShipsProperty();
        validateNumOfMissilesProperty();
        validateShipPositions();
    }

    private void validateNumOfShipsProperty() {
        int lowerBound = 0;
        int upperBound = battlegroundSize*battlegroundSize/2;

        if (numOfShips <= lowerBound || numOfShips > upperBound) {
            throw new IllegalArgumentException("Invalid range for numOfShips: Must be in (0..battlegroundSize**2/2]");
        }
    }

    private void validateNumOfMissilesProperty() {
        int lowerBound = 0;
        int upperBound = 100;

        if (numOfMissiles <= lowerBound || numOfMissiles >= upperBound) {
            throw new IllegalArgumentException("Invalid range for numOfMissiles: Must be in (0..100)");
        }
    }

    private void validateShipPositions() {
        validateShipPositionsToNumOfShips(shipPositions);
        validateShipPositionsInsideBattleground(shipPositions);
    }

    private void validateShipPositionsToNumOfShips(List<int[]> shipPositions) {
        Set<int[]> shipPositionsSet = new HashSet<>(shipPositions);
        if (shipPositionsSet.size() != numOfShips) {
            throw new IllegalArgumentException("P1 and P2 shipPositions must align with numOfShips and no duplicate ships.");
        }
    }

    private void validateShipPositionsInsideBattleground(List<int[]> shipPositions) {
        for (int i = 0; i < numOfShips; i++) {
            validateCoordinateIsInsideBattleground(shipPositions.get(i));
        }
    }

    private void validateCoordinateIsInsideBattleground(int[] coordinate) {
        if (coordinate[0] >= battlegroundSize || coordinate[0] < 0 ||
                coordinate[1] >= battlegroundSize || coordinate[1] < 0) {
            throw new IllegalArgumentException("All ships must be inside the battlefield.");
        }
    }
}
