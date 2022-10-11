package battleship;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BattlegroundPropertyValidatorTest {
    @Test
    public void givenBattlegroundSizeOutOfDefinedRangeThrowError() {
        int battlegroundSizeUpperBoundTest = 10;
        int battlegroundSizeLowerBoundTest = 0;

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

    private List<int[]> generateMockShipPositions(int battlegroundSize, int numOfShips) {
        if (numOfShips == 0) {
            return new ArrayList<int[]>();
        } else if (numOfShips < 0) {
            throw new IllegalArgumentException("numOfShips must be a positive integer.");
        }

        List<int[]> shipPositions = new ArrayList<int[]>();

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