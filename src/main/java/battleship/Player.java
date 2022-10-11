package battleship;

public class Player {
    private Battleship battleship;
    private int numOfShips;
    private int numOfMissiles;
    private int[][] shipPositions;

    public Player(
            Battleship battleship,
            int numOfShips,
            int numOfMissiles,
            int[][] shipPositions) {
        this.battleship = battleship;
        this.numOfShips = numOfShips;
        this.numOfMissiles = numOfMissiles;
        this.shipPositions = shipPositions;

        PlayerPropertyValidator propertyValidator = new PlayerPropertyValidator(
                battleship.getBattlegroundSize(),
                numOfShips,
                numOfMissiles,
                shipPositions
        );
        propertyValidator.validatePlayerProperties();
    }


}
