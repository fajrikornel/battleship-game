package battleship;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    public void givenMissileAvailableWhenPlayerAttacksThenMissileDecreasesByOne() {
        int numOfMissiles = 2;
        Player P1 = new Player(numOfMissiles);
        Player P2 = new Player(numOfMissiles);

        int battlegroundSize = 5;
        int numOfShips = 4;
        List<int[]> shipPositions = new ArrayList<int[]>();
        shipPositions.add(new int[] {0,1});
        shipPositions.add(new int[] {0,2});
        shipPositions.add(new int[] {0,3});
        shipPositions.add(new int[] {0,4});
        Battleground battlegroundP1 = new Battleground(
                P1,
                battlegroundSize,
                numOfShips,
                shipPositions);
        Battleground battlegroundP2 = new Battleground(
                P2,
                battlegroundSize,
                numOfShips,
                shipPositions);

        P1.setBattleground(battlegroundP1);
        P2.setBattleground(battlegroundP2);

        int[] attackCoordinate = {0,1};
        P1.attack(P2, attackCoordinate);
        assertEquals(numOfMissiles - 1, P1.getNumOfMissiles());
    }

    @Test
    public void givenOutOfMissilesWhenPlayerAttacksThenMissileStillZero() {
        int numOfMissiles = 1;
        Player P1 = new Player(numOfMissiles);
        Player P2 = new Player(numOfMissiles);

        int battlegroundSize = 5;
        int numOfShips = 4;
        List<int[]> shipPositions = new ArrayList<int[]>();
        shipPositions.add(new int[] {0,1});
        shipPositions.add(new int[] {0,2});
        shipPositions.add(new int[] {0,3});
        shipPositions.add(new int[] {0,4});
        Battleground battlegroundP1 = new Battleground(
                P1,
                battlegroundSize,
                numOfShips,
                shipPositions);
        Battleground battlegroundP2 = new Battleground(
                P2,
                battlegroundSize,
                numOfShips,
                shipPositions);

        P1.setBattleground(battlegroundP1);
        P2.setBattleground(battlegroundP2);

        int[] attackCoordinate = {0,1};
        P1.attack(P2, attackCoordinate);
        P1.attack(P2, attackCoordinate);
        assertEquals(0, P1.getNumOfMissiles());
    }

    @Test
    public void whenAllShipsDestroyedThenPlayerStateNotAlive() {
        int numOfMissiles = 4;
        Player P1 = new Player(numOfMissiles);
        Player P2 = new Player(numOfMissiles);

        int battlegroundSize = 5;
        int numOfShips = 4;
        List<int[]> shipPositions = new ArrayList<int[]>();
        shipPositions.add(new int[] {0,1});
        shipPositions.add(new int[] {0,2});
        shipPositions.add(new int[] {0,3});
        shipPositions.add(new int[] {0,4});
        Battleground battlegroundP1 = new Battleground(
                P1,
                battlegroundSize,
                numOfShips,
                shipPositions);
        Battleground battlegroundP2 = new Battleground(
                P2,
                battlegroundSize,
                numOfShips,
                shipPositions);

        P1.setBattleground(battlegroundP1);
        P2.setBattleground(battlegroundP2);

        int[] attackCoordinateOne = {0,1};
        P1.attack(P2, attackCoordinateOne);
        int[] attackCoordinateTwo = {0,2};
        P1.attack(P2, attackCoordinateTwo);
        int[] attackCoordinateThree = {0,3};
        P1.attack(P2, attackCoordinateThree);
        int[] attackCoordinateFour = {0,4};
        P1.attack(P2, attackCoordinateFour);
        assertFalse(P2.isAlive());
    }
}