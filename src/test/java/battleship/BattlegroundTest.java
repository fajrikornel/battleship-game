package battleship;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BattlegroundTest {
    @Test
    public void givenMissedAttackThenAddAttackCoordinateToFailedAttacks() {
        int battlegroundSize = 4;
        int numOfShips = 2;
        List<int[]> shipPositions = new ArrayList<int[]>();
        shipPositions.add(new int[] {0,1});
        shipPositions.add(new int[] {0,2});

        Battleground battleground = new Battleground(
                battlegroundSize,
                numOfShips,
                shipPositions);

        int[] attackCoordinate = {1,0};
        battleground.attacked(attackCoordinate);
        assertEquals(true,isCoordinateInList(attackCoordinate,battleground.getFailedAttacks()));
        assertEquals(false,isCoordinateInList(attackCoordinate,battleground.getSuccessfulAttacks()));
    }

    @Test
    public void givenSuccessfulAttackThenAddAttackCoordinateToSuccessfulAttacks() {
        int battlegroundSize = 4;
        int numOfShips = 2;
        List<int[]> shipPositions = new ArrayList<int[]>();
        shipPositions.add(new int[] {0,1});
        shipPositions.add(new int[] {0,2});

        Battleground battleground = new Battleground(
                battlegroundSize,
                numOfShips,
                shipPositions);

        int[] attackCoordinate = {0,1};
        battleground.attacked(attackCoordinate);
        assertEquals(false,isCoordinateInList(attackCoordinate,battleground.getFailedAttacks()));
        assertEquals(true,isCoordinateInList(attackCoordinate,battleground.getSuccessfulAttacks()));
    }

    private boolean isCoordinateInList(int[] coordinate, List<int[]> list) {
        if (list.stream().anyMatch(shipPos -> Arrays.equals(shipPos, coordinate)))
            return true;
        return false;
    }
}