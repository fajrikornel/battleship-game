package battleship;

public class BattlegroundPropertyValidator {
    private int battlegroundSize;

    public BattlegroundPropertyValidator(int battlegroundSize) {
        this.battlegroundSize = battlegroundSize;
    }

    public void validateBattlegroundProperty() {
        validateBattlegroundSizeProperty();
    }

    private void validateBattlegroundSizeProperty() {
        int lowerBound = 0;
        int upperBound = 10;

        if (battlegroundSize <= lowerBound || battlegroundSize >= upperBound) {
            throw new IllegalArgumentException("Invalid range for Battleground size: Must be in (0..10)");
        }
    }
}
