package battleship;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerPropertyValidator {
    private int numOfMissiles;

    public PlayerPropertyValidator(int numOfMissiles) {
        this.numOfMissiles = numOfMissiles;
    }

    public void validatePlayerProperties() {
        validateNumOfMissilesProperty();
    }

    private void validateNumOfMissilesProperty() {
        int lowerBound = 0;
        int upperBound = 100;

        if (numOfMissiles <= lowerBound || numOfMissiles >= upperBound) {
            throw new IllegalArgumentException("Invalid range for numOfMissiles: Must be in (0..100)");
        }
    }
}
