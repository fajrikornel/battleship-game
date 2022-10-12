package battleship;

import java.util.List;

public interface BattleshipState {
    Player getCurrentPlayer();
    void attack(int[] attackCoordinate);
}
