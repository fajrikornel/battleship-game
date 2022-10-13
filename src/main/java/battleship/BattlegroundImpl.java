package battleship;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BattlegroundImpl implements Battleground {
    private Player player;
    private int battlegroundSize;
    private int numOfShips;
    private Set<List<Integer>> shipPositions;
    private Set<List<Integer>> successfulAttacks = new HashSet<>();
    private Set<List<Integer>> failedAttacks = new HashSet<>();

    public BattlegroundImpl(
            int battlegroundSize,
            int numOfShips,
            Set<List<Integer>> shipPositions) {
        this.battlegroundSize = battlegroundSize;
        this.numOfShips = numOfShips;
        this.shipPositions = shipPositions;

        BattlegroundPropertyValidator propertyValidator = new BattlegroundPropertyValidator(
                this.battlegroundSize,
                this.numOfShips,
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
        return failedAttacks.size();
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
