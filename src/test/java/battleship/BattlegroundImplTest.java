package battleship;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BattlegroundImplTest {
    @Test
    public void givenFailedAttackThenAddAttackCoordinateToFailedAttacks() {
        int numOfMissiles = 2;
        Player P1 = new PlayerImpl(numOfMissiles);

        int battlegroundSize = 5;
        int numOfShips = 4;
        Set<List<Integer>> shipPositions = new HashSet<>();
        shipPositions.add(new ArrayList<>(List.of(0,1)));
        shipPositions.add(new ArrayList<>(List.of(0,2)));
        shipPositions.add(new ArrayList<>(List.of(0,3)));
        shipPositions.add(new ArrayList<>(List.of(0,4)));
        Battleground battlegroundP1 = new BattlegroundImpl(
                battlegroundSize,
                numOfShips,
                shipPositions);

        battlegroundP1.setPlayer(P1);
        P1.setBattleground(battlegroundP1);

        List<Integer> attackCoordinate = new ArrayList<>(List.of(1,0));
        battlegroundP1.attacked(attackCoordinate);
        assertTrue(isCoordinateInSet(attackCoordinate,battlegroundP1.getFailedAttacks()));
        assertFalse(isCoordinateInSet(attackCoordinate,battlegroundP1.getSuccessfulAttacks()));
    }

    @Test
    public void givenSuccessfulAttackThenAddAttackCoordinateToSuccessfulAttacks() {
        int numOfMissiles = 2;
        Player P1 = new PlayerImpl(numOfMissiles);

        int battlegroundSize = 5;
        int numOfShips = 4;
        Set<List<Integer>> shipPositions = new HashSet<>();
        shipPositions.add(new ArrayList<>(List.of(0,1)));
        shipPositions.add(new ArrayList<>(List.of(0,2)));
        shipPositions.add(new ArrayList<>(List.of(0,3)));
        shipPositions.add(new ArrayList<>(List.of(0,4)));
        Battleground battlegroundP1 = new BattlegroundImpl(
                battlegroundSize,
                numOfShips,
                shipPositions);

        battlegroundP1.setPlayer(P1);
        P1.setBattleground(battlegroundP1);

        List<Integer> attackCoordinate = new ArrayList<>(List.of(0,1));
        battlegroundP1.attacked(attackCoordinate);
        assertEquals(false,isCoordinateInSet(attackCoordinate,battlegroundP1.getFailedAttacks()));
        assertEquals(true,isCoordinateInSet(attackCoordinate,battlegroundP1.getSuccessfulAttacks()));
    }

    private boolean isCoordinateInSet(List<Integer> coordinate, Set<List<Integer>> set) {
        return set.contains(coordinate);
    }
}