package battleship;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BattlegroundPropertyValidatorTest {
    @Test
    public void givenNumOfShipsOutOfDefinedRangeThrowError() {
        int battlegroundSize = 9; //Arbitrary number
        int numOfShipsUpperBoundTest = battlegroundSize*battlegroundSize/2 + 1;
        int numOfShipsLowerBoundTest = 0;
        Set<int[]> P1ShipPositionsUpper = generateMockShipPositions(battlegroundSize, numOfShipsUpperBoundTest);
        Set<int[]> P1ShipPositionsLower = generateMockShipPositions(battlegroundSize, numOfShipsLowerBoundTest);

        Exception exceptionUpperBound = assertThrows(IllegalArgumentException.class, () ->
                new BattlegroundPropertyValidator(battlegroundSize, numOfShipsUpperBoundTest, P1ShipPositionsUpper)
                        .validateBattlegroundProperties()
        );
        Exception exceptionLowerBound = assertThrows(IllegalArgumentException.class, () ->
                new BattlegroundPropertyValidator(battlegroundSize, numOfShipsLowerBoundTest, P1ShipPositionsLower)
                        .validateBattlegroundProperties()
        );

        String exceptionUpperBoundMessage = exceptionUpperBound.getMessage();
        String exceptionLowerBoundMessage = exceptionLowerBound.getMessage();

        assertTrue(exceptionUpperBoundMessage.equals("Invalid range for numOfShips: Must be in (0..battlegroundSize**2/2]"));
        assertTrue(exceptionLowerBoundMessage.equals("Invalid range for numOfShips: Must be in (0..battlegroundSize**2/2]"));
    }

    @Test
    public void givenBattlegroundSizeOutOfDefinedRangeThrowError() {
        int battlegroundSizeUpperBoundTest = 10;
        int battlegroundSizeLowerBoundTest = 0;
        int numOfShips = 3;
        Set<int[]> shipPositionsUpperBoundTest = generateMockShipPositions(battlegroundSizeUpperBoundTest, numOfShips);
        Set<int[]> shipPositionsLowerBoundTest = generateMockShipPositions(battlegroundSizeLowerBoundTest, numOfShips);

        Exception exceptionUpperBound = assertThrows(IllegalArgumentException.class, () ->
                new BattlegroundPropertyValidator(battlegroundSizeUpperBoundTest, numOfShips, shipPositionsUpperBoundTest)
                        .validateBattlegroundProperties()
        );
        Exception exceptionLowerBound = assertThrows(IllegalArgumentException.class, () ->
                new BattlegroundPropertyValidator(battlegroundSizeLowerBoundTest, numOfShips, shipPositionsLowerBoundTest)
                        .validateBattlegroundProperties()
        );

        String exceptionUpperBoundMessage = exceptionUpperBound.getMessage();
        String exceptionLowerBoundMessage = exceptionLowerBound.getMessage();

        assertTrue(exceptionUpperBoundMessage.equals("Invalid range for Battleground size: Must be in (0..10)"));
        assertTrue(exceptionLowerBoundMessage.equals("Invalid range for Battleground size: Must be in (0..10)"));
    }

    @Test
    public void givenShipPositionsNotMatchingNumberOfShipsThrowError() {
        int battlegroundSize = 9; //Arbitrary number
        int numOfP1AndP2ShipsCorrect = 2; //Arbitrary number
        int numOfP1ShipsWrong = 1;
        int numOfP2ShipsWrong = 3;
        Set<int[]> P1ShipPositionsWrong = generateMockShipPositions(battlegroundSize, numOfP1ShipsWrong);
        Set<int[]> P2ShipPositionsWrong = generateMockShipPositions(battlegroundSize, numOfP2ShipsWrong);

        Exception exceptionNumOfShipsMore = assertThrows(IllegalArgumentException.class, () ->
                new BattlegroundPropertyValidator(battlegroundSize, numOfP1AndP2ShipsCorrect, P1ShipPositionsWrong)
                        .validateBattlegroundProperties()
        );
        Exception exceptionNumOfShipsLess = assertThrows(IllegalArgumentException.class, () ->
                new BattlegroundPropertyValidator(battlegroundSize, numOfP1AndP2ShipsCorrect, P2ShipPositionsWrong)
                        .validateBattlegroundProperties()
        );

        String exceptionNumOfShipsMoreMessage = exceptionNumOfShipsMore.getMessage();
        String exceptionNumOfShipsLessMessage = exceptionNumOfShipsLess.getMessage();

        assertTrue(exceptionNumOfShipsMoreMessage.equals("shipPositions must align with numOfShips and no duplicate ships."));
        assertTrue(exceptionNumOfShipsLessMessage.equals("shipPositions must align with numOfShips and no duplicate ships."));
    }

    @Test
    public void givenCoordinatesOutOfBattlefieldThrowError() {
        int battlegroundSize = 9; //Arbitrary number
        int numOfShips = 1; //Arbitrary number
        Set<int[]> P1ShipPositionsWrong = new HashSet<>();
        P1ShipPositionsWrong.add(new int[] {0,-1});
        Set<int[]> P2ShipPositionsWrong = new HashSet<>();
        P2ShipPositionsWrong.add(new int[] {0,battlegroundSize});

        Exception exceptionCoordinateLessThanZero = assertThrows(IllegalArgumentException.class, () ->
                new BattlegroundPropertyValidator(battlegroundSize, numOfShips, P2ShipPositionsWrong)
                        .validateBattlegroundProperties()
        );
        Exception exceptionCoordinateMoreThanBattleground = assertThrows(IllegalArgumentException.class, () ->
                new BattlegroundPropertyValidator(battlegroundSize, numOfShips, P1ShipPositionsWrong)
                        .validateBattlegroundProperties()
        );

        String exceptionCoordinateLessThanZeroMessage = exceptionCoordinateLessThanZero.getMessage();
        String exceptionCoordinateMoreThanBattlegroundMessage = exceptionCoordinateMoreThanBattleground.getMessage();

        assertTrue(exceptionCoordinateLessThanZeroMessage.equals("All ships must be inside the battlefield."));
        assertTrue(exceptionCoordinateMoreThanBattlegroundMessage.equals("All ships must be inside the battlefield."));
    }

    private Set<int[]> generateMockShipPositions(int battlegroundSize, int numOfShips) {
        if (numOfShips == 0) {
            return new HashSet<>();
        } else if (numOfShips < 0) {
            throw new IllegalArgumentException("numOfShips must be a positive integer.");
        }

        Set<int[]> shipPositions = new HashSet<>();

        int shipCounter = 0;
        for (int x = 0; x < battlegroundSize; x++) {
            for (int y = 0; y < battlegroundSize; y++) {
                int[] shipCoordinate = {x,y};
                shipPositions.add(shipCoordinate);
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