package battleship;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PlayerTest {
    @Test
    public void givenMissileAvailableWhenPlayerAttacksThenMissileDecreasesByOne() {
        int numOfMissiles = 2;
        Player P1 = new Player(numOfMissiles);
        Player P2 = new Player(numOfMissiles);

        int battlegroundSize = 5;
        int numOfShips = 4;
        Set<List<Integer>> shipPositions = new HashSet<>();
        shipPositions.add(new ArrayList<>(List.of(0,1)));
        shipPositions.add(new ArrayList<>(List.of(0,2)));
        shipPositions.add(new ArrayList<>(List.of(0,3)));
        shipPositions.add(new ArrayList<>(List.of(0,4)));
        Battleground battlegroundP1 = new Battleground(
                battlegroundSize,
                numOfShips,
                shipPositions);
        Battleground battlegroundP2 = new Battleground(
                battlegroundSize,
                numOfShips,
                shipPositions);

        battlegroundP1.setPlayer(P1);
        P1.setBattleground(battlegroundP1);
        battlegroundP2.setPlayer(P2);
        P2.setBattleground(battlegroundP2);

        List<Integer> attackCoordinate = new ArrayList<>(List.of(0,1));
        P1.attack(P2, attackCoordinate);
        assertEquals(numOfMissiles - 1, P1.getNumOfMissilesAvailable());
    }

    @Test
    public void givenOutOfMissilesWhenPlayerAttacksThenMissileStillZero() {
        int numOfMissiles = 1;
        Player P1 = new Player(numOfMissiles);
        Player P2 = new Player(numOfMissiles);

        int battlegroundSize = 5;
        int numOfShips = 4;
        Set<List<Integer>> shipPositions = new HashSet<>();
        shipPositions.add(new ArrayList<>(List.of(0,1)));
        shipPositions.add(new ArrayList<>(List.of(0,2)));
        shipPositions.add(new ArrayList<>(List.of(0,3)));
        shipPositions.add(new ArrayList<>(List.of(0,4)));
        Battleground battlegroundP1 = new Battleground(
                battlegroundSize,
                numOfShips,
                shipPositions);
        Battleground battlegroundP2 = new Battleground(
                battlegroundSize,
                numOfShips,
                shipPositions);

        battlegroundP1.setPlayer(P1);
        P1.setBattleground(battlegroundP1);
        battlegroundP2.setPlayer(P2);
        P2.setBattleground(battlegroundP2);

        List<Integer> attackCoordinate = new ArrayList<>(List.of(0,1));
        P1.attack(P2, attackCoordinate);
        P1.attack(P2, attackCoordinate);
        assertEquals(0, P1.getNumOfMissilesAvailable());
    }

    @Test
    public void whenAllShipsDestroyedThenPlayerStateNotAlive() {
        int numOfMissiles = 4;
        Player P1 = new Player(numOfMissiles);
        Player P2 = new Player(numOfMissiles);

        int battlegroundSize = 5;
        int numOfShips = 4;
        Set<List<Integer>> shipPositions = new HashSet<>();
        shipPositions.add(new ArrayList<>(List.of(0,1)));
        shipPositions.add(new ArrayList<>(List.of(0,2)));
        shipPositions.add(new ArrayList<>(List.of(0,3)));
        shipPositions.add(new ArrayList<>(List.of(0,4)));
        Battleground battlegroundP1 = new Battleground(
                battlegroundSize,
                numOfShips,
                shipPositions);
        Battleground battlegroundP2 = new Battleground(
                battlegroundSize,
                numOfShips,
                shipPositions);

        battlegroundP1.setPlayer(P1);
        P1.setBattleground(battlegroundP1);
        battlegroundP2.setPlayer(P2);
        P2.setBattleground(battlegroundP2);

        List<Integer> attackCoordinateOne = new ArrayList<>(List.of(0,1));
        P1.attack(P2, attackCoordinateOne);
        List<Integer> attackCoordinateTwo = new ArrayList<>(List.of(0,2));
        P1.attack(P2, attackCoordinateTwo);
        List<Integer> attackCoordinateThree = new ArrayList<>(List.of(0,3));
        P1.attack(P2, attackCoordinateThree);
        List<Integer> attackCoordinateFour = new ArrayList<>(List.of(0,4));
        P1.attack(P2, attackCoordinateFour);
        assertFalse(P2.isAlive());
    }
}