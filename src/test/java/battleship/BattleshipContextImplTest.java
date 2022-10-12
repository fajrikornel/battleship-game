package battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleshipContextImplTest {

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

        BattleshipContext twoPlayerBattleship = new BattleshipContextImpl(P1, P2);

        int[] attackCoordinate = {0,0};
        Player shouldBeP1 = twoPlayerBattleship.getCurrentPlayer();
        twoPlayerBattleship.attack(attackCoordinate);
        Player shouldBeP2 = twoPlayerBattleship.getCurrentPlayer();
        twoPlayerBattleship.attack(attackCoordinate);
        Player shouldBeP1Again = twoPlayerBattleship.getCurrentPlayer();

        assertEquals(P1, shouldBeP1);
        assertEquals(P2, shouldBeP2);
        assertEquals(P1, shouldBeP1Again);
    }

    //like above but given p2 turn
    //edge case when it's about to game over
}