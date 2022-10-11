package battleship;

public class Battleship {
    private Battleground battleground;
    private Player P1;
    private Player P2;

    public Battleship(int battlegroundSize,
                      int numOfShips,
                      int numOfMissiles,
                      int[][] P1ShipPositions,
                      int[][] P2ShipPositions) {
        this.battleground = new Battleground(battlegroundSize);
        this.P1 = new Player(
                this,
                numOfShips,
                numOfMissiles,
                P1ShipPositions
        );
        this.P1 = new Player(
                this,
                numOfShips,
                numOfMissiles,
                P2ShipPositions
        );


    }

    protected int getBattlegroundSize() {
        return battleground.getBattlegroundSize();
    }
}