package battleship.player;

import battleship.battleground.Battleground;
import battleship.battleground.BattlegroundImpl;

import java.util.*;

public class RandomShipPositionsPlayerFactory implements PlayerFactory {
    private Player player;
    private Battleground battleground;
    private int battlegroundSize;
    private int numOfShips;
    private int numOfMissiles;
    private Set<List<Integer>> shipPositions = new HashSet<>();

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
            player = new PlayerImpl(numOfMissiles);
            generateRandomShipPositions();
            battleground = new BattlegroundImpl(
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
            List<Integer> shipCoordinate = new ArrayList<>();
            shipCoordinate.add(xCoordinate);
            shipCoordinate.add(yCoordinate);
            shipPositions.add(shipCoordinate);
        }
    }
}
