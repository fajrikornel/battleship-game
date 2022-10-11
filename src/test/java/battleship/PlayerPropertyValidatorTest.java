package battleship;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPropertyValidatorTest {

    @Test
    public void givenNumOfMissilesOutOfDefinedRangeThrowError() {
        int numOfMissilesUpperBoundTest = 100;
        int numOfMissilesLowerBoundTest = 0;

        Exception exceptionUpperBound = assertThrows(IllegalArgumentException.class, () ->
                new PlayerPropertyValidator(numOfMissilesUpperBoundTest)
                        .validatePlayerProperties()
        );
        Exception exceptionLowerBound = assertThrows(IllegalArgumentException.class, () ->
                new PlayerPropertyValidator(numOfMissilesLowerBoundTest)
                        .validatePlayerProperties()
        );

        String exceptionUpperBoundMessage = exceptionUpperBound.getMessage();
        String exceptionLowerBoundMessage = exceptionLowerBound.getMessage();

        assertTrue(exceptionUpperBoundMessage.contains("Invalid range for numOfMissiles: Must be in (0..100)"));
        assertTrue(exceptionLowerBoundMessage.contains("Invalid range for numOfMissiles: Must be in (0..100)"));
    }
}