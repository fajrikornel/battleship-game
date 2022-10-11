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
                battlegroundSize,
                numOfShips,
                P1ShipPositions);

        battlegroundP1.setPlayer(P1);
        P1.setBattleground(battlegroundP1);

        this.P2 = new Player(numOfMissiles);
        Battleground battlegroundP2 = new Battleground(
                battlegroundSize,
                numOfShips,
                P2ShipPositions);
        battlegroundP2.setPlayer(P2);
        P2.setBattleground(battlegroundP2);
    }
}