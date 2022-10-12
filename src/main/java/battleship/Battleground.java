package battleship;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Battleground {
    private Player player;
    private int battlegroundSize;
    private int numOfShips;
    private Set<List<Integer>> shipPositions;
    private Set<List<Integer>> successfulAttacks = new HashSet<>();
    private Set<List<Integer>> failedAttacks = new HashSet<>();

    public Battleground(
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

    protected Player getPlayer() {
        return this.player;
    }

    public Set<List<Integer>> getSuccessfulAttacks() {
        return successfulAttacks;
    }

    public Set<List<Integer>> getFailedAttacks() {
        return failedAttacks;
    }

    public int getNumOfDestroyedShips() {
        return successfulAttacks.size();
    }

    public int getNumOfIntactShips() {
        return failedAttacks.size();
    }

    public void attacked(List<Integer> attackCoordinate) {
        if (isCoordinateInSet(attackCoordinate, shipPositions)) {
            successfulAttacks.add(attackCoordinate);
            System.out.println("Successful attack. Size: " + successfulAttacks.size());
            successfulAttacks.stream().forEach(s -> System.out.println("Success coordinate: " + s.get(0) + "," + s.get(1)));
            if (isAllShipsDestroyed())
                updateAliveStatus();
        } else {
            failedAttacks.add(attackCoordinate);
            System.out.println("Failed attack. Size: " + failedAttacks.size());
            failedAttacks.stream().forEach(s -> System.out.println("Failed coordinate: " + s.get(0) + "," + s.get(1)));
        }
    }

    private boolean isCoordinateInSet(List<Integer> coordinate, Set<List<Integer>> positions) {
        return positions.contains(coordinate);
    }

    private boolean isAllShipsDestroyed() {
        return shipPositions.size() == successfulAttacks.size();
    }

    protected void updateAliveStatus() {
        this.player.setAliveStatus(!isAllShipsDestroyed());
    }
}
