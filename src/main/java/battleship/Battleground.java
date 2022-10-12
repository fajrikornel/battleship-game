package battleship;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Battleground {
    private Player player;
    private int battlegroundSize;
    private int numOfShips;
    private Set<int[]> shipPositions;
    private Set<int[]> successfulAttacks = new HashSet<int[]>();
    private Set<int[]> failedAttacks = new HashSet<int[]>();

    public Battleground(
            int battlegroundSize,
            int numOfShips,
            Set<int[]> shipPositions) {
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

    protected Player getPlayer() {
        return this.player;
    }

    public Set<int[]> getSuccessfulAttacks() {
        return successfulAttacks;
    }

    public Set<int[]> getFailedAttacks() {
        return failedAttacks;
    }

    public void attacked(int[] attackCoordinate) {
        if (isCoordinateInSet(attackCoordinate, shipPositions)) {
            successfulAttacks.add(attackCoordinate);
            if (isAllShipsDestroyed())
                updateAliveStatus();
        } else {
            failedAttacks.add(attackCoordinate);
        }
    }

    private boolean isCoordinateInSet(int[] coordinate, Set<int[]> positions) {
        return positions.stream().anyMatch(pos -> Arrays.equals(pos, coordinate));
    }

    private boolean isAllShipsDestroyed() {
        return shipPositions.size() == successfulAttacks.size();
    }

    protected void updateAliveStatus() {
        this.player.setAliveStatus(!isAllShipsDestroyed());
    }
}
