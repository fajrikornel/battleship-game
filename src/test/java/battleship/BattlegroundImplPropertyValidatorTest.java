package battleship;

import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BattlegroundImplPropertyValidatorTest {
    @Test
    public void givenNumOfShipsOutOfDefinedRangeThrowError() {
        int battlegroundSize = 9; //Arbitrary number
        int numOfShipsUpperBoundTest = battlegroundSize*battlegroundSize/2 + 1;
        int numOfShipsLowerBoundTest = 0;
        Set<List<Integer>> P1ShipPositionsUpper = generateMockShipPositions(battlegroundSize, numOfShipsUpperBoundTest);
        Set<List<Integer>> P1ShipPositionsLower = generateMockShipPositions(battlegroundSize, numOfShipsLowerBoundTest);

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
        Set<List<Integer>> shipPositionsUpperBoundTest = generateMockShipPositions(battlegroundSizeUpperBoundTest, numOfShips);
        Set<List<Integer>> shipPositionsLowerBoundTest = generateMockShipPositions(battlegroundSizeLowerBoundTest, numOfShips);

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
        Set<List<Integer>> P1ShipPositionsWrong = generateMockShipPositions(battlegroundSize, numOfP1ShipsWrong);
        Set<List<Integer>> P2ShipPositionsWrong = generateMockShipPositions(battlegroundSize, numOfP2ShipsWrong);

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
        Set<List<Integer>> P1ShipPositionsWrong = new HashSet<>();
        List<Integer> wrongPosition1 = new ArrayList<>(List.of(0,-1));
        P1ShipPositionsWrong.add(wrongPosition1);
        Set<List<Integer>> P2ShipPositionsWrong = new HashSet<>();
        List<Integer> wrongPosition2 = new ArrayList<>(List.of(0,battlegroundSize));
        P2ShipPositionsWrong.add(wrongPosition2);

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

    private Set<List<Integer>> generateMockShipPositions(int battlegroundSize, int numOfShips) {
        if (numOfShips == 0) {
            return new HashSet<>();
        } else if (numOfShips < 0) {
            throw new IllegalArgumentException("numOfShips must be a positive integer.");
        }

        Set<List<Integer>> shipPositions = new HashSet<>();

        int shipCounter = 0;
        for (int x = 0; x < battlegroundSize; x++) {
            for (int y = 0; y < battlegroundSize; y++) {
                List<Integer> shipCoordinate = new ArrayList<>();
                shipCoordinate.add(x);
                shipCoordinate.add(y);
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