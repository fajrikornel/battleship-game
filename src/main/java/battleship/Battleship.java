package battleship;

import java.util.Set;

public class Battleship {
    private Player P1;
    private Player P2;

    public Battleship(int battlegroundSize,
                      int numOfShips,
                      int numOfMissiles,
                      Set<int[]> P1ShipPositions,
                      Set<int[]> P2ShipPositions) {

        this.P1 = new Player(numOfMissiles);
        Battleground battlegroundP1 = new Battleground(
                P1,
                battlegroundSize,
                numOfShips,
                P1ShipPositions);
        P1.setBattleground(battlegroundP1);

        this.P2 = new Player(numOfMissiles);
        Battleground battlegroundP2 = new Battleground(
                P2,
                battlegroundSize,
                numOfShips,
                P2ShipPositions);
        P2.setBattleground(battlegroundP2);
    }
}