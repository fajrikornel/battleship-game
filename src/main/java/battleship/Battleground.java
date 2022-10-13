package battleship;

import java.util.List;
import java.util.Set;

public interface Battleground {
    void setPlayer(Player player);
    Player getPlayer();
    int getBattlegroundSize();
    int getNumOfDestroyedShips();
    int getNumOfIntactShips();
    Set<List<Integer>> getShipPositions();
    Set<List<Integer>> getFailedAttacks();
    Set<List<Integer>> getSuccessfulAttacks();
    void attacked(List<Integer> attackCoordinate);
    void updateAliveStatus();
}
