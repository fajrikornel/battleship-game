package battleship;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BattlegroundTest {
    @Test
    public void givenMissedAttackThenAddAttackCoordinateToFailedAttacks() {
        int numOfMissiles = 2;
        Player P1 = new Player(numOfMissiles);

        int battlegroundSize = 5;
        int numOfShips = 4;
        Set<int[]> shipPositions = new HashSet<>();
        shipPositions.add(new int[] {0,1});
        shipPositions.add(new int[] {0,2});
        shipPositions.add(new int[] {0,3});
        shipPositions.add(new int[] {0,4});
        Battleground battlegroundP1 = new Battleground(
                P1,
                battlegroundSize,
                numOfShips,
                shipPositions);

        P1.setBattleground(battlegroundP1);

        int[] attackCoordinate = {1,0};
        battlegroundP1.attacked(attackCoordinate);
        assertEquals(true,isCoordinateInSet(attackCoordinate,battlegroundP1.getFailedAttacks()));
        assertEquals(false,isCoordinateInSet(attackCoordinate,battlegroundP1.getSuccessfulAttacks()));
    }

    @Test
    public void givenSuccessfulAttackThenAddAttackCoordinateToSuccessfulAttacks() {
        int numOfMissiles = 2;
        Player P1 = new Player(numOfMissiles);

        int battlegroundSize = 5;
        int numOfShips = 4;
        Set<int[]> shipPositions = new HashSet<int[]>();
        shipPositions.add(new int[] {0,1});
        shipPositions.add(new int[] {0,2});
        shipPositions.add(new int[] {0,3});
        shipPositions.add(new int[] {0,4});
        Battleground battlegroundP1 = new Battleground(
                P1,
                battlegroundSize,
                numOfShips,
                shipPositions);

        P1.setBattleground(battlegroundP1);

        int[] attackCoordinate = {0,1};
        battlegroundP1.attacked(attackCoordinate);
        assertEquals(false,isCoordinateInSet(attackCoordinate,battlegroundP1.getFailedAttacks()));
        assertEquals(true,isCoordinateInSet(attackCoordinate,battlegroundP1.getSuccessfulAttacks()));
    }

    private boolean isCoordinateInSet(int[] coordinate, Set<int[]> set) {
        return set.stream().anyMatch(setPoint -> Arrays.equals(setPoint,coordinate));
    }
}