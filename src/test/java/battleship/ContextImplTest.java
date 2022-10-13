package battleship;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ContextImplTest {

    @Test
    public void givenMissilesAvailableWhenAttackExecutedThenPlayerTurnShouldChange() {
        int battlegroundSize = 5;
        int numOfShips = 5;
        int numOfMissiles = 10;

        PlayerFactory P1Factory = new RandomShipPositionsPlayerFactory(
                battlegroundSize,
                numOfShips,
                numOfMissiles
        );

        PlayerFactory P2Factory = new RandomShipPositionsPlayerFactory(
                battlegroundSize,
                numOfShips,
                numOfMissiles
        );

        Player P1 = P1Factory.getPlayerOrCreatePlayerIfNotCreated();
        Player P2 = P2Factory.getPlayerOrCreatePlayerIfNotCreated();

        Context twoPlayerBattleship = new ContextImpl(P1, P2);

        List<Integer> attackCoordinate = new ArrayList<>(List.of(0, 0));
        Player shouldBeP1 = twoPlayerBattleship.getCurrentPlayer();
        twoPlayerBattleship.attack(attackCoordinate);
        Player shouldBeP2 = twoPlayerBattleship.getCurrentPlayer();
        twoPlayerBattleship.attack(attackCoordinate);
        Player shouldBeP1Again = twoPlayerBattleship.getCurrentPlayer();

        assertEquals(P1, shouldBeP1);
        assertEquals(P2, shouldBeP2);
        assertEquals(P1, shouldBeP1Again);
    }

    @Test
    public void whenAllPlayersOutOfMissilesThenChangeStateToGameOver() {
        int battlegroundSize = 5;
        int numOfShips = 5;
        int numOfMissiles = 1;

        PlayerFactory P1Factory = new RandomShipPositionsPlayerFactory(
                battlegroundSize,
                numOfShips,
                numOfMissiles
        );

        PlayerFactory P2Factory = new RandomShipPositionsPlayerFactory(
                battlegroundSize,
                numOfShips,
                numOfMissiles
        );

        Player P1 = P1Factory.getPlayerOrCreatePlayerIfNotCreated();
        Player P2 = P2Factory.getPlayerOrCreatePlayerIfNotCreated();

        Context twoPlayerBattleship = new ContextImpl(P1, P2);

        List<Integer> attackCoordinate = new ArrayList<>(List.of(0, 0));
        twoPlayerBattleship.attack(attackCoordinate);
        twoPlayerBattleship.attack(attackCoordinate);
        State state = twoPlayerBattleship.getState();

        assertTrue(state instanceof GameOver);
    }

    @Test
    public void whenGameOverThenGenerateCorrectPlayerReports() {
        int battlegroundSize = 5;
        int numOfShips = 2;
        int numOfMissiles = 2;
        Set<List<Integer>> shipPositions = //{{0,0}, {0,1}}
                generateMockShipPositions(battlegroundSize, numOfShips);

        Player P1 = new PlayerImpl(numOfMissiles);
        Battleground battlegroundP1 = new BattlegroundImpl(
                battlegroundSize,
                numOfShips,
                shipPositions
        );
        battlegroundP1.setPlayer(P1);
        P1.setBattleground(battlegroundP1);

        Player P2 = new PlayerImpl(numOfMissiles);
        Battleground battlegroundP2 = new BattlegroundImpl(
                battlegroundSize,
                numOfShips,
                shipPositions
        );
        battlegroundP2.setPlayer(P2);
        P2.setBattleground(battlegroundP2);

        Context twoPlayerBattleship = new ContextImpl(P1, P2);

        List<Integer> attackCoordinateP1One = new ArrayList<>(List.of(0, 0)); //Hit
        List<Integer> attackCoordinateP2One = new ArrayList<>(List.of(1, 0)); //No hit
        List<Integer> attackCoordinateP1Two = new ArrayList<>(List.of(0, 1)); //Hit
        List<Integer> attackCoordinateP2Two = new ArrayList<>(List.of(2, 0)); //No hit
        twoPlayerBattleship.attack(attackCoordinateP1One);
        twoPlayerBattleship.attack(attackCoordinateP2One);
        twoPlayerBattleship.attack(attackCoordinateP1Two);
        twoPlayerBattleship.attack(attackCoordinateP2Two);

        Map<Player, PlayerReport> playerReports = twoPlayerBattleship.getPlayerReports();

        assertTrue(playerReports.get(P1).isAlive);
        assertEquals(2, playerReports.get(P1).numOfMissiles);
        assertEquals(0, playerReports.get(P1).numOfMissilesAvailable);
        assertEquals(2, playerReports.get(P1).numOfMissilesFired);
        assertEquals(2, playerReports.get(P1).numOfShips);
        assertEquals(0, playerReports.get(P1).numOfDestroyedShips);
        assertEquals(2, playerReports.get(P1).numOfIntactShips);

        assertFalse(playerReports.get(P2).isAlive);
        assertEquals(2, playerReports.get(P2).numOfMissiles);
        assertEquals(0, playerReports.get(P2).numOfMissilesAvailable);
        assertEquals(2, playerReports.get(P2).numOfMissilesFired);
        assertEquals(2, playerReports.get(P2).numOfShips);
        assertEquals(2, playerReports.get(P2).numOfDestroyedShips);
        assertEquals(0, playerReports.get(P2).numOfIntactShips);
    }

    private Set<List<Integer>> generateMockShipPositions(int battlegroundSize, int numOfShips) {
        if (numOfShips == 0) {
            return new HashSet<>();
        } else if (numOfShips < 0) {
            throw new IllegalArgumentException("numOfShips must be a positive integer.");
        }

        Set<List<Integer>> shipPositions = new HashSet<>();

        int shipCounter = 0;
        for (int x = 0; x < battlegroundSize; x++) {
            for (int y = 0; y < battlegroundSize; y++) {
                List<Integer> shipCoordinate = new ArrayList<>();
                shipCoordinate.add(x);
                shipCoordinate.add(y);
                shipPositions.add(shipCoordinate);
                shipCounter++;
                if (shipCounter == numOfShips) {
                    break;
                }
            }
            if (shipCounter == numOfShips) {
                break;
            }
        }

        return shipPositions;
    }
}