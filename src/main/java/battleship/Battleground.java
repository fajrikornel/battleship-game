package battleship;

public class Battleground {
    private int battlegroundSize;

    public Battleground(int battlegroundSize) {
        this.battlegroundSize = battlegroundSize;

        BattlegroundPropertyValidator propertyValidator = new BattlegroundPropertyValidator(battlegroundSize);
        propertyValidator.validateBattlegroundProperty();
    }

    protected int getBattlegroundSize() {
        return battlegroundSize;
    }
}
