package battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomShipPositionsPlayerFactoryTest {

    @Test
    public void whenGetPlayerEnsurePlayerAndBattlegroundIsConnected() {
        int battlegroundSize = 5;
        int numOfShips = 5;
        int numOfMissiles = 5;

        PlayerFactory factory = new RandomShipPositionsPlayerFactory(
                battlegroundSize,
                numOfShips,
                numOfMissiles);
        Player player = factory.getPlayerOrCreatePlayerIfNotCreated();
        Battleground battleground = player.getBattleground();

        assertTrue(player == battleground.getPlayer());
    }
}