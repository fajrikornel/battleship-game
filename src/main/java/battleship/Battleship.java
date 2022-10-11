package battleship;

public class Battleship {
    private int battlegroundSize;
    private int numOfShips;
    private int numOfMissiles;
    private int[][] P1ShipPositions;
    private int[][] P2ShipPositions;

    public Battleship(int battlegroundSize,
                      int numOfShips,
                      int numOfMissiles,
                      int[][] P1ShipPositions,
                      int[][] P2ShipPositions) {
        this.battlegroundSize = battlegroundSize;
        this.numOfShips = numOfShips;
        this.numOfMissiles = numOfMissiles;
        this.P1ShipPositions = P1ShipPositions;
        this.P2ShipPositions = P2ShipPositions;

        BattleshipPropertyValidator propertyValidator = new BattleshipPropertyValidator(
                battlegroundSize,
                numOfShips,
                numOfMissiles,
                P1ShipPositions,
                P2ShipPositions
        );

        propertyValidator.validateBattleshipProperties();
    }



}