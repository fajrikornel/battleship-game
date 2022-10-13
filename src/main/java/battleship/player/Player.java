package battleship.player;

import battleship.battleground.Battleground;

import java.util.List;

public interface Player {
    void setBattleground(Battleground battleground);
    Battleground getBattleground();
    boolean isAlive();
    void setAliveStatus(boolean status);
    int getNumOfMissilesAvailable();
    int getNumOfMissilesFired();
    int getNumOfDestroyedShips();
    int getNumOfIntactShips();
    void attack(Player target, List<Integer> attackCoordinate);
    void attacked(List<Integer> attackCoordinate);
}
