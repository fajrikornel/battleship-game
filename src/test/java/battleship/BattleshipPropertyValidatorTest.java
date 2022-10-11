package battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleshipPropertyValidatorTest {
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
                new BattleshipPropertyValidator(battlegroundSizeUpperBoundTest, numOfShips, numOfMissiles, P1ShipPositionsUpper, P2ShipPositionsUpper)
                        .validateBattleshipProperties()
        );
        Exception exceptionLowerBound = assertThrows(IllegalArgumentException.class, () ->
                new BattleshipPropertyValidator(battlegroundSizeLowerBoundTest, numOfShips, numOfMissiles, P1ShipPositionsLower, P2ShipPositionsLower)
                        .validateBattleshipProperties()
        );

        String exceptionUpperBoundMessage = exceptionUpperBound.getMessage();
        String exceptionLowerBoundMessage = exceptionLowerBound.getMessage();

        assertTrue(exceptionUpperBoundMessage.contains("Invalid range for Battleground size: Must be in (0..10)"));
        assertTrue(exceptionLowerBoundMessage.contains("Invalid range for Battleground size: Must be in (0..10)"));
    }

    @Test
    public void givenNumOfShipsOutOfDefinedRangeThrowError() {
        int battlegroundSize = 9; //Arbitrary number
        int numOfShipsUpperBoundTest = battlegroundSize*battlegroundSize/2 + 1;
        int numOfShipsLowerBoundTest = 0;
        int numOfMissiles = 2; //Arbitrary number
        int[][] P1ShipPositionsUpper = generateMockShipPositions(battlegroundSize, numOfShipsUpperBoundTest);
        int[][] P2ShipPositionsUpper = generateMockShipPositions(battlegroundSize, numOfShipsUpperBoundTest);
        int[][] P1ShipPositionsLower = generateMockShipPositions(battlegroundSize, numOfShipsLowerBoundTest);
        int[][] P2ShipPositionsLower = generateMockShipPositions(battlegroundSize, numOfShipsLowerBoundTest);

        Exception exceptionUpperBound = assertThrows(IllegalArgumentException.class, () ->
                new BattleshipPropertyValidator(battlegroundSize, numOfShipsUpperBoundTest, numOfMissiles, P1ShipPositionsUpper, P2ShipPositionsUpper)
                        .validateBattleshipProperties()
        );
        Exception exceptionLowerBound = assertThrows(IllegalArgumentException.class, () ->
                new BattleshipPropertyValidator(battlegroundSize, numOfShipsLowerBoundTest, numOfMissiles, P1ShipPositionsLower, P2ShipPositionsLower)
                        .validateBattleshipProperties()
        );

        String exceptionUpperBoundMessage = exceptionUpperBound.getMessage();
        String exceptionLowerBoundMessage = exceptionLowerBound.getMessage();

        assertTrue(exceptionUpperBoundMessage.contains("Invalid range for numOfShips: Must be in (0..battlegroundSize**2/2]"));
        assertTrue(exceptionLowerBoundMessage.contains("Invalid range for numOfShips: Must be in (0..battlegroundSize**2/2]"));
    }

    @Test
    public void givenNumOfMissilesOutOfDefinedRangeThrowError() {
        int battlegroundSize = 9; //Arbitrary number
        int numOfShips = 2; //Arbitrary number
        int numOfMissilesUpperBoundTest = 100;
        int numOfMissilesLowerBoundTest = 0;
        int[][] P1ShipPositions = generateMockShipPositions(battlegroundSize, numOfShips);
        int[][] P2ShipPositions = generateMockShipPositions(battlegroundSize, numOfShips);

        Exception exceptionUpperBound = assertThrows(IllegalArgumentException.class, () ->
                new BattleshipPropertyValidator(battlegroundSize, numOfShips, numOfMissilesUpperBoundTest, P1ShipPositions, P2ShipPositions)
                        .validateBattleshipProperties()
        );
        Exception exceptionLowerBound = assertThrows(IllegalArgumentException.class, () ->
                new BattleshipPropertyValidator(battlegroundSize, numOfShips, numOfMissilesLowerBoundTest, P1ShipPositions, P2ShipPositions)
                        .validateBattleshipProperties()
        );

        String exceptionUpperBoundMessage = exceptionUpperBound.getMessage();
        String exceptionLowerBoundMessage = exceptionLowerBound.getMessage();

        assertTrue(exceptionUpperBoundMessage.contains("Invalid range for numOfMissiles: Must be in (0..100)"));
        assertTrue(exceptionLowerBoundMessage.contains("Invalid range for numOfMissiles: Must be in (0..100)"));
    }

    @Test
    public void givenShipPositionsNotMatchingNumberOfShipsThrowError() {
        int battlegroundSize = 9; //Arbitrary number
        int numOfP1AndP2ShipsCorrect = 2; //Arbitrary number
        int numOfP1ShipsWrong = 3;
        int numOfP2ShipsWrong = 1;
        int numOfMissiles = 10;
        int[][] P1ShipPositionsCorrect = generateMockShipPositions(battlegroundSize, numOfP1AndP2ShipsCorrect);
        int[][] P1ShipPositionsWrong = generateMockShipPositions(battlegroundSize, numOfP1ShipsWrong);
        int[][] P2ShipPositionsCorrect = generateMockShipPositions(battlegroundSize, numOfP1AndP2ShipsCorrect);
        int[][] P2ShipPositionsWrong = generateMockShipPositions(battlegroundSize, numOfP2ShipsWrong);

        Exception exceptionNumOfShipsMore = assertThrows(IllegalArgumentException.class, () ->
                new BattleshipPropertyValidator(battlegroundSize, numOfP1AndP2ShipsCorrect, numOfMissiles, P1ShipPositionsCorrect, P2ShipPositionsWrong)
                        .validateBattleshipProperties()
        );
        Exception exceptionNumOfShipsLess = assertThrows(IllegalArgumentException.class, () ->
                new BattleshipPropertyValidator(battlegroundSize, numOfP1AndP2ShipsCorrect, numOfMissiles, P1ShipPositionsWrong, P2ShipPositionsCorrect)
                        .validateBattleshipProperties()
        );

        String exceptionNumOfShipsMoreMessage = exceptionNumOfShipsMore.getMessage();
        String exceptionNumOfShipsLessMessage = exceptionNumOfShipsLess.getMessage();

        assertTrue(exceptionNumOfShipsMoreMessage.contains("P1 and P2 shipPositions must align with numOfShips and no duplicate ships."));
        assertTrue(exceptionNumOfShipsLessMessage.contains("P1 and P2 shipPositions must align with numOfShips and no duplicate ships."));
    }

    @Test
    public void givenCoordinatesOutOfBattlefieldThrowError() {
        int battlegroundSize = 9; //Arbitrary number
        int numOfShips = 2; //Arbitrary number
        int numOfMissiles = 2;
        int[][] P1ShipPositionsCorrect = generateMockShipPositions(battlegroundSize, numOfShips);
        int[][] P1ShipPositionsWrong = generateMockShipPositions(battlegroundSize, numOfShips);
        P1ShipPositionsWrong[0][0] = -1;
        int[][] P2ShipPositionsCorrect = generateMockShipPositions(battlegroundSize, numOfShips);
        int[][] P2ShipPositionsWrong = generateMockShipPositions(battlegroundSize, numOfShips);
        P2ShipPositionsWrong[0][0] = battlegroundSize;

        Exception exceptionCoordinateLessThanZero = assertThrows(IllegalArgumentException.class, () ->
                new BattleshipPropertyValidator(battlegroundSize, numOfShips, numOfMissiles, P1ShipPositionsCorrect, P2ShipPositionsWrong)
                        .validateBattleshipProperties()
        );
        Exception exceptionCoordinateMoreThanBattleground = assertThrows(IllegalArgumentException.class, () ->
                new BattleshipPropertyValidator(battlegroundSize, numOfShips, numOfMissiles, P1ShipPositionsWrong, P2ShipPositionsCorrect)
                        .validateBattleshipProperties()
        );

        String exceptionCoordinateLessThanZeroMessage = exceptionCoordinateLessThanZero.getMessage();
        String exceptionCoordinateMoreThanBattlegroundMessage = exceptionCoordinateMoreThanBattleground.getMessage();

        assertTrue(exceptionCoordinateLessThanZeroMessage.contains("All ships must be inside the battlefield."));
        assertTrue(exceptionCoordinateMoreThanBattlegroundMessage.contains("All ships must be inside the battlefield."));
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