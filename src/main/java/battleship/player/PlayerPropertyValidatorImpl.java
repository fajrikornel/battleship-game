package battleship.player;

public class PlayerPropertyValidatorImpl implements PlayerPropertyValidator {
    private int numOfMissiles;

    public PlayerPropertyValidatorImpl(int numOfMissiles) {
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
