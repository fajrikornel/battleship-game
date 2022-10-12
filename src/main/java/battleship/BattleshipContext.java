package battleship;

public interface BattleshipContext {
    void setState(BattleshipState state);
    BattleshipState getState();
    Player getCurrentPlayer();
    void attack(int[] coordinate);
}
