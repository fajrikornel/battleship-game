package battleship.player;

import battleship.battleground.Battleground;
import battleship.battleground.BattlegroundImpl;

import java.util.List;
import java.util.Set;

public class PlayerFactoryImpl implements PlayerFactory {
    private Player player;
    private final int battlegroundSize;
    private final int numOfShips;
    private final int numOfMissiles;
    Set<List<Integer>> shipPositions;


    public PlayerFactoryImpl(
            int battlegroundSize,
            int numOfShips,
            int numOfMissiles,
            Set<List<Integer>> shipPositions) {
        this.battlegroundSize = battlegroundSize;
        this.numOfShips = numOfShips;
        this.numOfMissiles = numOfMissiles;
        this.shipPositions = shipPositions;
    }
    public Player getPlayerOrCreatePlayerIfNotCreated() {
        if (player == null) {
            player = new PlayerImpl(numOfMissiles);
            Battleground battleground = new BattlegroundImpl(
                    battlegroundSize,
                    numOfShips,
                    shipPositions
            );

            battleground.setPlayer(player);
            player.setBattleground(battleground);
        }
        return player;
    }
}
