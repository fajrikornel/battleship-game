package battleship;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomShipPositionsPlayerFactory implements PlayerFactory {
    private Player player;
    private Battleground battleground;
    private int battlegroundSize;
    private int numOfShips;
    private int numOfMissiles;
    private Set<int[]> shipPositions = new HashSet<>();

    public RandomShipPositionsPlayerFactory(
            int battlegroundSize,
            int numOfShips,
            int numOfMissiles) {
        this.battlegroundSize = battlegroundSize;
        this.numOfShips = numOfShips;
        this.numOfMissiles = numOfMissiles;
    }

    public Player getPlayerOrCreatePlayerIfNotCreated() {
        if (player == null) {
            player = new Player(numOfMissiles);
            generateRandomShipPositions();
            battleground = new Battleground(
                    battlegroundSize,
                    numOfShips,
                    shipPositions
            );

            battleground.setPlayer(player);
            player.setBattleground(battleground);
        }
        return player;
    }

    private void generateRandomShipPositions() {
        Random rand = new Random();
        while (shipPositions.size() != numOfShips) {
            int xCoordinate = rand.nextInt(battlegroundSize);
            int yCoordinate = rand.nextInt(battlegroundSize);
            int[] shipCoordinate = {xCoordinate, yCoordinate};
            shipPositions.add(shipCoordinate);
        }
    }
}
