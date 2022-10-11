package battleship;

import java.util.List;

public class Battleship {
    private Player P1;
    private Player P2;

    public Battleship(int battlegroundSize,
                      int numOfShips,
                      int numOfMissiles,
                      List<int[]> P1ShipPositions,
                      List<int[]> P2ShipPositions) {
        this.P1 = new Player(
                new Battleground(battlegroundSize),
                numOfShips,
                numOfMissiles,
                P1ShipPositions
        );
        this.P2 = new Player(
                new Battleground(battlegroundSize),
                numOfShips,
                numOfMissiles,
                P2ShipPositions
        );
    }
}