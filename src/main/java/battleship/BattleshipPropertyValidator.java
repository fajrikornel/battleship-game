package battleship;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BattleshipPropertyValidator {
    private int battlegroundSize;
    private int numOfShips;
    private int numOfMissiles;
    private int[][] P1ShipPositions;
    private int[][] P2ShipPositions;

    public BattleshipPropertyValidator(int battlegroundSize,
                                       int numOfShips,
                                       int numOfMissiles,
                                       int[][] P1ShipPositions,
                                       int[][] P2ShipPositions) {
        this.battlegroundSize = battlegroundSize;
        this.numOfShips = numOfShips;
        this.numOfMissiles = numOfMissiles;
        this.P1ShipPositions = P1ShipPositions;
        this.P2ShipPositions = P2ShipPositions;
    }

    public void validateBattleshipProperties() {
        validateBattlegroundSizeProperty();
        validateNumOfShipsProperty();
        validateNumOfMissilesProperty();
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

    private void validateNumOfMissilesProperty() {
        int lowerBound = 0;
        int upperBound = 100;

        if (numOfMissiles <= lowerBound || numOfMissiles >= upperBound) {
            throw new IllegalArgumentException("Invalid range for numOfMissiles: Must be in (0..100)");
        }
    }

    private void validateShipPositions() {
        validatePlayerShipPositions(P1ShipPositions);
        validatePlayerShipPositions(P2ShipPositions);
    }

    private void validatePlayerShipPositions(int[][] shipPositions) {
        validateShipPositionsToNumOfShips(shipPositions);
        validateShipPositionsInsideBattleground(shipPositions);
    }

    private void validateShipPositionsToNumOfShips(int[][] shipPositions) {
        Set<int[]> shipPositionsSet = new HashSet<>(Arrays.asList(shipPositions));
        if (shipPositionsSet.size() != numOfShips) {
            throw new IllegalArgumentException("P1 and P2 shipPositions must align with numOfShips and no duplicate ships.");
        }
    }

    private void validateShipPositionsInsideBattleground(int[][] shipPositions) {
        for (int i = 0; i < numOfShips; i++) {
            validateCoordinateIsInsideBattleground(shipPositions[i]);
        }
    }

    private void validateCoordinateIsInsideBattleground(int[] coordinate) {
        if (coordinate[0] >= battlegroundSize || coordinate[0] < 0 ||
                coordinate[1] >= battlegroundSize || coordinate[1] < 0) {
            throw new IllegalArgumentException("All ships must be inside the battlefield.");
        }
    }
}
