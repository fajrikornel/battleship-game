package battleship.state;

import battleship.player.Player;
import battleship.player.PlayerFactory;
import battleship.player.PlayerFactoryImpl;

import java.util.*;

public class ContextFactoryImpl implements ContextFactory {
    private Context context;
    private int battlegroundSize;
    private int numOfShips;
    private int numOfMissiles;
    private List<Set<List<Integer>>> shipPositionsList;

    public ContextFactoryImpl(
            int battlegroundSize,
            int numOfShips,
            int numOfMissiles,
            List<Set<List<Integer>>> shipPositionsAllPlayers
    ) {
        this.battlegroundSize = battlegroundSize;
        this.numOfShips = numOfShips;
        this.numOfMissiles = numOfMissiles;
        this.shipPositionsList = shipPositionsAllPlayers;
    }

    public Context getContextOrCreateContextIfNotCreated() {
        if (context == null) {
            List<Player> playerList = new ArrayList<>();
            for(Set<List<Integer>> shipPositions: shipPositionsList) {
                PlayerFactory playerFactory = new PlayerFactoryImpl(
                        battlegroundSize,
                        numOfShips,
                        numOfMissiles,
                        shipPositions
                );

                Player player = playerFactory.getPlayerOrCreatePlayerIfNotCreated();
                playerList.add(player);
            }

            context = new ContextImpl(playerList.toArray(Player[]::new));
        }
        return context;
    }
}
