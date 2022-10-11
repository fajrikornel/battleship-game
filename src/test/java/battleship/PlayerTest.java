package battleship;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    public void givenMissedAttackThenAddAttackCoordinateToFailedAttacks() {
        int battlegroundSize = 4;
        int numOfShips = 2;
        int numOfMissiles = 2;
        List<int[]> shipPositions = new ArrayList<int[]>();
        shipPositions.add(new int[] {0,1});
        shipPositions.add(new int[] {0,2});

        Battleground battleground = new Battleground(battlegroundSize);
        Player player = new Player(
                battleground,
                numOfShips,
                numOfMissiles,
                shipPositions
        );

        int[] attackCoordinate = {1,0};
        player.attacked(attackCoordinate);
        assertEquals(true,isCoordinateInList(attackCoordinate,player.getFailedAttacks()));
        assertEquals(false,isCoordinateInList(attackCoordinate,player.getSuccessfulAttacks()));
    }

    @Test
    public void givenSuccessfulAttackThenAddAttackCoordinateToSuccessfulAttacks() {
        int battlegroundSize = 4;
        int numOfShips = 2;
        int numOfMissiles = 2;
        List<int[]> shipPositions = new ArrayList<int[]>();
        shipPositions.add(new int[] {0,1});
        shipPositions.add(new int[] {0,2});

        Battleground battleground = new Battleground(battlegroundSize);
        Player player = new Player(
                battleground,
                numOfShips,
                numOfMissiles,
                shipPositions
        );

        int[] attackCoordinate = {0,1};
        player.attacked(attackCoordinate);
        assertEquals(false,isCoordinateInList(attackCoordinate,player.getFailedAttacks()));
        assertEquals(true,isCoordinateInList(attackCoordinate,player.getSuccessfulAttacks()));
    }

    private boolean isCoordinateInList(int[] coordinate, List<int[]> list) {
        if (list.stream().anyMatch(shipPos -> Arrays.equals(shipPos, coordinate)))
            return true;
        return false;
    }
}