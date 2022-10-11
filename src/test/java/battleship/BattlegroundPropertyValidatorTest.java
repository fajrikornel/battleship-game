package battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattlegroundPropertyValidatorTest {
    @Test
    public void givenBattlegroundSizeOutOfDefinedRangeThrowError() {
        int battlegroundSizeUpperBoundTest = 10;
        int battlegroundSizeLowerBoundTest = 0;
        int numOfShips = 2;
        int numOfMissiles = 2; //Arbitrary number
        int[][] P1ShipPositionsUpper = generateMockShipPositions(battlegroundSizeUpperBoundTest, numOfShips);
        int[][] P2ShipPositionsUpper = generateMockShipPositions(battlegroundSizeUpperBoundTest, numOfShips);
        int[][] P1ShipPositionsLower = generateMockShipPositions(battlegroundSizeLowerBoundTest, numOfShips);
        int[][] P2ShipPositionsLower = generateMockShipPositions(battlegroundSizeLowerBoundTest, numOfShips);


        Exception exceptionUpperBound = assertThrows(IllegalArgumentException.class, () ->
                new BattlegroundPropertyValidator(battlegroundSizeUpperBoundTest)
                        .validateBattlegroundProperty()
        );
        Exception exceptionLowerBound = assertThrows(IllegalArgumentException.class, () ->
                new BattlegroundPropertyValidator(battlegroundSizeLowerBoundTest)
                        .validateBattlegroundProperty()
        );

        String exceptionUpperBoundMessage = exceptionUpperBound.getMessage();
        String exceptionLowerBoundMessage = exceptionLowerBound.getMessage();

        assertTrue(exceptionUpperBoundMessage.contains("Invalid range for Battleground size: Must be in (0..10)"));
        assertTrue(exceptionLowerBoundMessage.contains("Invalid range for Battleground size: Must be in (0..10)"));
    }

    private int[][] generateMockShipPositions(int battlegroundSize, int numOfShips) {
        if (numOfShips == 0) {
            return new int[][] {};
        } else if (numOfShips < 0) {
            throw new IllegalArgumentException("numOfShips must be a positive integer.");
        }

        int[][] shipPositions = new int[numOfShips][2];

        int shipCounter = 0;
        for (int x = 0; x < battlegroundSize; x++) {
            for (int y = 0; y < battlegroundSize; y++) {
                shipPositions[shipCounter][0] = x;
                shipPositions[shipCounter][1] = y;
                shipCounter++;
                if (shipCounter == numOfShips) {
                    break;
                }
            }
            if (shipCounter == numOfShips) {
                break;
            }
        }

        return shipPositions;
    }
}