package battleship.player;

import battleship.player.PlayerPropertyValidatorImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPropertyValidatorImplTest {

    @Test
    public void givenNumOfMissilesOutOfDefinedRangeThrowError() {
        int numOfMissilesUpperBoundTest = 100;
        int numOfMissilesLowerBoundTest = 0;

        Exception exceptionUpperBound = assertThrows(IllegalArgumentException.class, () ->
                new PlayerPropertyValidatorImpl(numOfMissilesUpperBoundTest)
                        .validatePlayerProperties()
        );
        Exception exceptionLowerBound = assertThrows(IllegalArgumentException.class, () ->
                new PlayerPropertyValidatorImpl(numOfMissilesLowerBoundTest)
                        .validatePlayerProperties()
        );

        String exceptionUpperBoundMessage = exceptionUpperBound.getMessage();
        String exceptionLowerBoundMessage = exceptionLowerBound.getMessage();

        assertTrue(exceptionUpperBoundMessage.equals("Invalid range for numOfMissiles: Must be in (0..100)"));
        assertTrue(exceptionLowerBoundMessage.equals("Invalid range for numOfMissiles: Must be in (0..100)"));
    }
}