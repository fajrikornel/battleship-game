package battleship;

import java.util.*;

public class ContextFactoryImpl implements ContextFactory {
    private Context context;
    private int battlegroundSize;
    private int numOfShips;
    private int numOfMissiles;
    private List<Set<List<Integer>>> shipPositionsList = new ArrayList<>();

    public ContextFactoryImpl(
            int battlegroundSize,
            int numOfShips,
            int numOfMissiles,
            Set<List<Integer>>... shipPositionsList
    ) {
        this.battlegroundSize = battlegroundSize;
        this.numOfShips = numOfShips;
        this.numOfMissiles = numOfMissiles;
        this.shipPositionsList.addAll(Arrays.asList(shipPositionsList));
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
