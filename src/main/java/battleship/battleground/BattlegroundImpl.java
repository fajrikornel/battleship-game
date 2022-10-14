package battleship.battleground;

import battleship.player.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BattlegroundImpl implements Battleground {
    private Player player;
    private final int battlegroundSize;
    private final Set<List<Integer>> shipPositions;
    private final Set<List<Integer>> successfulAttacks = new HashSet<>();
    private final Set<List<Integer>> failedAttacks = new HashSet<>();

    public BattlegroundImpl(
            int battlegroundSize,
            int numOfShips,
            Set<List<Integer>> shipPositions) {
        this.battlegroundSize = battlegroundSize;
        this.shipPositions = shipPositions;

        BattlegroundPropertyValidator propertyValidator = new BattlegroundPropertyValidatorImpl(
                this.battlegroundSize,
                numOfShips,
                this.shipPositions);
        propertyValidator.validateBattlegroundProperties();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getBattlegroundSize() {
        return battlegroundSize;
    }

    public int getNumOfDestroyedShips() {
        return successfulAttacks.size();
    }

    public int getNumOfIntactShips() {
        Set<List<Integer>> intactShipPositions = new HashSet<>(shipPositions);
        intactShipPositions.removeAll(successfulAttacks);
        return intactShipPositions.size();
    }

    public Set<List<Integer>> getShipPositions() {
        return shipPositions;
    }

    public Set<List<Integer>> getSuccessfulAttacks() {
        return successfulAttacks;
    }

    public Set<List<Integer>> getFailedAttacks() {
        return failedAttacks;
    }

    public void attacked(List<Integer> attackCoordinate) {
        if (isCoordinateInSet(attackCoordinate, shipPositions)) {
            successfulAttacks.add(attackCoordinate);
            if (isAllShipsDestroyed())
                updateAliveStatus();
        } else {
            failedAttacks.add(attackCoordinate);
        }
    }

    private boolean isCoordinateInSet(List<Integer> coordinate, Set<List<Integer>> positions) {
        return positions.contains(coordinate);
    }

    private boolean isAllShipsDestroyed() {
        return shipPositions.size() == successfulAttacks.size();
    }

    public void updateAliveStatus() {
        this.player.setAliveStatus(!isAllShipsDestroyed());
    }
}
